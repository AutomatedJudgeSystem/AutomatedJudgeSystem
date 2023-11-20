package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements the EvaluationStrategy interface for evaluating return types in Java code.
 */
public class ReturnTypeEvaluation implements EvaluationStrategy {
    /**
     * Evaluates the return types of methods in the specified Java file.
     *
     * @param filePath The path of the Java file to evaluate.
     */
    @Override
    public void evaluate(String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        checkReturnTypes(javaCode);
    }

    /**
     * Checks the return types of methods in the provided Java code and prints feedback.
     *
     * @param javaCode The Java code to analyze.
     */
    private void checkReturnTypes(String javaCode) {
        Pattern returnTypePattern = Pattern.compile("\\b(\\w+)\\s+(\\w+)\\s*\\([^\\)]*\\)\\s*\\{");
        Matcher matcher = returnTypePattern.matcher(javaCode);

        while (matcher.find()) {
            String returnType = matcher.group(1);
            String methodName = matcher.group(2);

            // Implement your return type checks here
            if (!returnType.equals("void")) {
                System.out.println("Method '" + methodName + "' has a non-void return type.");
                // Add corrective feedback or take appropriate action
            }

            if(returnType.equals("boolean")){
                System.out.println("Method '" + methodName + "' has a boolean return type.");
            }

            if(returnType.equals("String")){
                System.out.println("Method '" + methodName + "' has a String return type.");
            }

            if(returnType.equals("int")){
                System.out.println("Method '" + methodName + "' has a int return type.");
            }

            if(returnType.equals("char")){
                System.out.println("Method '" + methodName + "' has a char return type.");
            }

            if(returnType.equals("double")){
                System.out.println("Method '" + methodName + "' has a double return type.");
            }
        }
    }

    /**
     * Reads the content of a Java file.
     *
     * @param filePath The path of the Java file to read.
     * @return The content of the Java file as a string.
     */
    private String readJavaCodeFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("IOException during reading the file: " + e.getMessage());
            return "";
        }
    }
}
