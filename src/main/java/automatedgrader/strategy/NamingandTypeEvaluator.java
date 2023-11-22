package automatedgrader.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamingandTypeEvaluator implements JavaClassEvaluator {
    private List<String> evaluationResults = new ArrayList<>();

    public NamingandTypeEvaluator(List<String> evaluationResults) {
        this.evaluationResults = evaluationResults;
    }

    @Override
    public void evaluate(String javaCode) {
        checkNamingConventions(javaCode);
        checkTypes(javaCode);
    }

    private void checkNamingConventions(String javaCode) {
        // Check if class names follow a specific pattern (e.g., PascalCase)
        Pattern classPattern = Pattern.compile("\\bclass\\s+([A-Z][a-zA-Z0-9]*)\\b");
        Matcher classMatcher = classPattern.matcher(javaCode);

        while (classMatcher.find()) {
            String className = classMatcher.group(1);
            if (!isPascalCase(className)) {
                evaluationResults.add("Class name does not follow naming convention: " + className);
            }
        }

        // Check if method names follow a specific pattern (e.g., camelCase)
        Pattern methodPattern = Pattern.compile("\\b([a-z][a-zA-Z0-9]*)\\s*\\(");
        Matcher methodMatcher = methodPattern.matcher(javaCode);

        while (methodMatcher.find()) {
            String methodName = methodMatcher.group(1);
            if (!isCamelCase(methodName)) {
                evaluationResults.add("Method name does not follow naming convention: " + methodName);
            }
        }

        // Check if variable names follow a specific pattern (e.g., camelCase)
        Pattern variablePattern = Pattern.compile("\\b([a-z][a-zA-Z0-9]*)\\s*[;=]");
        Matcher variableMatcher = variablePattern.matcher(javaCode);

        while (variableMatcher.find()) {
            String variableName = variableMatcher.group(1);
            if (!isCamelCase(variableName)) {
                evaluationResults.add("Variable name does not follow naming convention: " + variableName);
            }
        }
    }

    private void checkTypes(String javaCode) {
        if (javaCode.contains("ArrayList")) {
            evaluationResults.add("Avoid using specific types like ArrayList.");
        }

        if (javaCode.contains("String")) {
            evaluationResults.add("Avoid using specific types like String.");
        }

        if (javaCode.contains("int")) {
            evaluationResults.add("Avoid using specific types like int.");
        }

        if (javaCode.contains("double")) {
            evaluationResults.add("Avoid using specific types like double.");
        }

        if (javaCode.contains("boolean")) {
            evaluationResults.add("Avoid using specific types like boolean.");
        }

        if (javaCode.contains("void")) {
            evaluationResults.add("Avoid using specific types like void.");
        }

        if (javaCode.contains("char")) {
            evaluationResults.add("Avoid using specific types like char.");
        }
        
    }

    public List<String> getEvaluationResults() {
        return evaluationResults;
    }

    private boolean isPascalCase(String name) {
        // Check if a name follows PascalCase convention
        return Character.isUpperCase(name.charAt(0)) && !name.contains("_") && !name.contains(" ");
    }

    private boolean isCamelCase(String name) {
        // Check if a name follows camelCase convention
        return Character.isLowerCase(name.charAt(0)) && !name.contains("_") && !name.contains(" ");
    }
}
