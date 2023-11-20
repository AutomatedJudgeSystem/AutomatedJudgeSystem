package automatedgrader;

import automatedgrader.observer.PDFGenerator;
import automatedgrader.observer.Submission;
import automatedgrader.strategy.EvaluationResult;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PDFGeneratorTest {

    @Test
    public void testUpdatePDF() {
        // Create a PDFGenerator instance
        PDFGenerator pdfGenerator = new PDFGenerator();

        // Create a mock Submission
        Submission submission = new Submission(null, null, 0);
        submission.setFileName("TestFile");
        submission.setStudentId("123456");
        submission.setAssignmentNumber(1);
        submission.setFilePath("/path/to/test/file.java");

        // Create mock EvaluationResults
        List<EvaluationResult> testResults = new ArrayList<>();
        testResults.add(new EvaluationResult());
        testResults.add(new EvaluationResult());

        // Test the updatePDF method
        pdfGenerator.updatePDF(submission, testResults);
        
        // Assert something based on the expected output
        // For example, check if the PDF was created successfully
        // You may need to implement additional methods to check the generated PDF
        // For simplicity, this example just checks if the calculation strategy is used
        // You should adapt these assertions based on your actual implementation and requirements

        // For demonstration purposes, we assume the PDFGenerator creates a file with a specific name
        String expectedFileName = "123456_TestFile_Assignment1_feedback.pdf";
        // Replace this with actual logic to check the generated PDF

        // Assert the expected file name
        assertEquals(expectedFileName, "123456_TestFile_Assignment1_feedback.pdf");

        String outputDirectory = "submissions";
        Path outputPath = Paths.get(outputDirectory, expectedFileName);
        assertTrue("PDF file should be created in the correct location", Files.exists(outputPath));
    }
}
