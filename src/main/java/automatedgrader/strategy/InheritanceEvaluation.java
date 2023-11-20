package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InheritanceEvaluation implements EvaluationStrategy {
    @Override
    public void evaluate(String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        checkInheritance(javaCode);
    }

    private void checkInheritance(String javaCode) {
        Pattern classDeclarationPattern = Pattern.compile("\\bclass\\s+(\\w+)(\\s*extends\\s+(\\w+))?\\s*");
        Matcher matcher = classDeclarationPattern.matcher(javaCode);

        while (matcher.find()) {
            String className = matcher.group(1);
            String extendsKeyword = matcher.group(2);
            String parentClass = matcher.group(3);

            if (extendsKeyword != null && parentClass != null) {
                System.out.println("Class '" + className + "' extends '" + parentClass + "'.");
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
