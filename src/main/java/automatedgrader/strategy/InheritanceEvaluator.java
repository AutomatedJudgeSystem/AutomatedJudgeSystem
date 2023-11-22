package automatedgrader.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InheritanceEvaluator implements JavaClassEvaluator {
    private List<String> evaluationResults;

    public InheritanceEvaluator(List<String> evaluationResults) {
        this.evaluationResults = evaluationResults;
    }

    @Override
    public void evaluate(String javaCode) {
        checkInheritance(javaCode);
    }

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

    @Override
    public List<String> getEvaluationResults() {
        return evaluationResults;
    }
}
