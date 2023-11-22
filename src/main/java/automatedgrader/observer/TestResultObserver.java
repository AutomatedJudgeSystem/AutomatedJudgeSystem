package automatedgrader.observer;

/**
 * TestResultObserver Class
 * 
 * Monitors and stores test results, providing functionality to generate a PDF report.
 * Implements the TestObserver interface.
 * 
 * Class-level:
 * - No additional class-level documentation.
 * 
 * Method-level:
 * - `void update(String className, boolean passed, double score, String feedback)`: 
 *   Overrides the method from TestObserver to update and store test results.
 *   Parameters:
 *   - `className`: Name of the test class.
 *   - `passed`: Indicates whether the test passed.
 *   - `score`: The score achieved by the test.
 *   - `feedback`: Additional feedback or comments about the test.
 * 
 * - `void generatePDFReport(List<String> evaluationResults)`: 
 *   Generates a PDF report using the PDFGenerator class.
 *   Parameters:
 *   - `evaluationResults`: List of evaluation results to include in the report.
 *   Exceptions:
 *   - IOException: Handled internally with a printStackTrace and can be customized.
 * 
 * - `List<String> getTestResults()`: 
 *   Getter method to retrieve the stored test results.
 *   Returns:
 *   - List of Strings containing test results.
 * 
 * Field-level:
 * - `private List<String> testResults`: 
 *   Stores the test results as a list of strings.
 * 
 * - `private String studentId`: 
 *   Stores the student ID associated with the test results.
 * 
 * - `private String studentFolderPath`: 
 *   Stores the folder path for the student's test results.
 */
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
