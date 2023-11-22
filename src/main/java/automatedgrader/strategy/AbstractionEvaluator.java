package automatedgrader.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractionEvaluator implements JavaClassEvaluator {
    private List<String> evaluationResults;

    public AbstractionEvaluator(List<String> evaluationResults) {
        this.evaluationResults = evaluationResults;
    }

    @Override
    public void evaluate(String javaCode) {
        checkAbstractClasses(javaCode);
        checkAbstractMethods(javaCode);
    }

    private void checkAbstractClasses(String javaCode) {
        Pattern abstractClassPattern = Pattern.compile("\\babstract\\s+class\\s+([A-Z][a-zA-Z0-9]*)\\b");
        Matcher abstractClassMatcher = abstractClassPattern.matcher(javaCode);

        while (abstractClassMatcher.find()) {
            String abstractClassName = abstractClassMatcher.group(1);
            evaluationResults.add("Abstract class found: " + abstractClassName);
        }
    }

    private void checkAbstractMethods(String javaCode) {
        Pattern abstractMethodPattern = Pattern.compile("\\babstract\\s+([a-z][a-zA-Z0-9]*)\\s*\\(");
        Matcher abstractMethodMatcher = abstractMethodPattern.matcher(javaCode);

        while (abstractMethodMatcher.find()) {
            String abstractMethodName = abstractMethodMatcher.group(1);
            evaluationResults.add("Abstract method found: " + abstractMethodName);
        }
    }

    @Override
    public List<String> getEvaluationResults() {
        return evaluationResults;
    }
}
