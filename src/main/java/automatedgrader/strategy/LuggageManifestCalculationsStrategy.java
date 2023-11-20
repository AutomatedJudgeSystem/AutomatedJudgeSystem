package project.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LuggageManifestCalculationStrategy implements CalculationStrategy{
    @Override
    public int calculate(String filePath){
        String javaCode = readJavaCodeFromFile(filePath);
        int score = 0;

        score += checkAttributes(javaCode);
        score += checkConstructor(javaCode);
        score += checkAddLuggageMethod(javaCode);
        score += checkGetExcessLuggageCost(javaCode);
        score += checkGetExcessLuggageCostByPassenger(javaCode);
        score += checkToStringMethod(javaCode);

        return score;
    }

    public String readJavaCodeFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("IOException during reading the file: " + e.getMessage());
            return "";
        }
    }

    public int checkAttributes(String javaCode) {
        int attributeScore = 0;

        String[] expectedAttributeTypes = {"ArrayList<LuggageSlip>"};
        String[] attributes = {"slips"};

        for (int i = 0; i < attributes.length; i++) {
            String attribute = attributes[i];
            String expectedType = expectedAttributeTypes[i];

            Pattern attributePattern = Pattern.compile("\\bprivate\\s+" + expectedType + "\\s+" + attribute + "\\s*;");
            Matcher matcher = attributePattern.matcher(javaCode);

            if (matcher.find()) {
                attributeScore += 2;
            } else {
                System.out.println("Attribute '" + attribute + "' does not meet the criteria.");
            }
        }
        return attributeScore;
    }

    public int checkConstructor(String javaCode) {
        int constructorScore = 0;

        Pattern constructorPattern = Pattern.compile("public\\s+LuggageManifest\\(\\)\\s*\\{([^}]*)\\}");
        Matcher matcher = constructorPattern.matcher(javaCode);

        if (matcher.find()) {
            constructorScore += 1; // Full marks 
        } else {
            System.out.println("LuggageManifest() constructor not found.");
        }

        return constructorScore;
    }

    public int checkAddLuggageMethod(String javaCode) {
        int methodScore = 0;

        Pattern addLuggagePattern = Pattern.compile("public\\s+String\\s+addLuggage\\(Passenger\\s+p,\\s+Flight\\s+f\\)\\s*\\{([^}]*)\\}");
        Matcher addLuggageMatcher = addLuggagePattern.matcher(javaCode);

        if (addLuggageMatcher.find()) {
            methodScore += 6; // Full marks
        } else {
            System.out.println("addLuggage(Passenger p, Flight f) method not found.");
        }

        return methodScore;
    }

    public int checkGetExcessLuggageCost(String javaCode) {
        int methodScore = 0;

        Pattern getExcessLuggageCostPattern = Pattern.compile("public\\s+double\\s+getExcessLuggageCost\\(int\\s+numPieces,\\s+int\\s+numAllowedPieces\\)\\s*\\{([^}]*)\\}");
        Matcher getExcessLuggageCostMatcher = getExcessLuggageCostPattern.matcher(javaCode);

        if (getExcessLuggageCostMatcher.find()) {
            methodScore += 3; // Full marks 
        } else {
            System.out.println("getExcessLuggageCost(int numPieces, int numAllowedPieces) method not found.");
        }

        return methodScore;
    }

    private int checkGetExcessLuggageCostByPassenger(String javaCode) {
        int methodScore = 0;

        Pattern getExcessLuggageCostByPassengerPattern = Pattern.compile("public\\s+String\\s+getExcessLuggageCostByPassenger\\(String\\s+passportNumber\\)\\s*\\{([^}]*)\\}");
        Matcher getExcessLuggageCostByPassengerMatcher = getExcessLuggageCostByPassengerPattern.matcher(javaCode);

        if (getExcessLuggageCostByPassengerMatcher.find()) {
            methodScore += 5; // Full marks 
        } else {
            System.out.println("getExcessLuggageCostByPassenger(String passportNumber) method not found.");
        }

        return methodScore;
    }

    private int checkToStringMethod(String javaCode) {
        int methodScore = 0;

        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{([^}]*)\\}");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);

        if (toStringMatcher.find()) {
            methodScore += 3; // Full marks 
        } else {
            System.out.println("toString() method not found.");
        }

        return methodScore;
    }



}

