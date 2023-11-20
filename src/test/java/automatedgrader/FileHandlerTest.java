package automatedgrader;

import junit.framework.TestCase;

import static org.junit.Assume.assumeNoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


import automatedgrader.template.FileHandler;
import automatedgrader.template.NestedZipFileHandler;


public class FileHandlerTest extends TestCase{
    final static String path = "src/test/java/automatedgrader/";
    @Before
    public static void initializeState() throws Exception{
        Files.createDirectories(Paths.get(path));
        Files.createDirectories(Paths.get(path + "empty.zip"));
        Files.createDirectories(Paths.get(path + "invalid.txt"));
    }

//     @Test
//     public void testNestedZipFileHandler() throws Exception{
//         NestedZipFileHandler fileHandler = new NestedZipFileHandler();
//         fileHandler.handleFile("temp.zip");
//         File f = new File("temp");
//         // assertTrue(f.isFile());
//         // if(!sourceDirectory.exists()){
//         //     sourceDirectory.mkdir();
//         // }
//         // try{
        
//         // }
//         // catch(Exception e){

//         // // }
//         // ZipEntry student1zip = new ZipEntry("student1.zip");
//         // ZipEntry student2zip = new ZipEntry("student2.zip");
//         // ZipEntry student3zip = new ZipEntry("student3.zip");
//         // ZipEntry student4zip = new ZipEntry("student4.zip");
//         // ZipEntry student5zip = new ZipEntry("student5.zip");
        
//         // File file = new File("sampleFile1.java");
//         // FileInputStream(file);
//         // try{
//         //     out.putNextEntry(student1zip);
//         //     out.putNextEntry(student2zip);
//         //     out.putNextEntry(student3zip);
//         //     out.putNextEntry(student4zip);
//         //     out.putNextEntry(student5zip);
//         //     out.putNextEntry(student6zip);
//         // // }
//         // // catch(Exception e){

//         // // }
//         // fileHandler.handleFile("temp.zip");
//         // try{
//         //     Files.delete(Paths.get("temp.zip"));
//         // }
//         // catch(Exception e){

//         // }
//     }

    /**
     * Test case to ensure that a valid zip file is handled successfully.
     * Responsible for checking if the files are extracted and the directory structure is preserved.
     */
    @Test
    public void testValidZipFileHandling() {
        FileHandler fileHandler = new NestedZipFileHandler();
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
            // Add more assertions as needed
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
     * Test case to verify that the handler handles an invalid zip file appropriately.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testInvalidZipFileExtension() {
        FileHandler fileHandler = new NestedZipFileHandler();
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
        FileHandler fileHandler = new NestedZipFileHandler();
        try {
            fileHandler.handleFile(path + "empty.zip");
            fail("Expected IOException not thrown");
        } catch (IOException e) {
            assertEquals("The zip file at '" + path + "empty.zip' is empty.", e.getMessage());
        }
    }

    /**
     * Test case to verify the handling of a nested zip file.
     * Responsible for checking if nested zip files are recursively handled and extracted.
     */
    @Test
    public void testNestedZipFileHandling() throws IOException {
        FileHandler fileHandler = new NestedZipFileHandler();
        fileHandler.handleFile("files/submissions.zip");
        assertTrue(Files.exists(Paths.get("files/submissions/Nicholas_Pariag_816031948_A1/Flight.java")));
        // Add more assertions as needed
    }

    /**
     * Test case to verify the handling of a non-existent file.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testNonExistentZipFile() {
        FileHandler fileHandler = new NestedZipFileHandler();
        try {
            fileHandler.handleFile(path + "nonExistent.zip");
            fail("Expected IOException not thrown");
        } catch (IOException e) {
            assertEquals("The file at '" + path + "nonExistent.zip' does not exist.", e.getMessage());
        }
        
       
    }

//     /**
//      * Test case to ensure proper destination directory creation.
//      * Responsible for checking if the destination directory is created successfully.
//      */
//     @Test
//     public void testDestinationDirectoryCreation() {
//         FileHandler fileHandler = new NestedZipFileHandler();
//         String zipFilePath = "path/to/valid/file.zip";
//         try {
//             fileHandler.handleFile(zipFilePath);
//             assertTrue(Files.exists(Paths.get(zipFilePath.substring(0, zipFilePath.length()-4))));
//             // Add more assertions as needed
//         } catch (IOException e) {
//             fail("Unexpected IOException: " + e.getMessage());
//         }
//     }

//     /**
//      * Test case to verify that the destination directory is not recreated if it already exists.
//      * Responsible for checking if the destination directory is not recreated when it already exists.
//      */
    @Test
    public void testDestinationDirectoryReuse() {
        FileHandler fileHandler = new NestedZipFileHandler();
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

   
// /**
//      * Test case to verify the handling of a zip file with multiple submissions.
//      * Responsible for checking if multiple submissions within a zip file are processed correctly.
//      */
//     @Test
//     public void testMultipleSubmissionsHandling() {
//         FileHandler fileHandler = new NestedZipFileHandler();
//         try {
//             Path rootPath = Paths.get("path/to/multiple/submissions");
//             Path zipPath = Paths.get("path/to/multiple/submissions.zip");

//             fileHandler.handleFile(zipPath.toString());

//             // Use Files.list to efficiently check the existence of multiple files and directories
//             long count = Files.list(rootPath)
//                     .filter(path -> Files.exists(path))
//                     .count();

//             assertTrue(count > 0);
//         } catch (IOException e) {
//             fail("Unexpected IOException: " + e.getMessage());
//         }
//     }


// /**
//      * Test case to verify the handling of various file formats within a zip file.
//      * Responsible for checking if different file formats are handled correctly.
//      */
//     @Test
//     public void testMultipleFormatsHandling() {
//         FileHandler fileHandler = new NestedZipFileHandler();
//         try {
//             Path rootPath = Paths.get("path/to/multiple/formats");
//             Path zipPath = Paths.get("path/to/multiple/formats.zip");

//             fileHandler.handleFile(zipPath.toString());

//             // Add assertions to check if various file formats are handled correctly
//             assertTrue(Files.exists(rootPath.resolve("text_file.txt")));
//             assertTrue(Files.exists(rootPath.resolve("image_file.jpg")));
//             assertTrue(Files.exists(rootPath.resolve("code_file.java")));
//         } catch (IOException e) {
//             fail("Unexpected IOException: " + e.getMessage());
//         }
//     }
    @AfterClass
    public static void clear() throws IOException{
        Files.deleteIfExists(Paths.get(path + "empty.zip"));
        Files.deleteIfExists(Paths.get(path + "invalid.txt"));
    }
}
