package automatedgrader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import automatedgrader.template.NestedZipFileHandler;
import automatedgrader.strategy.*;

public class App {
    public static void main(String[] args) {
        //extracts all files from 'files/submissions.zip' and saves them to 'files/submissions'
        NestedZipFileHandler fileExtractor = new NestedZipFileHandler();
        String zipFilePath = "files/submissions.zip";
        try {
            fileExtractor.handleFile(zipFilePath);
        } 
        catch (IOException e) {
            System.err.println("Unexpected IOException: " + e.getMessage());
            e.printStackTrace();
        }

        AssignmentEvaluator e1 = new AssignmentEvaluator(new LuaggageSlipCalculationStrategy());

        File submissions = new File("files/submissions");
        File[] submissionFolders = submissions.listFiles();
        ArrayList <File> submissionFiles = new ArrayList<>();

        for(File submissionFile: submissionFolders){
            submissionFiles.addAll(Arrays.asList(submissionFile.listFiles()));
        }

        for(File submissionFile: submissionFiles){

            if(submissionFile.getName().endsWith(".java")){
                System.out.println(submissionFile.getName());
                e1.evaluateAssignment(submissionFile.getName());
                System.out.println("");
            }
        }
       
    }
        
        /*
        FileExtractor fileExtractor = new FileExtractor();
        try {
            fileExtractor.unzip("files/submissions.zip", "files/submissions");
        }
        catch(Exception e){
            System.out.println("error");
        }

        File submissions = new File("files/submissions");
        File[] submissionFolders = submissions.listFiles();
        ArrayList <File> submissionFiles = new ArrayList<>();

        for(File submissionFile: submissionFolders){
            submissionFiles.addAll(Arrays.asList(submissionFile.listFiles()));
        }


        for(File submissionFile: submissionFiles){
            //System.out.println(submissionFile);
            if(!submissionFile.isDirectory()){
                try {
                    // Specify the path to your Java file
                    FileInputStream fis = new FileInputStream(submissionFile);

                    // Parse the Java file using an instance of JavaParser
                    JavaParser javaParser = new JavaParser();
                    CompilationUnit cu = javaParser.parse(fis).getResult().orElse(null);

                    // Visit and print class information
                    if (cu != null) {
                        new ClassVisitor().visit(cu, null);
                    }

                    fis.close();
            }   
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
         }
         */
    
    

    // // Visitor to print class information
    // private static class ClassVisitor extends VoidVisitorAdapter<Void> {
    //     @Override
    //     public void visit(ClassOrInterfaceDeclaration n, Void arg) {
    //         System.out.println("Class Name: " + n.getAccessSpecifier().asString() + " " + n.getName());
        
    //         // Print fields
    //         System.out.println("Fields:");
    //         n.getFields().forEach(field -> {
    //             System.out.println("- " + field.getAccessSpecifier().asString() + " " + field.getVariables().get(0).getNameAsString());
    //         });
        
    //         // Print methods
    //         System.out.println("Methods:");
    //         n.getMethods().forEach(method -> {
    //             System.out.println("- " + method.getAccessSpecifier().asString()  + " " +  method.getName());
    //         });
        
    //         super.visit(n, arg);
    //     }
    // }
}
