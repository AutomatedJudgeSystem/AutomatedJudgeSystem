package automatedgrader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//import projectv2.observer.TestResultObserver;
import automatedgrader.strategy.AbstractionEvaluator;
import automatedgrader.strategy.EvaluationResults;
import automatedgrader.strategy.InheritanceEvaluator;
import automatedgrader.strategy.NamingandTypeEvaluator;
import automatedgrader.template.NestedZipFileHandler;

public class App {
    public static void main(String[] args) {
        // Specify the path to the main zip file containing nested submissions
        String mainZipFilePath = "files/submissions.zip";

        try {
            EvaluationResults evaluationResults = new EvaluationResults();

            List<String> namingAndTypeResults = new ArrayList<>();
            NamingandTypeEvaluator namingAndTypeEvaluator = new NamingandTypeEvaluator(namingAndTypeResults);

            List<String> inheritanceResults = new ArrayList<>();
            InheritanceEvaluator inheritanceEvaluator = new InheritanceEvaluator(inheritanceResults);

            List<String> abstractionResults = new ArrayList<>();
            AbstractionEvaluator abstractionEvaluator = new AbstractionEvaluator(abstractionResults);

            NestedZipFileHandler fileExtractor = new NestedZipFileHandler(evaluationResults);

            // Set evaluators for the file extractor
            fileExtractor.addJavaClassEvaluator(namingAndTypeEvaluator);
            fileExtractor.addJavaClassEvaluator(inheritanceEvaluator);
            fileExtractor.addJavaClassEvaluator(abstractionEvaluator);

            // Execute the extraction process
            fileExtractor.handleFile(mainZipFilePath);

            // Get and print all evaluation results
            List<String> allResults = evaluationResults.getAllResults();
            for (String result : allResults) {
                System.out.println(result);
            }

            // Integrate the test and PDF generation logic here based on the evaluation results
            double namingAndTypeScore = calculateScoreBasedOnResults(namingAndTypeResults);
            double inheritanceScore = calculateScoreBasedOnResults(inheritanceResults);
            double abstractionScore = calculateScoreBasedOnResults(abstractionResults);

            // Assuming you have a rubric, adjust the score calculation based on your criteria
            double overallScore = (namingAndTypeScore + inheritanceScore + abstractionScore) / 3;

            System.out.println("Overall Score: " + overallScore);

            // Generate a PDF report for each student
            List<String> studentFolderPaths = fileExtractor.getStudentFolderPaths();
            for (String studentFolderPath : studentFolderPaths) {
                generatePDFReport(studentFolderPath, allResults);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate the score based on the evaluation results
    private static double calculateScoreBasedOnResults(List<String> results) {
        int passedCount = 0;
        int totalCount = results.size();

        for (String result : results) {
            if (result.contains("Pass")) {
                passedCount++;
            }
        }

        return (double) passedCount / totalCount * 100;
    }

    // Method to generate the PDF report
    private static void generatePDFReport(String studentFolderPath, List<String> evaluationResults) {
        String studentId = extractStudentId(studentFolderPath);
        try {
            PDFGenerator.generateReport(studentId, evaluationResults, studentFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }

    // Method to extract student ID from the folder path
    private static String extractStudentId(String studentFolderPath) {
        Path path = Paths.get(studentFolderPath);
        return path.getFileName().toString(); // Assuming the student ID is the folder name
    }
}
