package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BehaviourEvaluation implements EvaluationStrategy {
    @Override
    public void evaluate(String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        checkMethodBehavior(javaCode);
    }

    private void checkMethodBehavior(String javaCode) {
        Pattern methodPattern = Pattern.compile("\\b\\w+\\s+(\\w+)\\s*\\([^\\)]*\\)\\s*\\{([^}]*)\\}");
        Matcher matcher = methodPattern.matcher(javaCode);

        while (matcher.find()) {
            String methodName = matcher.group(1);
            String methodBody = matcher.group(2);

            // Implement your behavior checks here
            if (methodBody.contains("FileInputStream") && !methodBody.contains("try (FileInputStream")) {
                System.out.println("Method '" + methodName + "' does not use try-with-resources for FileInputStream. Consider resource management.");
                // Add corrective feedback or take appropriate action
            }
        }
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
