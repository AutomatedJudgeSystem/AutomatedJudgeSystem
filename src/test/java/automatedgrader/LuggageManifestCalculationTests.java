package automatedgrader;
import org.junit.jupiter.api.Test;

import automatedgrader.strategy.LuggageManifestCalculationsStrategy;

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

            LuggageManifestCalculationsStrategy luggageManifestEvaluation = new LuggageManifestCalculationsStrategy();
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

        LuggageManifestCalculationsStrategy luggageManifestEvaluation = new LuggageManifestCalculationsStrategy();
        int attributeScore = luggageManifestEvaluation.checkAttributes(javaCode);

        int expectedAttributeScore = 2;

        assertEquals(expectedAttributeScore, attributeScore);
    }

    @Test
    public void testCheckConstructor() {
        
        String javaCode = "public class LuggageManifest {\n" +
                "    public LuggageManifest() { }\n" +
                "}";

        LuggageManifestCalculationsStrategy luggageManifestEvaluation = new LuggageManifestCalculationsStrategy();
        int constructorScore = luggageManifestEvaluation.checkConstructor(javaCode);

        
        int expectedConstructorScore = 1;

        assertEquals(expectedConstructorScore, constructorScore);
    }

    

}
