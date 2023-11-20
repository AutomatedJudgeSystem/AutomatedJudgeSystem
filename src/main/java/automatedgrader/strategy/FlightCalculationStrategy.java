package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightCalculationStrategy implements CalculationStrategy {

    @Override
    public int calculate(String filePath) {

        String javaCode = readJavaCodeFromFile(filePath);
        int score = 0;

        
        score += checkAttributes(javaCode);
        score += checkConstructor(javaCode);
        score += checkCheckInLuggageMethod(javaCode);
        score += checkPrintLuggageManifestMethod(javaCode);
        score += checkGetAllowedLuggageMethod(javaCode);
        score += checkToStringMethod(javaCode);

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

    private int checkAttributes(String javaCode) {
        int attributeScore = 0;

        // Define the expected attribute types
        String[] expectedAttributeTypes = {"String", "String", "String", "LocalDateTime", "LuggageManifest"};

        // Define the attribute names
        String[] attributes = {"flightNo", "destination", "origin", "flightDate", "manifest"};

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

    private int checkConstructor(String javaCode) {
        int constructorScore = 0;

        // Check Flight(String flightNo, String destination, String origin, LocalDateTime flightDate) constructor
        Pattern constructorPattern = Pattern.compile("public\\s+Flight\\(String\\s+flightNo,\\s+String\\s+destination,\\s+String\\s+origin,\\s+LocalDateTime\\s+flightDate\\)\\s*\\{([^}]*)\\}");
        Matcher matcher = constructorPattern.matcher(javaCode);

        if (matcher.find()) {
            constructorScore += 2; // Full marks 
        } else {
            System.out.println("Flight constructor not found.");
        }

        return constructorScore;
    }

    private int checkCheckInLuggageMethod(String javaCode) {
        int methodScore = 0;

        // Check checkInLuggage(Passenger p) method
        Pattern checkInLuggagePattern = Pattern.compile("public\\s+String\\s+checkInLuggage\\(Passenger\\s+p\\)\\s*\\{([^}]*)\\}");
        Matcher checkInLuggageMatcher = checkInLuggagePattern.matcher(javaCode);

        if (checkInLuggageMatcher.find()) {
            methodScore += 5; // Full marks 
        } else {
            System.out.println("checkInLuggage(Passenger p) method not found.");
        }

        return methodScore;
    }

    private int checkPrintLuggageManifestMethod(String javaCode) {
        int methodScore = 0;

        // Check printLuggageManifest() method
        Pattern printLuggageManifestPattern = Pattern.compile("public\\s+String\\s+printLuggageManifest\\(\\)\\s*\\{([^}]*)\\}");
        Matcher printLuggageManifestMatcher = printLuggageManifestPattern.matcher(javaCode);

        if (printLuggageManifestMatcher.find()) {
            methodScore += 1; // Full marks 
        } else {
            System.out.println("printLuggageManifest() method not found.");
        }

        return methodScore;
    }

    private int checkGetAllowedLuggageMethod(String javaCode) {
        int methodScore = 0;

        // Check getAllowedLuggage(char cabinClass) method
        Pattern getAllowedLuggagePattern = Pattern.compile("public\\s+int\\s+getAllowedLuggage\\(char\\s+cabinClass\\)\\s*\\{([^}]*)\\}");
        Matcher getAllowedLuggageMatcher = getAllowedLuggagePattern.matcher(javaCode);

        if (getAllowedLuggageMatcher.find()) {
            methodScore += 2; // Full marks
        } else {
            System.out.println("getAllowedLuggage(char cabinClass) method not found.");
        }

        return methodScore;
    }

    private int checkToStringMethod(String javaCode) {
        int methodScore = 0;

        // Check toString() method
        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{([^}]*)\\}");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);

        if (toStringMatcher.find()) {
            methodScore += 1; // Full marks
        } else {
            System.out.println("toString() method not found.");
        }

        return methodScore;
    }

}
