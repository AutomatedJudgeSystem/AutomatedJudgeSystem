package automatedgrader.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * The ScoreStrategyFactory class is responsible for creating instances of ScoreCalculationStrategy
 * based on the specified class names. It serves as a central factory for obtaining score calculation strategies.
 */
public class ScoreStrategyFactory {

    /**
     * A map that associates class names with their corresponding ScoreCalculationStrategy instances.
     */
    private Map<String, ScoreCalculationStrategy> strategies;

    /**
     * Constructs a ScoreStrategyFactory with initializations for the available score calculation strategies.
     *
     * @param studentFolderPath The path to the folder containing student-specific class files.
     */
    public ScoreStrategyFactory(String studentFolderPath) {
        strategies = new HashMap<>();
        // Initialize strategies based on the classes or criteria you want to evaluate
        strategies.put("Passenger", new PassengerScoreStrategy(studentFolderPath));
        strategies.put("LuggageSlip", new LuggageSlipScoreStrategy(studentFolderPath));
        strategies.put("LuggageManifest", new LuggageManifestScoreStrategy(studentFolderPath));
        strategies.put("Flight", new FlightScoreStrategy(studentFolderPath));
        // Uncomment and add more strategies as needed
        // strategies.put("LuggageManagementSystem", new LuggageManagementSystemScoreStrategy(studentFolderPath));
    }

    /**
     * Retrieves the ScoreCalculationStrategy associated with the specified class name.
     *
     * @param className The name of the class for which the strategy is requested.
     * @return The ScoreCalculationStrategy instance corresponding to the specified class name.
     */
    public ScoreCalculationStrategy getStrategy(String className) {
        return strategies.get(className);
    }
}

