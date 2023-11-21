package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerCalculationStrategy implements CalculationStrategy, FeedbackGenerator {
    private boolean attributesPassed = false;
    private boolean constructorPassed = false;
    private boolean methodsPassed = false;

    @Override
    public int calculate(String filePath) {

        String javaCode = readJavaCodeFromFile(filePath);
        int score = 0;
    
        score += checkAttributes(javaCode);
        score += checkConstructor(javaCode);
        score += checkMethods(javaCode);
    
        return score;
    }

    @Override
    public EvaluationResult createResult (String filePath) {
        String javaCode = readJavaCodeFromFile(filePath);

        String testname= "Passenger Test";
        int total = calculate(filePath);
        String feedback = "Attribute marks: " +checkAttributes(javaCode) +"\n Constructor marks: "+
                          checkConstructor(javaCode) +"/n Other Method marks: "+ checkMethods(javaCode);
        boolean status = false;

        return new EvaluationResult(testname, total, feedback, status);
    }

    @Override
    public void TestPassed(EvaluationResult evaluationResult){
        if (attributesPassed && constructorPassed && methodsPassed){
            evaluationResult.setStatus(true);  
        }

    }

    @Override
    public void generateFeedback(String filePath){
        EvaluationResult evaluationResult = createResult(filePath);
        
        System.out.println("TEST NAME: " + evaluationResult.getTestName());
        TestPassed(evaluationResult);
        System.out.println("TEST STATUS: " + evaluationResult.isPassed(evaluationResult));
        System.out.println("FEEDBACK ______________________________________________________/n" 
                           + evaluationResult.getFeedback());
        System.out.println("SCORE OUT OF 16: "+ evaluationResult.getTotal() + "/n/n");
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

        String[] attributes = {"passportNumber", "flightNo", "firstName", "lastName", "numLuggage", "cabinClass"};

        for (String attribute : attributes) {
            Pattern attributePattern = Pattern.compile("\\bprivate\\s+\\w+\\s+" + attribute + "\\s*;");
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
                constructorPassed = true;
                return 4; // Full marks 
            } else {
                System.out.println("Constructor does not meet the criteria.");
            }
        } else {
            System.out.println("Constructor not found.");
        }
    
        return 0;
    }

    public int checkMethods(String javaCode) {
        int methodScore = 0;

        Pattern assignRandomCabinClassPattern = Pattern.compile("public\\s+void\\s+assignRandomCabinClass\\(\\)\\s*\\{");
        Matcher assignRandomCabinClassMatcher = assignRandomCabinClassPattern.matcher(javaCode);
        boolean randomClassPassed = false;

        if (assignRandomCabinClassMatcher.find()) {
            String assignRandomCabinClassBody = assignRandomCabinClassMatcher.group(1);
    
            // Check if the cabinClass is set randomly
            boolean randomizesCabinClass = assignRandomCabinClassBody.contains("this.cabinClass =")
                    && assignRandomCabinClassBody.contains("randomCabinClass()");
    
            if (randomizesCabinClass) {
                methodScore += 2; // Full marks
                randomClassPassed = true;
            } else {
                System.out.println("assignRandomCabinClass method does not meet the criteria.");
            }
        } else {
            System.out.println("assignRandomCabinClass method not found.");
        }

        // Check toString method
        Pattern toStringPattern = Pattern.compile("public\\s+String\\s+toString\\(\\)\\s*\\{");
        Matcher toStringMatcher = toStringPattern.matcher(javaCode);
        boolean toStringPassed = false; 

        if (toStringMatcher.find()) {
            String toStringBody = toStringMatcher.group(1);
    
           boolean correctFormat = toStringBody.contains("return \"PP NO. \" + passportNumber + \" NAME: \" + firstName.charAt(0) + \".\" + lastName + \" NUMLUGGAGE: \" + numLuggage + \" CLASS: \" + cabinClass;");
    
            if (correctFormat) {
                methodScore += 3; // Full marks 
                toStringPassed = true; 
            } else {
                System.out.println("toString method does not return the correct format.");
            }
        } else {
            System.out.println("toString method not found.");
        }

        if(randomClassPassed && toStringPassed){
            methodsPassed = true;
        }

        return methodScore;
    }

}


