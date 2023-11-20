package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

import project.strategy.FlightCalculationStrategy;

public class FlightCalculationTests {

    @Test
    public void readJavaCodeFromFileTest() {
        
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
    
    @Test
    public void testCheckAttributes() {
        
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

    @Test
    public void testCheckConstructor() {
        
        String javaCode = "public class Flight {\n" +
                "    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate) {\n" +
                "        // Constructor implementation\n" +
                "    }\n" +
                "}";

        FlightCalculationStrategy strategy = new FlightCalculationStrategy();
        int constructorScore = strategy.checkConstructor(javaCode);

        int expectedConstructorScore = 2;

        assertEquals(expectedConstructorScore, constructorScore);
    }

    

}
