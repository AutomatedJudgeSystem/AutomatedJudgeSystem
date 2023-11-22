package automatedgrader.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The `AbstractionEvaluator` class implements the `JavaClassEvaluator` interface
 * to evaluate Java code for the presence of abstract classes and methods.
 */
public class AbstractionEvaluator implements JavaClassEvaluator {

    private List<String> evaluationResults;

    /**
     * Constructs a new `AbstractionEvaluator` with the specified list for storing evaluation results.
     *
     * @param evaluationResults The list to store evaluation results.
     */
    public AbstractionEvaluator(List<String> evaluationResults) {
        this.evaluationResults = evaluationResults;
    }

    /**
     * Evaluates the given Java code for the presence of abstract classes and methods.
     *
     * @param javaCode The Java code to be evaluated.
     */
    @Override
    public void evaluate(String javaCode) {
        checkAbstractClasses(javaCode);
        checkAbstractMethods(javaCode);
    }

    /**
     * Checks for the presence of abstract classes in the given Java code.
     *
     * @param javaCode The Java code to be checked.
     */
    private void checkAbstractClasses(String javaCode) {
        Pattern abstractClassPattern = Pattern.compile("\\babstract\\s+class\\s+([A-Z][a-zA-Z0-9]*)\\b");
        Matcher abstractClassMatcher = abstractClassPattern.matcher(javaCode);

        while (abstractClassMatcher.find()) {
            String abstractClassName = abstractClassMatcher.group(1);
            evaluationResults.add("Abstract class found: " + abstractClassName);
        }
    }

    /**
     * Checks for the presence of abstract methods in the given Java code.
     *
     * @param javaCode The Java code to be checked.
     */
    private void checkAbstractMethods(String javaCode) {
        Pattern abstractMethodPattern = Pattern.compile("\\babstract\\s+([a-z][a-zA-Z0-9]*)\\s*\\(");
        Matcher abstractMethodMatcher = abstractMethodPattern.matcher(javaCode);

        while (abstractMethodMatcher.find()) {
            String abstractMethodName = abstractMethodMatcher.group(1);
            evaluationResults.add("Abstract method found: " + abstractMethodName);
        }
    }

    /**
     * Retrieves the list of evaluation results.
     *
     * @return The list of evaluation results.
     */
    @Override
    public List<String> getEvaluationResults() {
        return evaluationResults;
    }
}
