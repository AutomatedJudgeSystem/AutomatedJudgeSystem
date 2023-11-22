// package automatedgrader;

// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// import automatedgrader.observer.PDFGenerator;
// import automatedgrader.observer.PDFObserver;
// import automatedgrader.observer.Submission;
// import automatedgrader.strategy.*;
// import automatedgrader.template.NestedZipFileHandler;

// public class App {
//     public static void main(String[] args) {
//         NestedZipFileHandler fileExtractor = new NestedZipFileHandler();
//         String zipFilePath = "files\\submissions.zip";

//         try {
//             fileExtractor.handleFile(zipFilePath);
//         } catch (IOException e) {
//             handleIOException(e);
//         }

//         ArrayList<Submission> submissionsList = createSubmissionsList();

//         evaluateSubmissions(submissionsList);

//         try {
//              generatePDFReports(submissionsList);
//         } catch (Exception e) {
//             handlePDFGenerationException(e);
//         }
//     }  
   

//     private static void handleIOException(IOException e) {
//         System.err.println("Unexpected IOException: " + e.getMessage());
//         e.printStackTrace();
//     }

//     private static ArrayList<Submission> createSubmissionsList() {
//         ArrayList<Submission> submissionsList = new ArrayList<>();
//         File submissionsFolder = new File("files\\submissions");
//         File[] submissionFolders = submissionsFolder.listFiles();

//         for (File submissionFolder : submissionFolders) {
//             String studentName;
//             String studentId;
//             int assignmentNumber;
//             String fileName;
//             String filePath;

//             String[] splitFileName = submissionFolder.getName().split("_");
//             if (splitFileName.length >= 4) {
//                 studentName = splitFileName[0] + " " + splitFileName[1];
//                 studentId = splitFileName[2];
//                 assignmentNumber = Integer.parseInt(splitFileName[3].substring(1));

//                 File[] submissionFiles = submissionFolder.listFiles();
//                 if (submissionFiles != null && submissionFiles.length > 0) {
//                     fileName = submissionFiles[0].getName();
//                     filePath = submissionFiles[0].getAbsolutePath();

//                     Submission s = new Submission(studentName, studentId, assignmentNumber, fileName, filePath);
//                     PDFObserver pdfObserver = new PDFGenerator();
//                     s.attachObserver(pdfObserver);
//                     submissionsList.add(s);
//                 }
//             }
//         }

//         return submissionsList;
//     }
  
//     private static void evaluateSubmissions(ArrayList<Submission> submissionsList) {
//         CalculationStrategy calculationStrategy = new LuggageSlipCalculationStrategy();
//         AssignmentEvaluator evaluator = new AssignmentEvaluator(calculationStrategy);

//         for (Submission submission : submissionsList) {
//             File submissionFile = new File(submission.getFilePath());
//             if (submissionFile.getName().endsWith(".java")) {
//                 System.out.println(submissionFile.getName());
//                 evaluator.evaluateAssignment(submissionFile.getAbsolutePath());
//                 System.out.println("");
//             }
//         }
//     }

//     private static void generatePDFReports(ArrayList<Submission> submissionsList) {
//         for (Submission submission : submissionsList) {
//             String studentId = submission.getStudentId();
//             String studentFolderPath = submission.getFilePath();
            
    
//             List<String> evaluationResults = new ArrayList<>();  // Replace this with actual evaluation results
    
//             try {
//                 PDFGenerator pdfGenerator = new PDFGenerator();
//                 pdfGenerator.updatePDF(studentId, evaluationResults, studentFolderPath);
//             } catch (IOException e) {
//                 handlePDFGenerationException(e);
//             }
//         }
//     }
    
//     private static void handlePDFGenerationException(Exception e) {
//         e.printStackTrace();
//         // Handle the exception according to your needs
//     }

// }



    // private static List<CalculationStrategy> getCalculationStrategies() {
    //     // Instantiate your CalculationStrategy classes here and return the list
    //     List<CalculationStrategy> strategies = new ArrayList<>();
    //     strategies.add(new FlightCalculationStrategy());
    //     strategies.add(new LuggageManifestCalculationStrategy());
    //     strategies.add(new LuggageSlipCalculationStrategy());
    //     strategies.add(new PassengerCalculationStrategy());

    //     return strategies;
    // }


