package automatedgrader.template;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import automatedgrader.strategy.EvaluationResult;
import automatedgrader.strategy.JavaClassEvaluator;

public class NestedZipFileHandler extends FileHandler {
    private List<JavaClassEvaluator> javaClassEvaluators = new ArrayList<>();
    private EvaluationResult evaluationResults;
    private List<String> processedFiles = new ArrayList<>();
    private List<String> studentFolderPaths = new ArrayList<>();

    public NestedZipFileHandler(EvaluationResult evaluationResults) {
        this.evaluationResults = evaluationResults;
    }

    // Method to add JavaClassEvaluator instances
    public void addJavaClassEvaluator(JavaClassEvaluator evaluator) {
        javaClassEvaluators.add(evaluator);
    }

    /**
     * Extracts files from a nested zip archive specified by the given path.
     *
     * @param zipFilePath The path to the nested zip file to be extracted.
     * @throws IOException If an I/O error occurs during extraction.
     */
    @Override
    protected void extract(String zipFilePath) throws IOException {
        extractRecursively(zipFilePath);
    }

    private void extractRecursively(String zipFilePath) throws IOException {
        try (ZipInputStream in = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = in.getNextEntry();

            while (entry != null) {
                String destFilePath = zipFilePath.substring(0, zipFilePath.length() - 4) + File.separator
                        + entry.getName();

                File file = new File(destFilePath);

                if (!processedFiles.contains(destFilePath)) {
                    if (!file.exists()) {
                        Path destinationPath = Paths.get(destFilePath);
                        Files.createDirectories(destinationPath.getParent());
                        Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        if (entry.isDirectory()) {
                            // If the entry is a directory, make the directory
                            File dir = new File(destFilePath);
                            dir.mkdirs();
                        } else if (getExtension(entry.getName()).equals("zip")) {
                            // Recursively handle nested zip files
                            extractRecursively(destFilePath);
                        } else if (file.isFile() && getExtension(file.getName()).equals("java")) {
                            // Process the Java class if it's a file and has a "java" extension
                            String javaCode = new String(Files.readAllBytes(file.toPath()));

                            // Evaluate using all added JavaClassEvaluator instances
                            for (JavaClassEvaluator evaluator : javaClassEvaluators) {
                                evaluator.evaluate(javaCode);
                            }

                            // Mark the file as processed
                            processedFiles.add(destFilePath);

                            // Store the student folder path
                            studentFolderPaths.add(file.getParent());
                        }
                    }
                }

                in.closeEntry();
                entry = in.getNextEntry();
            }
        }

        // Add evaluation results to the overall report
        evaluationResults.addResults(getAllEvaluationResults());
    }

    // Helper method to gather all evaluation results from added evaluators
    private List<String> getAllEvaluationResults() {
        List<String> allResults = new ArrayList<>();
        for (JavaClassEvaluator evaluator : javaClassEvaluators) {
            allResults.addAll(evaluator.getEvaluationResults());
        }
        return allResults;
    }

    // Getter for student folder paths
    public List<String> getStudentFolderPaths() {
        return studentFolderPaths;
    }
}
