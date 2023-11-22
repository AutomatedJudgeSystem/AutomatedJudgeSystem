package automatedgrader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

import automatedgrader.strategy.PassengerCalculationStrategy;

public class PassengerCalculationTests {
    
    @Test
    public void testReadJavaCodeFromFile_SuccessfulReading() {
        
        String expectedJavaCode = "public class Passenger { }";
        String fileName = "Passenger.java";

        // Creating a temporary file with known content
        try {
            Path tempFile = Files.createTempFile(fileName, "");
            Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);

            PassengerCalculationStrategy passengerEvaluation = new PassengerCalculationStrategy();
            String javaCode = passengerEvaluation.readJavaCodeFromFile(tempFile.toString());

            assertNotNull(javaCode);
            assertEquals(expectedJavaCode, javaCode);
        } catch (IOException e) {
            e.printStackTrace();  // Handle exception appropriately in your code
        }
    }

    @Test
    public void testCheckAttributeTypes() {
        
        String javaCode = "public class Passenger {\n" +
                "    private String passportNumber;\n" +
                "    private String flightNo;\n" +
                "    private String firstName;\n" +
                "    private String lastName;\n" +
                "    private int numLuggage;\n" +
                "    private String cabinClass;\n" +
                "}";

        PassengerCalculationStrategy passengerEvaluation = new PassengerCalculationStrategy();
        int attributeScore = passengerEvaluation.checkAttributes(javaCode);

        
        int expectedAttributeScore = 6;

        assertEquals(expectedAttributeScore, attributeScore);
    }

    @Test
    public void testCheckConstructor() {
        
        String javaCode = "public class Passenger {\n" +
                "    public Passenger(String passportNumber, String firstName, String lastName, String flightNo) {\n" +
                "        this.passportNumber = passportNumber;\n" +
                "        this.firstName = firstName;\n" +
                "        this.lastName = lastName;\n" +
                "        this.flightNo = flightNo;\n" +
                "        this.numLuggage = new Random().nextInt(10);\n" +
                "        this.cabinClass = randomCabinClass();\n" +
                "    }\n" +
                "}";

        PassengerCalculationStrategy passengerEvaluation = new PassengerCalculationStrategy();
        int constructorScore = passengerEvaluation.checkConstructor(javaCode);

        int expectedConstructorScore = 4;

        assertEquals(expectedConstructorScore, constructorScore);
    }
}