// package automatedgrader;

// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// import automatedgrader.observer.PDFGenerator;
// import automatedgrader.observer.PDFObserver;
// import automatedgrader.observer.Submission;
// import automatedgrader.strategy.*;
// import automatedgrader.template.NestedZipFileHandler;

// public class App {
//     public static void main(String[] args) {
//         NestedZipFileHandler fileExtractor = new NestedZipFileHandler();
//         String zipFilePath = "files\\submissions.zip";

//         try {
//             fileExtractor.handleFile(zipFilePath);
//         } catch (IOException e) {
//             handleIOException(e);
//         }

//         ArrayList<Submission> submissionsList = createSubmissionsList();

//         evaluateSubmissions(submissionsList);

//         try {
//             generatePDFReports(submissionsList);
//         } catch (Exception e) {
//             handlePDFGenerationException(e);
//         }
//     }

//     private static void handleIOException(IOException e) {
//         System.err.println("Unexpected IOException: " + e.getMessage());
//         e.printStackTrace();
//     }

//     private static ArrayList<Submission> createSubmissionsList() {
//         ArrayList<Submission> submissionsList = new ArrayList<>();
//         File submissionsFolder = new File("files\\submissions");
//         File[] submissionFolders = submissionsFolder.listFiles();

//         for (File submissionFolder : submissionFolders) {
//             String studentName;
//             String studentId;
//             int assignmentNumber;
//             String fileName;
//             String filePath;

//             String[] splitFileName = submissionFolder.getName().split("_");
//             if (splitFileName.length >= 4) {
//                 studentName = splitFileName[0] + " " + splitFileName[1];
//                 studentId = splitFileName[2];
//                 assignmentNumber = Integer.parseInt(splitFileName[3].substring(1));

//                 File[] submissionFiles = submissionFolder.listFiles();
//                 if (submissionFiles != null && submissionFiles.length > 0) {
//                     fileName = submissionFiles[0].getName();
//                     filePath = submissionFiles[0].getAbsolutePath();

//                     Submission s = new Submission(studentName, studentId, assignmentNumber, fileName, filePath);
//                     PDFObserver pdfObserver = new PDFGenerator();
//                     s.attachObserver(pdfObserver);
//                     submissionsList.add(s);
//                 }
//             }
//         }

//         return submissionsList;
//     }

//     private static void evaluateSubmissions(ArrayList<Submission> submissionsList) {
//         CalculationStrategy calculationStrategy = new LuggageSlipCalculationStrategy();
//         AssignmentEvaluator evaluator = new AssignmentEvaluator(calculationStrategy);

//         for (Submission submission : submissionsList) {
//             File submissionFile = new File(submission.getFilePath());
//             if (submissionFile.getName().endsWith(".java")) {
//                 System.out.println(submissionFile.getName());
//                 evaluator.evaluateAssignment(submissionFile.getAbsolutePath());
//                 System.out.println("");
//             }
//         }
//     }

//     private static void generatePDFReports(ArrayList<Submission> submissionsList) {
//         for (Submission submission : submissionsList) {
//             String studentId = submission.getStudentId();
//             String studentFolderPath = submission.getFilePath();

//             List<String> evaluationResults = new ArrayList<>();  // Replace this with actual evaluation results

//             // Check if there are evaluation results before generating PDF
//             if (!evaluationResults.isEmpty()) {
//                 try {
//                     PDFGenerator pdfGenerator = new PDFGenerator();
//                     pdfGenerator.updatePDF(studentId, evaluationResults, studentFolderPath);
//                 } catch (IOException e) {
//                     handlePDFGenerationException(e);
//                 }
//             } else {
//                 System.out.println("No evaluation results for " + studentId + ". Skipping PDF generation.");
//             }
//         }
//     }

//     private static void handlePDFGenerationException(Exception e) {
//         e.printStackTrace();
//         // Handle the exception according to your needs
//     }
// }


package automatedgrader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automatedgrader.observer.PDFGenerator;
import automatedgrader.observer.PDFObserver;
import automatedgrader.observer.Submission;
import automatedgrader.strategy.*;
import automatedgrader.template.NestedZipFileHandler;

