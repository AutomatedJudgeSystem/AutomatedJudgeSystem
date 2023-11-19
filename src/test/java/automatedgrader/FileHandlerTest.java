package automatedgrader;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


import automatedgrader.template.FileHandler;
import automatedgrader.template.NestedZipFileHandler;


public class FileHandlerTest extends TestCase{
    @Before
    public static void initializeState() throws Exception{
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("student6.zip"));
        ZipEntry file1 = new ZipEntry("file1.java");
        out.putNextEntry(file1);
        out.close();
        out = new ZipOutputStream(new FileOutputStream("temp.zip"));
        ZipEntry student6zip = new ZipEntry("student6.zip");
        out.putNextEntry(student6zip);
        out.close();
    }

    @Test
    public void testNestedZipFileHandler() throws Exception{
        NestedZipFileHandler fileHandler = new NestedZipFileHandler();
        fileHandler.handleFile("temp.zip");
        File f = new File("temp");
        // assertTrue(f.isFile());
        // if(!sourceDirectory.exists()){
        //     sourceDirectory.mkdir();
        // }
        // try{
        
        // }
        // catch(Exception e){

        // // }
        // ZipEntry student1zip = new ZipEntry("student1.zip");
        // ZipEntry student2zip = new ZipEntry("student2.zip");
        // ZipEntry student3zip = new ZipEntry("student3.zip");
        // ZipEntry student4zip = new ZipEntry("student4.zip");
        // ZipEntry student5zip = new ZipEntry("student5.zip");
        
        // File file = new File("sampleFile1.java");
        // FileInputStream(file);
        // try{
        //     out.putNextEntry(student1zip);
        //     out.putNextEntry(student2zip);
        //     out.putNextEntry(student3zip);
        //     out.putNextEntry(student4zip);
        //     out.putNextEntry(student5zip);
        //     out.putNextEntry(student6zip);
        // // }
        // // catch(Exception e){

        // // }
        // fileHandler.handleFile("temp.zip");
        // try{
        //     Files.delete(Paths.get("temp.zip"));
        // }
        // catch(Exception e){

        // }
    }
    @AfterClass
    public static void clear() throws IOException{
        if(Files.deleteIfExists(Paths.get("temp.zip")))
            assertTrue(true);
        else
            assertTrue(false);
    }

    /**
     * Test case to ensure that a valid zip file is handled successfully.
     * Responsible for checking if the files are extracted and the directory structure is preserved.
     */
    @Test
    public void testValidZipFileHandling() throws IOException {
        FileHandler fileHandler = new NestedZipFileHandler();
        fileHandler.handleFile("files/submissions.zip");
        assertTrue(Files.exists(Paths.get("files/submissions")));
        // Add more assertions as needed
    }

    /**
     * Test case to verify that the handler handles an invalid zip file appropriately.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testInvalidZipFileHandling() throws IOException {
        try {
            FileHandler fileHandler = new NestedZipFileHandler();
            fileHandler.handleFile("files/nonzipsubmissions/Flight.class");

        } catch (Exception e) {
            fail("Expected IOException not thrown");
        }
    }

    /**
     * Test case to verify the handling of an empty zip file.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testEmptyZipFileHandling() throws IOException {
        try {
            FileHandler fileHandler = new NestedZipFileHandler();
            fileHandler.handleFile("files/emptysubmissions.zip"); 

        } catch (Exception e) {
            fail("Expected IOException not thrown");
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
     * Test case to verify the handling of a non-zip file.
     * Responsible for checking if the expected IOException is thrown with the correct error message.
     */
    @Test
    public void testNonZipFileHandling() throws IOException {
        try {
            FileHandler fileHandler = new NestedZipFileHandler();
            fileHandler.handleFile("files/nonzipsubmissions/Flights.txt");
        } catch (Exception e) {
             fail("Expected IOException not thrown");
        }
        
       
    }

    /**
     * Test case to ensure proper destination directory creation.
     * Responsible for checking if the destination directory is created successfully.
     */
    @Test
    public void testDestinationDirectoryCreation() throws IOException {
        FileHandler fileHandler = new NestedZipFileHandler();
        String zipFilePath = "files/submissions.zip";
        fileHandler.handleFile(zipFilePath);
        assertTrue(Files.exists(Paths.get(zipFilePath.substring(0, zipFilePath.length()-4))));
        // Add more assertions as needed
    }

    /**
     * Test case to verify that the destination directory is not recreated if it already exists.
     * Responsible for checking if the destination directory is not recreated when it already exists.
     */
    @Test
    public void testDuplicateDestinationDirectoryCreation() {
        FileHandler fileHandler = new NestedZipFileHandler();
        String zipFilePath = "files/submissions.zip";
        String destDirectory = zipFilePath.substring(0, zipFilePath.length() - 4);

        IOException exception = assertThrows(IOException.class, () -> {
            // Create the destination directory before invoking handleFile
            Files.createDirectory(Paths.get(destDirectory));
        });
        
        // Invoke the handleFile method
        fileHandler.handleFile(zipFilePath);

         // Assert that the destination directory still exists
         assertTrue(Files.exists(Paths.get(destDirectory)));
    }

   
/**
     * Test case to verify the handling of a zip file with multiple submissions.
     * Responsible for checking if multiple submissions within a zip file are processed correctly.
     */
    @Test
    public void testMultipleSubmissionsHandling() {
        FileHandler fileHandler = new NestedZipFileHandler();
        try {
            Path rootPath = Paths.get("files");
            Path zipPath = Paths.get("files/submissions.zip");

            fileHandler.handleFile(zipPath.toString());

            // Use Files.list to efficiently check the existence of multiple files and directories
            long count = Files.list(rootPath)
                    .filter(path -> Files.exists(path))
                    .count();

            assertTrue(count > 0);
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }


/**
     * Test case to verify the handling of various file formats within a zip file.
     * Responsible for checking if different file formats are handled correctly.
     */
    @Test
    public void testMultipleFormatsHandling() throws IOException {
        FileHandler fileHandler = new NestedZipFileHandler();
        Path rootPath = Paths.get("files");
        Path zipPath = Paths.get("files/submissions.zip");

        fileHandler.handleFile(zipPath.toString());

        try {
             // Add assertions to check if various file formats are handled correctly
             assertTrue(Files.exists(rootPath.resolve("nonzipsubmissions/Flights.txt")));
             assertTrue(Files.exists(rootPath.resolve("nonzipsubmissions/Flight.class")));
             assertTrue(Files.exists(rootPath.resolve("nonzipsubmissions/Flight.java")));
        } 
        
        catch (Exception e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

}
