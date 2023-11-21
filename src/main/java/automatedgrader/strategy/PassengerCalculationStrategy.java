package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerCalculationStrategy implements CalculationStrategy {

   // private static final Random random = new Random();

    @Override
    public EvaluationResult calculate(String filePath) {
            String javaCode = readJavaCodeFromFile(filePath);
            int score = 0;
    
            // Check attribute types
            score += checkAttributeTypes(javaCode);
    
            // Check constructor
            score += checkConstructor(javaCode);
    
            // Check methods
            score += checkMethods(javaCode);
    
            String feedback ="Total score possible: 16 /n" + "Attribute marks: " +checkAttributeTypes(javaCode) +"\n Constructor marks: "+ checkConstructor(javaCode) +"/n Other Method marks: "+ checkMethods(javaCode);
            String total = "Total marks earned out of 16: "+ score;
            String testName = "PassengerCalculation";

        return new EvaluationResult(testName, total, feedback);
        }
    public String readJavaCodeFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("IOException during reading the file: " + e.getMessage());
            return "";
        }
    }

    public int checkAttributeTypes(String javaCode) {
        int attributeScore = 0;

        String[] attributes = {"passportNumber", "flightNo", "firstName", "lastName", "numLuggage", "cabinClass"};

        for (String attribute : attributes) {
            Pattern attributePattern = Pattern.compile("\\bprivate\\s+\\w+\\s+" + attribute + "\\s*;");
            Matcher matcher = attributePattern.matcher(javaCode);

            if (matcher.find()) {
                attributeScore += 1;
            } else {
                System.out.println("Attribute '" + attribute + "' does not meet the criteria.");
            }
        }

        return attributeScore;
    }

    public int checkConstructor(String javaCode) {
        Pattern constructorPattern = Pattern.compile("public\\s+Passenger\\s*\\(\\s*String\\s+passportNumber,\\s*String\\s+firstName,\\s*String\\s+lastName,\\s*String\\s+flightNo\\s*\\)\\s*\\{([^}]*)\\}");
        Matcher matcher = constructorPattern.matcher(javaCode);
    
        if (matcher.find()) {
            String constructorBody = matcher.group(1);
    
            boolean setsState = constructorBody.contains("this.passportNumber = passportNumber")
                    && constructorBody.contains("this.firstName = firstName")
                    && constructorBody.contains("this.lastName = lastName")
                    && constructorBody.contains("this.flightNo = flightNo");
    
            // Check if numLuggage is randomized
            boolean randomizesNumLuggage = constructorBody.contains("this.numLuggage =") &&
                    constructorBody.contains("new Random().nextInt(");
    
            // Check if cabinClass is randomized
            boolean randomizesCabinClass = constructorBody.contains("this.cabinClass =") &&
                    constructorBody.contains("randomCabinClass()");
    
            if (setsState && randomizesNumLuggage && randomizesCabinClass) {
                return 4; // Full marks
            } else {
                System.out.println("Constructor does not meet the criteria.");
                // Add corrective feedback or take appropriate action
            }
        } else {
            System.out.println("Constructor not found.");
            // Add corrective feedback or take appropriate action
        }
    
        return 0;
    }
    

    public int checkMethods(String javaCode) {
        int methodScore = 0;

        Pattern assignRandomCabinClassPattern = Pattern.compile("public\\s+void\\s+assignRandomCabinClass\\(\\)\\s*\\{");
        Matcher assignRandomCabinClassMatcher = assignRandomCabinClassPattern.matcher(javaCode);

        if (assignRandomCabinClassMatcher.find()) {
            String assignRandomCabinClassBody = assignRandomCabinClassMatcher.group(1);
    
            // Check if the cabinClass is set randomly
            boolean randomizesCabinClass = assignRandomCabinClassBody.contains("this.cabinClass =")
                    && assignRandomCabinClassBody.contains("randomCabinClass()");
    
            if (randomizesCabinClass) {
                methodScore += 2; // Full marks 
            } else {
                System.out.println("assignRandomCabinClass method does not meet the criteria.");
            }
        } else {
            System.out.println("assignRandomCabinClass method not found.");
        }

        // Check toString method
        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);

        if (toStringMatcher.find()) {
            String toStringBody = toStringMatcher.group(1);
    
           boolean correctFormat = toStringBody.contains("return \"PP NO. \" + passportNumber + \" NAME: \" + firstName.charAt(0) + \".\" + lastName + \" NUMLUGGAGE: \" + numLuggage + \" CLASS: \" + cabinClass;");
    
            if (correctFormat) {
                methodScore += 3; // Full marks 
            } else {
                System.out.println("toString method does not return the correct format.");
            }
        } else {
            System.out.println("toString method not found.");
        }

        return methodScore;
    }

}