public class App {
    public static void main(String[] args) {
        NestedZipFileHandler fileExtractor = new NestedZipFileHandler();
        String zipFilePath = "files\\submissions.zip";

        try {
            fileExtractor.handleFile(zipFilePath);
        } catch (IOException e) {
            handleIOException(e);
        }

        ArrayList<Submission> submissionsList = createSubmissionsList();

        List<List<String>> evaluationResultsList = evaluateSubmissions(submissionsList);

        try {
            generatePDFReports(submissionsList, evaluationResultsList);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void handleIOException(IOException e) {
        System.err.println("Unexpected IOException: " + e.getMessage());
        e.printStackTrace();
    }

    private static ArrayList<Submission> createSubmissionsList() {
        ArrayList<Submission> submissionsList = new ArrayList<>();
        File submissionsFolder = new File("files\\submissions");
        File[] submissionFolders = submissionsFolder.listFiles();

        for (File submissionFolder : submissionFolders) {
            String studentName;
            String studentId;
            int assignmentNumber;
            String fileName;
            String filePath;

            String[] splitFileName = submissionFolder.getName().split("_");
            if (splitFileName.length >= 4) {
                studentName = splitFileName[0] + " " + splitFileName[1];
                studentId = splitFileName[2];
                assignmentNumber = Integer.parseInt(splitFileName[3].substring(1));

                File[] submissionFiles = submissionFolder.listFiles();
                if (submissionFiles != null && submissionFiles.length > 0) {
                    fileName = submissionFiles[0].getName();
                    filePath = submissionFiles[0].getAbsolutePath();

                    Submission s = new Submission(studentName, studentId, assignmentNumber, fileName, filePath);
                    PDFObserver pdfObserver = new PDFGenerator();
                    s.attachObserver(pdfObserver);
                    submissionsList.add(s);
                }
            }
        }

        return submissionsList;
    }

    private static List<List<String>> evaluateSubmissions(ArrayList<Submission> submissionsList) {
        List<List<String>> evaluationResultsList = new ArrayList<>();

        for (Submission submission : submissionsList) {
            File submissionFile = new File(submission.getFilePath());
            if (submissionFile.getName().endsWith(".java")) {
                System.out.println(submissionFile.getName());

                List<String> evaluationResults = new ArrayList<>();
                // Instantiate and evaluate each CalculationStrategy
                for (CalculationStrategy strategy : getCalculationStrategies()) {
                    AssignmentEvaluator evaluator = new AssignmentEvaluator(strategy);
                    String result = evaluator.evaluateAssignment(submissionFile.getAbsolutePath());
                    evaluationResults.add(result);
                }

                System.out.println("");

                evaluationResultsList.add(evaluationResults);
            }
        }

        return evaluationResultsList;
    }

    private static void generatePDFReports(ArrayList<Submission> submissionsList, List<List<String>> evaluationResultsList) {
        int submissionIndex = 0;

        for (Submission submission : submissionsList) {
            String studentId = submission.getStudentId();
            String studentFolderPath = submission.getFilePath();

            // Check if evaluationResultsList has more elements
            if (submissionIndex < evaluationResultsList.size()) {
                List<String> evaluationResults = evaluationResultsList.get(submissionIndex);

                // Check if there are evaluation results before generating PDF
                if (!evaluationResults.isEmpty()) {
                    try {
                        PDFGenerator pdfGenerator = new PDFGenerator();
                        pdfGenerator.updatePDF(studentId, evaluationResults, studentFolderPath);
                    } catch (IOException e) {
                        handleException(e);
                    }
                } else {
                    System.out.println("No evaluation results for " + studentId + ". Skipping PDF generation.");
                }

                submissionIndex++;
            }
        }
    }

    private static void handleException(Exception e) {
        System.err.println("Unexpected Exception: " + e.getMessage());
        e.printStackTrace();
    }

    private static List<CalculationStrategy> getCalculationStrategies() {
        // Instantiate your CalculationStrategy classes here and return the list
        List<CalculationStrategy> strategies = new ArrayList<>();
        strategies.add(new FlightCalculationStrategy());
        strategies.add(new LuggageManifestCalculationStrategy());
        strategies.add(new LuggageSlipCalculationStrategy());
        strategies.add(new PassengerCalculationStrategy());

        return strategies;
    }
}
