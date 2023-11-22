package automatedgrader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PDFGeneratorTest {

    @Test
    void generateReportTestCorrect() throws IOException {
        String studentId = "123456";
        List<String> testResults = Arrays.asList("Test 1: Passed", "Test 2: Failed", "Test 3: Passed");
        String studentFolderPath = "path/to/student/folder/";

        PDFGenerator.generateReport(studentId, testResults, studentFolderPath);

        Path pdfPath = Path.of(studentFolderPath, studentId + "SubmissionPDFReport.pdf");
        assertTrue(Files.exists(pdfPath), "PDF file should exist");
    }

    @Test
    void generateReportTestPartial() throws IOException {
        String studentId = "987654";
        String studentFolderPath = "path/to/student/folder/";

        try {
            PDFGenerator.generateReport(studentId, Collections.emptyList(), studentFolderPath);

            Path pdfPath = Path.of(studentFolderPath, studentId + "SubmissionPDFReport.pdf");
            assertFilesExist(pdfPath);
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    private void assertFilesExist(Path... paths) {
        for (Path path : paths) {
            assertTrue(Files.exists(path), "File should exist: " + path);
        }
    }

    @Test
    void generateTestIncorrect() throws IOException {
        //Incorrect test data
        String studentId = "INVALID_ID";
        String studentFolderPath = "invalid/path/to/student/folder/";

        Files.createDirectories(Path.of(studentFolderPath));

        PDFGenerator.generateReport(studentId, Collections.emptyList(), studentFolderPath);

        Path pdfPath = Path.of(studentFolderPath, studentId + "SubmissionPDFReports.pdf");
        assertFileDoesNotExist(pdfPath);
    }

    private void assertFileDoesNotExist(Path path) {
        assertFalse(Files.exists(path), "File should not exist: " + path);
    }
}
