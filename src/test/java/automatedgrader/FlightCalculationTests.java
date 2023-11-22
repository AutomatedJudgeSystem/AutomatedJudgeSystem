// package automatedgrader;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotNull;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.StandardOpenOption;

// import org.junit.Test;

// import automatedgrader.strategy.FlightCalculationStrategy;

// /**
//  * Test class for evaluating the FlightCalculationStrategy class.
//  */
// public class FlightCalculationTests {

//     /**
//      * Test method for the readJavaCodeFromFile method.
//      * Verifies that the readJavaCodeFromFile method correctly reads the content of a Java file.
//      */
//     @Test
//     public void readJavaCodeFromFileTest() {

//         // Sample Java code to be written to a temporary file
//         String expectedJavaCode = "class Flight { }";
//         String fileName = "Flight.java";

//         // Creating a temporary file with known content
//         try {
//             Path tempFile = Files.createTempFile(fileName, "");
//             Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);

//             FlightCalculationStrategy flightCalculationStrategy = new FlightCalculationStrategy();
//             String javaCode = flightCalculationStrategy.readJavaCodeFromFile(tempFile.toString());

//             assertNotNull(javaCode);
//             assertEquals(expectedJavaCode, javaCode);
//         } catch (IOException e) {
//             e.printStackTrace();  // Handle exception appropriately in your code
//         }
//     }

//     /**
//      * Test method for the checkAttributes method.
//      * Verifies that the checkAttributes method correctly evaluates the attributes in a given Java code.
//      */
//     @Test
//     public void testCheckAttributes() {

//         // Sample Java code with attributes to be evaluated
//         String javaCode = "class Flight {\n" +
//                 "    private String flightNo;\n" +
//                 "    private String destination;\n" +
//                 "    private String origin;\n" +
//                 "    private LocalDateTime flightDate;\n" +
//                 "    private LuggageManifest manifest;\n" +
//                 "}";

//         FlightCalculationStrategy strategy = new FlightCalculationStrategy();
//         int attributeScore = strategy.checkAttributes(javaCode);

//         // Assuming all attributes are correctly defined in the sample code
//         int expectedAttributeScore = 5;

//         assertEquals(expectedAttributeScore, attributeScore);
//     }

//     /**
//      * Test method for the checkConstructor method.
//      * Verifies that the checkConstructor method correctly evaluates the constructor in a given Java code.
//      */
//     @Test
//     public void testCheckConstructor() {

//         // Sample Java code with a constructor to be evaluated
//         String javaCode = "public class Flight {\n" +
//                 "    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate) {\n" +
//                 "        // Constructor implementation\n" +
//                 "    }\n" +
//                 "}";

//         FlightCalculationStrategy strategy = new FlightCalculationStrategy();
//         int constructorScore = strategy.checkConstructor(javaCode);

//         // Assuming the constructor is correctly defined in the sample code
//         int expectedConstructorScore = 2;

//         assertEquals(expectedConstructorScore, constructorScore);
//     }
// }

package automatedgrader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

import automatedgrader.strategy.EvaluationResult;
import automatedgrader.strategy.FlightCalculationStrategy;

/**
 * Test class for evaluating the FlightCalculationStrategy class.
 */
public class FlightCalculationTests {

