package automatedgrader.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;

/**
 * The abstract base class for handling files in the Template Design Pattern.
 * Subclasses must implement the 'extract' method to define how files are extracted.
 */
public abstract class FileHandler {
    /**
     * Extracts files from a zip archive specified by the given path.
     *
     * @param zipFilePath The path to the zip file to be extracted.
     * @throws IOException If an I/O error occurs during extraction.
     */
    protected abstract void extract(String zipFilePath) throws IOException;

    /**
     * Handles the extraction of files from the specified zip file path.
     *
     * @param zipFilePath The path to the zip file to be extracted.
     * @throws IOException If an I/O error occurs during the extraction process.
     */
    public void handleFile(String zipFilePath) throws IOException{
        try {
            if(checkForErrors(zipFilePath) == false){
                    createDestinationDirectory(zipFilePath);
                    extract(zipFilePath);
            }
        }
        catch (IOException e) {
            throw e;
        }
    }

    /**
     * Returns the file extension of a given filename.
     *
     * @param filename The name of the file.
     * @return The file extension.
     */
    protected static String getExtension(String filename){
        int index = filename.lastIndexOf('.');
        String extension = "";
        
        if (index > 0)
            extension = filename.substring(index+1);
            
        return extension;
    }

    /**
     * Creates the destination directory where extracted files will be stored.
     *
     * @param zipFilePath The path to the zip file.
     * @throws IOException If an I/O error occurs while creating the directory.
     */
    protected void createDestinationDirectory(String zipFilePath) throws IOException{
        Path destDirectory = Paths.get(zipFilePath.substring(0, zipFilePath.length()-4));

        if (!Files.exists(destDirectory)) {
            File destDir = new File(zipFilePath.substring(0, zipFilePath.length()-4));
            if (!destDir.exists()) {
                destDir.mkdir();
            }
        }
        else{
            throw new IOException("The folder '" + zipFilePath.substring(0, zipFilePath.length()-4) + "' exists already. It's contents may be altered but not deleted.");
        }
    }

    /**
     * Checks for various issues with the file to be extracted.
     * Returns false if no errors are detected.
     *
     * @param zipFilePath The path to the zip file.
     * @return True if there are errors, false otherwise.
     * @throws IOException If an I/O error occurs during the error check.
     */
    protected boolean checkForErrors(String zipFilePath) throws IOException{
        if(!Files.exists(Paths.get(zipFilePath))){
            throw new IOException("The file at '" + zipFilePath + "' does not exist.");
        }
        if(!getExtension(zipFilePath).equals("zip")){
            throw new IOException("There is no zip file at '" + zipFilePath + "' to extract.");
        }
        ZipInputStream in = new ZipInputStream(new FileInputStream(zipFilePath));
        if(in.getNextEntry() == null){
            in.close();
            throw new IOException("The zip file at '" + zipFilePath + "' is empty.");
        }
        else{
            in.close();
            return false;
        }
    }
}
