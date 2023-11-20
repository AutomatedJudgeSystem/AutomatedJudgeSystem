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
}
