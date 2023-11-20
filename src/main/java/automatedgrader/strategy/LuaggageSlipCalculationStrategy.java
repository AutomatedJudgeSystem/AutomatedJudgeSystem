package automatedgrader.strategy;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LuaggageSlipCalculationStrategy implements CalculationStrategy{

    public int calculate(String filePath) {

        String javaCode = readJavaCodeFromFile(filePath);
        int score = 0;

        score += checkAttributeTypes(javaCode);
        score += checkConstructors(javaCode);
        score += checkMethods(javaCode);

        return score;
    }

    private String readJavaCodeFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("IOException during reading the file: " + e.getMessage());
            return "";
        }
    }

    private int checkAttributeTypes(String javaCode) {
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
            } else {
                System.out.println("Attribute '" + attribute + "' does not meet the criteria.");
               
            }
        }
        return attributeScore;
    }

    private int checkConstructors(String javaCode) {
        int constructorScore = 0;

        // Check LuggageSlip(Passenger p, Flight f) constructor
        Pattern constructorPattern1 = Pattern.compile("public\\s+LuggageSlip\\(Passenger\\s+p,\\s+Flight\\s+f\\)\\s*\\{([^}]*)\\}");
        Matcher matcher1 = constructorPattern1.matcher(javaCode);

        if (matcher1.find()) {
            constructorScore += 3; // Full marks 
        } else {
            System.out.println("LuggageSlip(Passenger p, Flight f) constructor not found.");
        }

        // Check LuggageSlip(Passenger p, Flight f, String label) constructor
        Pattern constructorPattern2 = Pattern.compile("public\\s+LuggageSlip\\(Passenger\\s+p,\\s+Flight\\s+f,\\s+String\\s+label\\)\\s*\\{([^}]*)\\}");
        Matcher matcher2 = constructorPattern2.matcher(javaCode);

        if (matcher2.find()) {
            constructorScore += 3; // Full marks 
        } else {
            System.out.println("LuggageSlip(Passenger p, Flight f, String label) constructor not found.");

        }

        return constructorScore;
    }

    private int checkMethods(String javaCode) {
        int methodScore = 0;

        // Check hasOwner(String passportNumber) method
        Pattern hasOwnerPattern = Pattern.compile("public\\s+boolean\\s+hasOwner\\(String\\s+passportNumber\\)\\s*\\{([^}]*)\\}");
        Matcher hasOwnerMatcher = hasOwnerPattern.matcher(javaCode);

        if (hasOwnerMatcher.find()) {
            methodScore += 2; // Full marks 
        } else {
            System.out.println("hasOwner(String passportNumber) method not found.");
           
        }

        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{([^}]*)\\}");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);

        if (toStringMatcher.find()) {
            methodScore += 2; // Full marks
        } else {
            System.out.println("toString() method not found.");
        }
        return methodScore;
    }
}
