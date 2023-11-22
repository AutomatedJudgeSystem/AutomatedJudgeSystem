package automatedgrader;

import org.junit.jupiter.api.Test;

import automatedgrader.strategy.LuggageSlipCalculationStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LuggageSlipCalculationTests {

    @Test
    public void readJavaCodeFromFileTest() {
        
        String expectedJavaCode = "public class LuggageSlip { }";
        String fileName = "LuggageSlip.java";

        // Creating a temporary file with known content
        try {
            Path tempFile = Files.createTempFile(fileName, "");
            Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);

            LuggageSlipCalculationStrategy luggageSlipEvaluation = new LuggageSlipCalculationStrategy();
            String javaCode = luggageSlipEvaluation.readJavaCodeFromFile(tempFile.toString());

            assertNotNull(javaCode);
            assertEquals(expectedJavaCode, javaCode);
        } catch (IOException e) {
            e.printStackTrace();  // Handle exception appropriately in your code
        }
    }

    @Test
    public void testCheckAttributeTypes() {
        
        String javaCode = "public class LuggageSlip {\n" +
                "    private Passenger owner;\n" +
                "    private int luggageSlipIDCounter;\n" +
                "    private String luggageSlipID;\n" +
                "    private String label;\n" +
                "}";

        LuggageSlipCalculationStrategy luggageSlipEvaluation = new LuggageSlipCalculationStrategy();
        int attributeScore = luggageSlipEvaluation.checkAttributes(javaCode);

        
        int expectedAttributeScore = 4;

        assertEquals(expectedAttributeScore, attributeScore);
    }

    @Test
    public void testCheckConstructors() {
        
        String javaCode = "public class LuggageSlip {\n" +
                "    public LuggageSlip(Passenger p, Flight f) { }\n" +
                "    public LuggageSlip(Passenger p, Flight f, String label) { }\n" +
                "}";

        LuggageSlipCalculationStrategy luggageSlipEvaluation = new LuggageSlipCalculationStrategy();
        int constructorScore = luggageSlipEvaluation.checkConstructors(javaCode);

        int expectedConstructorScore = 6;

        assertEquals(expectedConstructorScore, constructorScore);
    }

    

}
