package project;
import org.junit.jupiter.api.Test;

import project.strategy.LuggageManifestCalculationStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LuggageManifestCalculationTests {

    @Test
    public void testReadJavaCodeFromFile_SuccessfulReading() {
        
        String expectedJavaCode = "public class LuggageManifest { }";
        String fileName = "LuggageManifest.java";

        // Creating a temporary file with known content
        try {
            Path tempFile = Files.createTempFile(fileName, "");
            Files.writeString(tempFile, expectedJavaCode, StandardOpenOption.WRITE);

            LuggageManifestCalculationStrategy luggageManifestEvaluation = new LuggageManifestCalculationStrategy();
            String javaCode = luggageManifestEvaluation.readJavaCodeFromFile(tempFile.toString());

            assertNotNull(javaCode);
            assertEquals(expectedJavaCode, javaCode);
        } catch (IOException e) {
            e.printStackTrace();  // Handle exception appropriately in your code
        }
    }

    @Test
    public void testCheckAttributes() {
        
        String javaCode = "public class LuggageManifest {\n" +
                "    private ArrayList<LuggageSlip> slips;\n" +
                "}";

        LuggageManifestCalculationStrategy luggageManifestEvaluation = new LuggageManifestCalculationStrategy();
        int attributeScore = luggageManifestEvaluation.checkAttributes(javaCode);

        int expectedAttributeScore = 2;

        assertEquals(expectedAttributeScore, attributeScore);
    }

    @Test
    public void testCheckConstructor() {
        
        String javaCode = "public class LuggageManifest {\n" +
                "    public LuggageManifest() { }\n" +
                "}";

        LuggageManifestCalculationStrategy luggageManifestEvaluation = new LuggageManifestCalculationStrategy();
        int constructorScore = luggageManifestEvaluation.checkConstructor(javaCode);

        
        int expectedConstructorScore = 1;

        assertEquals(expectedConstructorScore, constructorScore);
    }

    

}