    /**
     * Test method for the readJavaCodeFromFile method.
     * Verifies that the readJavaCodeFromFile method correctly reads the content of a Java file.
     */
    @Test
    public void readJavaCodeFromFileTest() {

        // Sample Java code to be written to a temporary file
        String expectedJavaCode = "class Flight { }";
        String fileName = "Flight.java";

        // Creating a temporary file with known content
        try {
            Path tempFile = Files.createTempFile(fileName, "");
            Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);

            FlightCalculationStrategy flightCalculationStrategy = new FlightCalculationStrategy();
            String javaCode = flightCalculationStrategy.readJavaCodeFromFile(tempFile.toString());

            assertNotNull(javaCode);
            assertEquals(expectedJavaCode, javaCode);
        } catch (IOException e) {
            e.printStackTrace();  // Handle exception appropriately in your code
        }
    }

    /**
     * Test method for the checkAttributes method.
     * Verifies that the checkAttributes method correctly evaluates the attributes in a given Java code.
     */
    @Test
    public void testCheckAttributes() {

        // Sample Java code with attributes to be evaluated
        String javaCode = "class Flight {\n" +
                "    private String flightNo;\n" +
                "    private String destination;\n" +
                "    private String origin;\n" +
                "    private LocalDateTime flightDate;\n" +
                "    private LuggageManifest manifest;\n" +
                "}";

        FlightCalculationStrategy strategy = new FlightCalculationStrategy();
        int attributeScore = strategy.checkAttributes(javaCode);

        // Assuming all attributes are correctly defined in the sample code
        int expectedAttributeScore = 5;

        assertEquals(expectedAttributeScore, attributeScore);
    }

    /**
     * Test method for the checkConstructor method.
     * Verifies that the checkConstructor method correctly evaluates the constructor in a given Java code.
     */
    @Test
    public void testCheckConstructor() {

        // Sample Java code with a constructor to be evaluated
        String javaCode = "public class Flight {\n" +
                "    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate) {\n" +
                "        // Constructor implementation\n" +
                "    }\n" +
                "}";

        FlightCalculationStrategy strategy = new FlightCalculationStrategy();
        int constructorScore = strategy.checkConstructor(javaCode);

        // Assuming the constructor is correctly defined in the sample code
        int expectedConstructorScore = 2;

        assertEquals(expectedConstructorScore, constructorScore);
    }

    /**
     * Test method for the createResult method.
     * Verifies that the createResult method correctly creates an EvaluationResult.
     */
    // @Test
    // public void testCreateResult() {
    
    //     // Sample Java code to be written to a temporary file
    //     String expectedJavaCode = "class Flight { }";
    //     String fileName = "Flight.java";
    
    //     // Creating a temporary file with known content
    //     try {
    //         Path tempFile = Files.createTempFile(fileName, "");
    //         Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);
    
    //         FlightCalculationStrategy flightCalculationStrategy = new FlightCalculationStrategy();
    //         EvaluationResult result = flightCalculationStrategy.createResult(tempFile.toString());
    
    //         assertNotNull(result);
    //         assertEquals("Flight Test", result.getTestName());
    //         assertFalse(result.isPassed());
    
    //         // Update the expected values based on the new calculation
    //         assertEquals("Attribute marks: 0 \n" +
    //                      "Constructor marks: 0 \n" +
    //                      "Other Method marks: 0", result.getFeedback());
    
    //         // Update the expected total based on the new calculation
    //         assertEquals(0, result.getTotal());
    //     } catch (IOException e) {
    //         e.printStackTrace();  // Handle exception appropriately in your code
    //     }
    // }
    
    

    /**
     * Test method for the testPassed method.
     * Verifies that the testPassed method correctly sets the status of an EvaluationResult.
     */
    @Test
    public void testTestPassed() {
    
        FlightCalculationStrategy flightCalculationStrategy = new FlightCalculationStrategy();
        EvaluationResult result = new EvaluationResult("Test", 10, "Feedback", false);
    
        flightCalculationStrategy.testPassed(result);
        assertFalse(result.isPassed());
    
        // Setting flags to mimic a passed test
        flightCalculationStrategy.attributesPassed = true;
        flightCalculationStrategy.constructorPassed = true;
        flightCalculationStrategy.checkInLuggagePassed = true;
        flightCalculationStrategy.printLuggageManifestPassed = true;
        flightCalculationStrategy.allowedLuggagePassed = true;
        flightCalculationStrategy.stringPassed = true;
    
        flightCalculationStrategy.testPassed(result);
        assertTrue(result.isPassed());
    }
    

    /**
     * Test method for the generateFeedback method.
     * Verifies that the generateFeedback method correctly generates feedback for an EvaluationResult.
     */
//     @Test
// public void testGenerateFeedback() {

//     // Sample Java code to be written to a temporary file
//     String expectedJavaCode = "class Flight { }";
//     String fileName = "Flight.java";

//     // Creating a temporary file with known content
//     try {
//         Path tempFile = Files.createTempFile(fileName, "");
//         Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);

//         FlightCalculationStrategy flightCalculationStrategy = new FlightCalculationStrategy();

//         // Setting flags to mimic a passed test
//         flightCalculationStrategy.attributesPassed = true;
//         flightCalculationStrategy.constructorPassed = true;
//         flightCalculationStrategy.checkInLuggagePassed = true;
//         flightCalculationStrategy.printLuggageManifestPassed = true;
//         flightCalculationStrategy.allowedLuggagePassed = true;
//         flightCalculationStrategy.stringPassed = true;

//         String feedback = flightCalculationStrategy.generateFeedback(tempFile.toString());

//         assertNotNull(feedback);
//         assertTrue(feedback.contains("TEST NAME: Flight Test\n"));
        
//         // Update to true since all flags are set
//         assertTrue(feedback.contains("TEST STATUS: true\n"));
        
//         // Update to reflect the new structure of feedback in FlightCalculationStrategy
//         assertTrue(feedback.contains("FEEDBACK ______________________________________________________\n" +
//                                       "Attribute marks: 6\n" +
//                                       "Constructor marks: 2\n" +
//                                       "Other Method marks: 3\n"));
        
//         // Update to 11 as all flags are set
//         assertTrue(feedback.contains("SCORE OUT OF 16: 11\n\n"));

//     } catch (IOException e) {
//         e.printStackTrace();  // Handle exception appropriately in your code
//     }
// }

}
