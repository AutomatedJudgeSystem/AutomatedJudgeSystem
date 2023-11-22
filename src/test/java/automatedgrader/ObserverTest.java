// package automatedgrader;


// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// class ObserverTest {

//     @Test
//     void testPDFGeneration() {
//         // Create a sample Submission
//         Submission submission = new Submission("John Doe", "12345", 1, "assignment1.txt", "/path/to/submission");

//         // Create a PDFGenerator observer
//         PDFGenerator pdfGenerator = new PDFGenerator();

//         // Attach the PDFGenerator observer to the Submission
//         submission.attachObserver(pdfGenerator);

//         // Create a list of test results
//         List<String> testResults = new ArrayList<>();
//         testResults.add("Test Result 1: Passed");
//         testResults.add("Test Result 2: Failed");

//         // Notify observers (generate PDF)
//         submission.notifyObservers(testResults);

//         // Check if the PDF file is generated in the correct location
//         String pdfFilePath = "/path/to/submission/12345SubmissionPDFReport.pdf";
//         File pdfFile = new File(pdfFilePath);

//         assertTrue(pdfFile.exists(), "PDF file should exist");
//         assertTrue(pdfFile.isFile(), "Path should point to a file");

//         // Clean up: Delete the generated PDF file
//         assertTrue(pdfFile.delete(), "Failed to delete the generated PDF file");
//     }

//     @Test
//     void testPDFGenerationIOException() {
//         // Create a sample Submission
//         Submission submission = new Submission("Jane Doe", "67890", 2, "assignment2.txt", "/path/to/submission");

//         // Create a PDFGenerator observer
//         PDFObserver pdfGeneratorWithError = new PDFObserver() {
//             @Override
//             public void updatePDF(String studentId, List<String> results, String studentFolderPath) throws IOException {
//                 // Simulate an IOException during PDF generation
//                 throw new IOException("Error generating PDF");
//             }
//         };

//         // Attach the PDFGenerator observer to the Submission
//         submission.attachObserver(pdfGeneratorWithError);

//         // Create a list of test results
//         List<String> testResults = new ArrayList<>();
//         testResults.add("Test Result 3: Error");

//         // Notify observers (generate PDF)
//         assertDoesNotThrow(() -> submission.notifyObservers(testResults), "Exception should not be thrown");
//     }
// }
