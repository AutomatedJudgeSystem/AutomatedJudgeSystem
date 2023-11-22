package automatedgrader.strategy;

import java.util.HashMap;
import java.util.Map;

public class ScoreStrategyFactory {
    private Map<String, ScoreCalculationStrategy> strategies;

    public ScoreStrategyFactory(String studentFolderPath) {
        strategies = new HashMap<>();
        // Initialize strategies based on the classes or criteria you want to evaluate
        strategies.put("Passenger", new PassengerScoreStrategy(studentFolderPath));
        strategies.put("LuggageSlip", new LuggageSlipScoreStrategy(studentFolderPath));
        strategies.put("LuggageManifest", new LuggageManifestScoreStrategy(studentFolderPath));
        strategies.put("Flight", new FlightScoreStrategy(studentFolderPath));
       // strategies.put("LuggageManagementSystem", new LuggageManagementSystemScoreStrategy(studentFolderPath));
    }

    public ScoreCalculationStrategy getStrategy(String className) {
        return strategies.get(className);
    }
}
