package automatedgrader.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The `InheritanceEvaluator` class is an implementation of the `JavaClassEvaluator` interface.
 * It checks for extends/implements relationships in the provided Java code.
 */
public class InheritanceEvaluator implements JavaClassEvaluator {
    private List<String> evaluationResults;

    /**
     * Constructs an `InheritanceEvaluator` object.
     *
     * @param evaluationResults The list to store evaluation results.
     */
    public InheritanceEvaluator(List<String> evaluationResults) {
        this.evaluationResults = evaluationResults;
    }

    /**
     * Evaluates the provided Java code for inheritance relationships.
     *
     * @param javaCode The Java code to evaluate.
     */
    @Override
    public void evaluate(String javaCode) {
        checkInheritance(javaCode);
    }

    /**
     * Checks for extends/implements relationships in the provided Java code.
     *
     * @param javaCode The Java code to check for inheritance relationships.
     */
    private void checkInheritance(String javaCode) {
        // Check if there are extends/implements relationships
        Pattern inheritancePattern = Pattern.compile("\\bclass\\s+([A-Z][a-zA-Z0-9]*)\\s*\\b(?:extends|implements)\\s*([A-Z][a-zA-Z0-9]*)");
        Matcher inheritanceMatcher = inheritancePattern.matcher(javaCode);

        while (inheritanceMatcher.find()) {
            String className = inheritanceMatcher.group(1);
            String superClassOrInterface = inheritanceMatcher.group(2);
            evaluationResults.add("Class '" + className + "' extends/implements: " + superClassOrInterface);
        }
    }

    /**
     * Gets the list of evaluation results.
     *
     * @return The list of evaluation results.
     */
    @Override
    public List<String> getEvaluationResults() {
        return evaluationResults;
    }
}
