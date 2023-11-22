package automatedgrader.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of an evaluation.
 */
public class EvaluationResult {
    private String testname;
    private int total;
    private String feedback;
    private boolean status;
    private List<String> Results;

    /**
     * Constructs an EvaluationResult with specified parameters.
     *
     * @param testname The name of the test.
     * @param total    The total score or value associated with the test.
     * @param feedback The feedback generated for the test.
     * @param status   The status indicating whether the test passed or not.
     */
    public EvaluationResult(String testname, int total, String feedback, boolean status) {
        this.testname = testname;
        this.total = total;
        this.feedback = feedback;
        this.status = status;
        this.Results = new ArrayList<>();
    }

    /**
     * Adds a result to the list of individual results for the test.
     *
     * @param result The individual result to be added.
     */
    public void addResult(String result) {
        Results.add(result);
    }

    /**
     * Gets all individual results for the test.
     *
     * @return The list of individual results.
     */
    public List<String> getAllResults() {
        return Results;
    }

    /**
     * Gets the name of the test.
     *
     * @return The name of the test.
     */
    public String getTestName() {
        return testname;
    }

    /**
     * Gets the total score or value associated with the test.
     *
     * @return The total score or value.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Gets the status indicating whether the test passed or not.
     *
     * @return The status of the test.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Gets the feedback generated for the test.
     *
     * @return The feedback for the test.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Sets the status of the test.
     *
     * @param status The new status of the test.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Checks if the test is passed based on its status.
     *
     * @param result The EvaluationResult to check.
     * @return True if the test is passed, false otherwise.
     */
    public boolean isPassed() {
        return getStatus();
    }
}