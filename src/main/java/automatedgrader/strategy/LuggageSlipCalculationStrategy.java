package automatedgrader.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents a calculation strategy for evaluating LuggageSlip class implementations.
 */
public class LuggageSlipCalculationStrategy implements CalculationStrategy {
    private boolean attributesPassed = false;
    private boolean constructorPassed = false;
    private boolean methodsPassed = false;

    /**
     * Calculates a score based on the content of a file representing a LuggageSlip class.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The calculated score.
     */
    @Override
    public int calculate(String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);
        int score = 0;

        score += checkAttributes(javaCode);
        score += checkConstructors(javaCode);
        score += checkMethods(javaCode);

        return score;
    }

    /**
     * Creates an EvaluationResult for the Luggage Slip Test based on the content of a file.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The EvaluationResult created.
     */
    @Override
    public EvaluationResult createResult(String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        String testname = "Luggage Slip Test";
        int total = calculate(filePath);
        String feedback = "Attribute marks: " + checkAttributes(javaCode) + "\n Constructor marks: " +
                checkConstructors(javaCode) + "/n Other Method marks: " + checkMethods(javaCode);
        boolean status = false;

        return new EvaluationResult(testname, total, feedback, status);
    }

    /**
     * Notifies that the Luggage Slip Test has passed for the given EvaluationResult.
     *
     * @param evaluationResult The EvaluationResult indicating a test pass.
     */
    @Override
    public void testPassed(EvaluationResult evaluationResult) {
        if (attributesPassed && constructorPassed && methodsPassed) {
            evaluationResult.setStatus(true);
        }
    }

    /**
     * Generates feedback based on the content of a file representing a LuggageSlip class.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The generated feedback.
     */
    @Override
    public String generateFeedback(String filePath) {
        EvaluationResult evaluationResult = createResult(filePath);

        String result = "TEST NAME: " + evaluationResult.getTestName() + "/n";
        testPassed(evaluationResult);
        result += "TEST STATUS: " + evaluationResult.isPassed() + "/n" +
                "FEEDBACK ______________________________________________________/n"
                + evaluationResult.getFeedback() + "/n" + "SCORE OUT OF 14: " +
                evaluationResult.getTotal() + "/n/n";

        evaluationResult.addResult(result);
        return result;
    }

    /**
     * Reads the content of a Java code file.
     *
     * @param filePath The path to the file to be read.
     * @return The content of the file as a String.
     */
    public String readJavaCodeFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("IOException during reading the file: " + e.getMessage());
            return "";
        }
    }

    /**
     * Checks the attributes of the LuggageSlip class in the provided Java code.
     *
     * @param javaCode The Java code representing the LuggageSlip class.
     * @return The score for attributes.
     */
    public int checkAttributes(String javaCode) {
        int attributeScore = 0;

        String[] expectedAttributeTypes = {"Passenger", "int", "String", "String"};
        String[] attributes = {"owner", "luggageSlipIDCounter", "luggageSlipID", "label"};

        for (int i = 0; i < attributes.length; i++) {
            String attribute = attributes[i];
            String expectedType = expectedAttributeTypes[i];

            // Pattern to match the attribute declaration with the expected type
            Pattern attributePattern = Pattern.compile("\\bprivate\\s+" + expectedType + "\\s+" + attribute + "\\s*;");
            Matcher matcher = attributePattern.matcher(javaCode);

            if (matcher.find()) {
                attributeScore += 1;
                attributesPassed = true;

            } else {
                System.out.println("Attribute '" + attribute + "' does not meet the criteria.");
            }
        }
        return attributeScore;
    }

    /**
     * Checks the constructors of the LuggageSlip class in the provided Java code.
     *
     * @param javaCode The Java code representing the LuggageSlip class.
     * @return The score for constructors.
     */
    public int checkConstructors(String javaCode) {
        int constructorScore = 0;
        boolean constructorPassed1 = false;
        boolean constructorPassed2 = false;

        // Check LuggageSlip(Passenger p, Flight f) constructor
        Pattern constructorPattern1 = Pattern.compile("public\\s+LuggageSlip\\(Passenger\\s+p,\\s+Flight\\s+f\\)\\s*\\{([^}]*)\\}");
        Matcher matcher1 = constructorPattern1.matcher(javaCode);

        if (matcher1.find()) {
            constructorScore += 3; // Full marks for LuggageSlip(Passenger p, Flight f) constructor
            constructorPassed1 = true;
        } else {
            System.out.println("LuggageSlip(Passenger p, Flight f) constructor not found.");
        }

        // Check LuggageSlip(Passenger p, Flight f, String label) constructor
        Pattern constructorPattern2 = Pattern.compile("public\\s+LuggageSlip\\(Passenger\\s+p,\\s+Flight\\s+f,\\s+String\\s+label\\)\\s*\\{([^}]*)\\}");
        Matcher matcher2 = constructorPattern2.matcher(javaCode);

        if (matcher2.find()) {
            constructorScore += 3; // Full marks for LuggageSlip(Passenger p, Flight f, String label) constructor
            constructorPassed2 = true;
        } else {
            System.out.println("LuggageSlip(Passenger p, Flight f, String label) constructor not found.");
            // Add corrective feedback or take appropriate action
        }

        if (constructorPassed1 && constructorPassed2) {
            constructorPassed = true;
        }

        return constructorScore;
    }

    /**
     * Checks the methods of the LuggageSlip class in the provided Java code.
     *
     * @param javaCode The Java code representing the LuggageSlip class.
     * @return The score for methods.
     */
    public int checkMethods(String javaCode) {
        int methodScore = 0;
        boolean hasOwnerPassed = false;
        boolean toStringPassed = false;

        // Check hasOwner(String passportNumber) method
        Pattern hasOwnerPattern = Pattern.compile("public\\s+boolean\\s+hasOwner\\(String\\s+passportNumber\\)\\s*\\{([^}]*)\\}");
        Matcher hasOwnerMatcher = hasOwnerPattern.matcher(javaCode);

        if (hasOwnerMatcher.find()) {
            methodScore += 2; // Full marks for hasOwner(String passportNumber) method
            hasOwnerPassed = true;
        } else {
            System.out.println("hasOwner(String passportNumber) method not found.");

        }

        // Check toString() method
        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{([^}]*)\\}");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);

        if (toStringMatcher.find()) {
            methodScore += 2; // Full marks for toString() method
            toStringPassed = true;
        } else {
            System.out.println("toString() method not found.");
        }

        if (hasOwnerPassed && toStringPassed) {
            methodsPassed = true;
        }
        return methodScore;
    }
}
