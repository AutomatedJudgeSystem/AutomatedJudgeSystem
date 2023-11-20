package automatedgrader.template;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.*;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Handles the extraction of files from a nested zip archive.
 */
public class NestedZipFileHandler extends FileHandler{
    /**
     * Extracts files from a nested zip archive specified by the given path.
     *
     * @param zipFilePath The path to the nested zip file to be extracted.
     * @throws IOException If an I/O error occurs during extraction.
     */
    @Override
    protected void extract(String zipFilePath) throws IOException{        
        ZipInputStream in = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = in.getNextEntry();
        while(entry != null){
            String destFilePath = zipFilePath.substring(0, zipFilePath.length()-4) + File.separator + entry.getName();
            File file = new File(destFilePath);
            if(!file.exists()){
                Path destinationPath = Paths.get(destFilePath);
                Files.createDirectories(destinationPath.getParent());
                Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                if(getExtension(entry.getName()).equals("zip")){
                    handleFile(destFilePath);
                    Files.deleteIfExists(destinationPath);
                }
                if (entry.isDirectory()){
                    // if the entry is a directory, make the directory
                    File dir = new File(destFilePath);
                    dir.mkdirs();
                }
            }
            in.closeEntry();
            entry = in.getNextEntry();
        }
        in.close();
    }
}
