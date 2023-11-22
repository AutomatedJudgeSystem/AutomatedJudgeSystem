package automatedgrader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import automatedgrader.strategy.EvaluationResult;
import automatedgrader.template.FileHandler;
import automatedgrader.template.NestedZipFileHandler;


/**
 * Test class for FileHandler and its concrete implementation NestedZipFileHandler.
 */
public class FileHandlerTest{
    final static String path = "files/test/";

    /**
     * Test case to ensure that a valid zip file is handled successfully.
     * Responsible for checking if the files are extracted and the directory structure is preserved.
     */
    @Test
    public void testValidZipFileHandling() {
        FileHandler fileHandler = new NestedZipFileHandler(new EvaluationResult());
        try {
            fileHandler.handleFile(path + "valid.zip");
            assertTrue(Files.exists(Paths.get(path + "valid")));
            assertTrue(Files.exists(Paths.get(path + "valid/Salmah_Hanif_816029006_A1/Passengers.txt")));
            assertTrue(Files.exists(Paths.get(path + "valid/Shania_Gajadhar_816030212_A1/Passenger.java")));
            assertTrue(Files.exists(Paths.get(path + "valid/Salmah_Hanif_816029006_A1/Passenger.class")));
            assertTrue(Files.exists(Paths.get((path + "valid.zip").substring(0, (path + "valid.zip").length()-4))));
            
            // Use Files.list to efficiently check the existence of multiple files and directories
            long count = Files.list(Paths.get(path + "valid"))
                    .filter(path -> Files.exists(path))
                    .count();

            assertTrue(count > 0);
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
     * Test case to verify that the handler handles a file with an invalid zip extension appropriately.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testInvalidZipFileExtension() {
        FileHandler fileHandler = new NestedZipFileHandler(new EvaluationResult());
        try {
            fileHandler.handleFile(path + "invalid.txt");
            fail("Expected IOException not thrown");
        } catch (IOException e) {
            assertEquals("There is no zip file at '" + path + "invalid.txt' to extract.", e.getMessage());
        }
    }

    /**
     * Test case to verify the handling of an empty zip file.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testEmptyZipFile() {
        FileHandler fileHandler = new NestedZipFileHandler(new EvaluationResult());
        try {
            fileHandler.handleFile(path + "empty.zip");
            fail("Expected IOException not thrown");
        } catch (IOException e) {
            assertEquals("The zip file at '" + path + "empty.zip' is empty.", e.getMessage());
        }
    }

    /**
     * Test case to verify the handling of a non-existent file.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testNonExistentZipFile() {
        FileHandler fileHandler = new NestedZipFileHandler(new EvaluationResult());
        try {
            fileHandler.handleFile(path + "nonExistent.zip");
            fail("Expected IOException not thrown");
        } catch (IOException e) {
            assertEquals("The file at '" + path + "nonExistent.zip' does not exist.", e.getMessage());
        }
    }

    /**
     * Test case to verify that the destination directory is not recreated but reused if it already exists.
     * Responsible for checking if the destination directory is not recreated when it already exists.
     */
    @Test
    public void testDestinationDirectoryReuse() {
        FileHandler fileHandler = new NestedZipFileHandler(new EvaluationResult());
        // String zipFilePath = "path/to/valid/file.zip";
        try {
            fileHandler.handleFile(path + "valid.zip");
            fail("Expected IOException not thrown");
            // Add more assertions as needed
        } catch (IOException e) {
            assertTrue(Files.exists(Paths.get((path + "valid.zip").substring(0, (path + "valid.zip").length()-4))));
            assertEquals("The folder '" + path + "valid' exists already. It's contents may be altered but not deleted.", e.getMessage());
        }
    }

    /**
     * Test case to ensure that multiple valid zip files are handled correctly.
     * Responsible for checking if each student's submission files are extracted and the directory structure is preserved.
     */
    @Test
    public void testMultipleValidZipFilesHandling() {
        FileHandler fileHandler = new NestedZipFileHandler();
        try {
            Path zipPath = Paths.get("files/submissions.zip");

            fileHandler.handleFile(zipPath.toString());

            // check that each student's submission zip exists
            assertTrue(Files.exists(Paths.get("files/submissions/Nicholas_Pariag_816031948_A1")));
            assertTrue(Files.exists(Paths.get("files/submissions/Salmah_Hanif_816029006_A1")));
            assertTrue(Files.exists(Paths.get("files/submissions/Shania_Gajadhar_816030212_A1")));

            Path s1Path = Paths.get("files/submissions/Nicholas_Pariag_816031948_A1");
            Path s2Path = Paths.get("files/submissions/Salmah_Hanif_816029006_A1");
            Path s3Path = Paths.get("files/submissions/Shania_Gajadhar_816030212_A1");

            // Use Files.list to efficiently check the existence of files in each submission
            // count the number of files in each student's submissio folder
            long count1 = Files.list(s1Path)
                    .filter(path -> Files.exists(path))
                    .count();

            long count2 = Files.list(s2Path)
                    .filter(path -> Files.exists(path))
                    .count();

            long count3 = Files.list(s3Path)
                    .filter(path -> Files.exists(path))
                    .count();

            assertTrue(count1 > 0);
            assertTrue(count2 > 0);
            assertTrue(count3 > 0);

        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
     * Test case to verify correct handling of multi-file submissions.
     * Responsible for checking if the files within the student's submission are handled correctly.
     */
    @Test
    public void testMultiFileSubmissionHandling() {
        FileHandler fileHandler = new NestedZipFileHandler();
        try {
            fileHandler.handleFile("files/submissions.zip");
        } catch (IOException e) {
            e.getMessage();
        }

        // check that files from a student's submission have been extracted
        assertTrue(Files.exists(Paths.get("files/submissions/Nicholas_Pariag_816031948_A1/Flight.java")));
        assertTrue(Files.exists(Paths.get("files/submissions/Nicholas_Pariag_816031948_A1/LuggageManagementSystem.java")));
        assertTrue(Files.exists(Paths.get("files/submissions/Nicholas_Pariag_816031948_A1/LuggageManifest.java")));
    }
}
