package automatedgrader.observer;

import automatedgrader.PDFGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The `TestResultObserver` class is an implementation of the `TestObserver` interface.
 * It observes and collects test results, generating a PDF report upon request.
 */
public class TestResultObserver implements TestObserver {
    private List<String> testResults = new ArrayList<>();
    private String studentId;
    private String studentFolderPath;

    /**
     * Constructs a new `TestResultObserver` for a specific student.
     *
     * @param studentId          The unique identifier of the student.
     * @param studentFolderPath The path to the folder where student-related files are stored.
     */
    public TestResultObserver(String studentId, String studentFolderPath) {
        this.studentId = studentId;
        this.studentFolderPath = studentFolderPath;
    }

    /**
     * Updates the observer with information about a test result.
     *
     * @param className The name of the class being tested.
     * @param passed    A boolean indicating whether the test passed or not.
     * @param score     The score obtained in the test.
     * @param feedback  Additional feedback or comments about the test.
     */
    @Override
    public void update(String className, boolean passed, double score, String feedback) {
        String result = "Class: " + className + " | Passed: " + passed + " | Feedback: " + feedback;
        testResults.add(result);
    }

    /**
     * Generates a PDF report for the student with the provided evaluation results.
     *
     * @param evaluationResults The list of evaluation results to include in the report.
     */
    public void generatePDFReport(List<String> evaluationResults) {
        try {
            PDFGenerator.generateReport(studentId, evaluationResults, studentFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }

    /**
     * Retrieves the list of test results collected by the observer.
     *
     * @return The list of test results.
     */
    public List<String> getTestResults() {
        return testResults;
    }
}
