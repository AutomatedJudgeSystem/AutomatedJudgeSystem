package automatedgrader.strategy;

/**
 * Represents a calculation strategy for automated grading.
 */
public interface CalculationStrategy {
    
    /**
     * Calculates a score based on the content of a file.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The calculated score.
     */
    int calculate(String filePath);

    /**
     * Creates an EvaluationResult based on the content of a file.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The EvaluationResult created.
     */
    EvaluationResult createResult(String filePath);

    /**
     * Notifies that a test has passed for the given EvaluationResult.
     *
     * @param evaluationResult The EvaluationResult indicating a test pass.
     */
    void testPassed(EvaluationResult evaluationResult);

    /**
     * Generates feedback based on the content of a file.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The generated feedback.
     */
    String generateFeedback(String filePath);
}
