package automatedgrader.observer;

import automatedgrader.PDFGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestResultObserver implements TestObserver {
    private List<String> testResults = new ArrayList<>();
    private String studentId;
    private String studentFolderPath;

    // Updated constructor to take both studentId and studentFolderPath
    public TestResultObserver(String studentId, String studentFolderPath) {
        this.studentId = studentId;
        this.studentFolderPath = studentFolderPath;
    }

    @Override
    public void update(String className, boolean passed, double score, String feedback) {
        String result = "Class: " + className + " | Passed: " + passed + " | Feedback: " + feedback;
        testResults.add(result);
    }

    // Updated method to generate PDF report with the correct parameters
    public void generatePDFReport(List<String> evaluationResults) {
        try {
            PDFGenerator.generateReport(studentId, evaluationResults, studentFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }

    // Getter for test results
    public List<String> getTestResults() {
        return testResults;
    }
}
