package automatedgrader.strategy;

import java.util.ArrayList;
import java.util.List;

public class EvaluationResults {
    private List<String> allResults = new ArrayList<>();

    public void addResults(List<String> results) {
        allResults.addAll(results);
    }

    public List<String> getAllResults() {
        return allResults;
    }
}
