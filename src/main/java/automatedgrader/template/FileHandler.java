package automatedgrader.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;

import com.github.javaparser.Problem;

public abstract class FileHandler {
    protected abstract void extract(String zipFilePath) throws IOException;

    //method to handle file extraction
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

    //returns the file extension of a given file
    protected static String getExtension(String filename){
        int index = filename.lastIndexOf('.');
        String extension = "";
        
        if (index > 0)
            extension = filename.substring(index+1);
            
        return extension;
    }

    //creates directory where extracted files would be stored
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

    //checks for the presence of various issues with the file to be extracted. return false if no errors are detected.
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