package automatedgrader.strategy;

/**
 * The ScoreCalculationStrategy interface defines the contract for classes that perform
 * score calculation based on specific rules or strategies.
 */
public interface ScoreCalculationStrategy {

    /**
     * Calculates and returns a score based on the implementation-specific rules or strategy.
     *
     * @return The calculated score.
     */
    double calculateScore();
}

