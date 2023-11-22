package automatedgrader.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * The `EvaluationResult` class represents the results of the evaluation process.
 */
public class EvaluationResult {

    private List<String> allResults = new ArrayList<>();

    /**
     * Adds the specified list of results to the overall results.
     *
     * @param results The list of results to be added.
     */
    public void addResults(List<String> results) {
        allResults.addAll(results);
    }

    /**
     * Retrieves all the results accumulated during the evaluation process.
     *
     * @return The list of all evaluation results.
     */
    public List<String> getAllResults() {
        return allResults;
    }
}
