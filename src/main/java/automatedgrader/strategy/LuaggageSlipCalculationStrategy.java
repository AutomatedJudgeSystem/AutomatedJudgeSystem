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
    
        score += checkAttributeTypes(javaCode, filePath);
        score += checkConstructors(javaCode, filePath);
        score += checkMethods(javaCode, filePath);

        return score;
    }

    public EvaluationResult createResult (String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        String testname= "Luggage Slip Calculation";
        String total = "Total marks earned out of 14: "+ calculate(filePath);
        String feedback = "Total score possible: 14 /n" + "Attribute marks: " +checkAttributeTypes(javaCode, filePath) +"\n Constructor marks: "+
                          checkConstructors(javaCode, filePath) +"/n Other Method marks: "+ checkMethods(javaCode, filePath);
        boolean status = false;

        return new EvaluationResult(testname, total, feedback, status);
    }

    public String readJavaCodeFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("IOException during reading the file: " + e.getMessage());
            return "";
        }
    }

    public int checkAttributeTypes(String javaCode, String filePath){
        int attributeScore = 0;
        EvaluationResult testResult = createResult(filePath);

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
                testResult.setStatus(true);
                
            } else {
                System.out.println("Attribute '" + attribute + "' does not meet the criteria.");
            }
        }
        return attributeScore;
    }

    public int checkConstructors(String javaCode, String filePath){
        int constructorScore = 0;
        EvaluationResult testResult = createResult(filePath);
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

        if (constructorPassed1 && constructorPassed2){
            testResult.setStatus(true);
        }

        return constructorScore;
    }

    public int checkMethods(String javaCode, String filePath){
        int methodScore = 0;
        EvaluationResult testResult = createResult(filePath);
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
            // Add corrective feedback or take appropriate action
        }

        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{([^}]*)\\}");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);

        if (toStringMatcher.find()) {
            methodScore += 2; // Full marks for toString() method
            toStringPassed = true;
        } else {
            System.out.println("toString() method not found.");
            // Add corrective feedback or take appropriate action
        }

        if(hasOwnerPassed && toStringPassed){
            testResult.setStatus(true);
        }
        
        return methodScore;
    }
}
