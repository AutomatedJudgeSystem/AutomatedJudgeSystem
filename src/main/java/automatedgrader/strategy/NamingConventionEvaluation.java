package project.strategy;

import java.io.IOException;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamingConventionEvaluation implements EvaluationStrategy {
    @Override
    public void evaluate(String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        checkClassNames(javaCode);
        
        checkMethodNames(javaCode);

        checkAttributeNames(javaCode);
    }

    private void checkClassNames(String javaCode) {
        Pattern classNamePattern = Pattern.compile("\\bclass\\s+([A-Z][a-zA-Z0-9]*)\\b");
        Matcher matcher = classNamePattern.matcher(javaCode);

        while (matcher.find()) {
            String className = matcher.group(1);
            if (!isCamelCase(className)) {
                System.out.println("Class name '" + className + "' does not follow CamelCase convention.");
                // Add corrective feedback or take appropriate action
            }
        }
    }

    private void checkMethodNames(String javaCode) {
        Pattern methodPattern = Pattern.compile("\\b\\w+\\s+(\\w+)\\s*\\(");
        Matcher matcher = methodPattern.matcher(javaCode);

        while (matcher.find()) {
            String methodName = matcher.group(1);
            if (!isCamelCase(methodName)) {
                System.out.println("Method name '" + methodName + "' does not follow CamelCase convention.");
                // Add corrective feedback or take appropriate action
            }
        }
    }

    private void checkAttributeNames(String javaCode) {
        Pattern attributePattern = Pattern.compile("\\b\\w+\\s+(\\w+)\\s*;");
        Matcher matcher = attributePattern.matcher(javaCode);

        while (matcher.find()) {
            String attributeName = matcher.group(1);
            if (!isSnakeCase(attributeName)) {
                System.out.println("Attribute name '" + attributeName + "' does not follow snake_case convention.");
                // Add corrective feedback or take appropriate action
            }
        }
    }

    private boolean isCamelCase(String name) {
        return name.matches("^[a-z]+([A-Z][a-zA-Z0-9]*)*$");
    }

    private boolean isSnakeCase(String name) {
        return name.matches("^[a-z]+(_[a-z]+)*$");
    }

    private String readJavaCodeFromFile(String filePath) {
    try {
        return Files.readString(Paths.get(filePath));
    } catch (IOException e) {
        System.err.println("IOException during reading the file: " + e.getMessage());
        return "";
    }
}
}
