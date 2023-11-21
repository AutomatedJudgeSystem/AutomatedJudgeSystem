package automatedgrader.observer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import automatedgrader.strategy.EvaluationResult;
import automatedgrader.strategy.FlightCalculationStrategy;
import automatedgrader.strategy.LuaggageSlipCalculationStrategy;
import automatedgrader.strategy.LuggageManifestCalculationsStrategy;
import automatedgrader.strategy.PassengerCalculationStrategy;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Concrete Observer Class
public class PDFGenerator implements PDFObserver {

    private static final String OUTPUT_DIRECTORY = "submissions";

    // Variable to store the result of the calculate method
    private int passengerScore;
    private int luggageSlipScore;
    private int luggageManifestScore;
    private int flightScore;
    EvaluationResult result = new EvaluationResult();

    public void updatePDF(Submission submission, List<EvaluationResult> testResults) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            String fileName = submission.getFileName();
            String studentId = submission.getStudentId();
            int assignmentNumber = submission.getAssignmentNumber();

            String pdfFileName = String.format("%s_%s_Assignment%d_feedback.pdf", studentId, fileName, assignmentNumber);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                addHeader(contentStream, studentId);
                addPassengerEvaluation(contentStream, testResults);
                addLuggageSlipEvaluation(contentStream, testResults);
                addLuggageManifestEvaluation(contentStream, testResults);
                addFlightEvaluation(contentStream, testResults);
                addTestResults(contentStream, testResults);
                addOverallScore(contentStream, submission.getOverallScore());
                addGeneratedDate(contentStream);
        
                // Save PDF in folder
                savePDF(document, pdfFileName);
        document.close();               
            }
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private void addHeader(PDPageContentStream contentStream, String studentId) throws IOException {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(20, 700);
        contentStream.showText("Feedback for " + studentId + ":");
        contentStream.newLineAtOffset(0, -20);
    }

    private void addTestResults(PDPageContentStream contentStream, List<EvaluationResult> testResults) throws IOException {
        for (EvaluationResult result : testResults) {
            contentStream.showText(result.getTestName() + ": " + (result.isPassed() ? "Passed" : "Failed"));
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Feedback: " + result.getFeedback());
            contentStream.newLineAtOffset(0, -15);
        }
    }

    private void addOverallScore(PDPageContentStream contentStream, double overallScore) throws IOException {
        contentStream.showText("Overall Score: " + overallScore);
        contentStream.newLineAtOffset(0, -20);
    }

    private void addGeneratedDate(PDPageContentStream contentStream) throws IOException {
        contentStream.showText("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        contentStream.endText();
        contentStream.close();
    }

    private void addPassengerEvaluation(PDPageContentStream contentStream, List<EvaluationResult> testResults) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Passsenger Class Evaluation: " + passengerScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Passsenger Class Feedback: " + result.getFeedback());
    }

    private void addLuggageSlipEvaluation(PDPageContentStream contentStream, List<EvaluationResult> testResults) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("LuggageSlip Class Evaluation: " + luggageSlipScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("LuggageSlip Class Feedback: " + result.getFeedback());
    }

    private void addLuggageManifestEvaluation(PDPageContentStream contentStream, List<EvaluationResult> testResults) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("LuggageManifest Class Evaluation: " + luggageManifestScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("LuggageManifest Class Feedback: " + result.getFeedback());
    }

    private void addFlightEvaluation(PDPageContentStream contentStream, List<EvaluationResult> testResults) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Flight Class Evaluation: " + flightScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Flight Class Feedback: " + result.getFeedback());
        contentStream.newLineAtOffset(0, -30);
    }

    private void savePDF(PDDocument document, String pdfFileName) throws IOException {
        Path outputDirectoryPath = FileSystems.getDefault().getPath(OUTPUT_DIRECTORY);
        if (!Files.exists(outputDirectoryPath)) {
            Files.createDirectories(outputDirectoryPath);
        }
        Path outputPath = outputDirectoryPath.resolve(pdfFileName);
        document.save(outputPath.toString());
    }

    //method to calculate Passenger score using PassengerCalculationStrategy
    private int calculatePassengerScore(String filePath) {
        // instance of PassengerCalculationStrategy
        PassengerCalculationStrategy passengerStrategy = new PassengerCalculationStrategy();
        // Use the calculate method
        passengerScore = passengerStrategy.calculate(filePath);
        return passengerScore;
    }

    private int calculateLuggageSlipScore(String filePath) {
        //instance of PassengerCalculationStrategy
        LuaggageSlipCalculationStrategy luggageSlipStrategy = new LuaggageSlipCalculationStrategy();
        // Use the calculate method
        luggageSlipScore = luggageSlipStrategy.calculate(filePath);
        return luggageSlipScore;
    }

    private int calculateLuggageManifestScore(String filePath) {
        //instance of PassengerCalculationStrategy
        LuggageManifestCalculationsStrategy luggageManifestStrategy = new LuggageManifestCalculationsStrategy();
        // Use the calculate method
        luggageManifestScore = luggageManifestStrategy.calculate(filePath);

        return luggageManifestScore;
    }

    private int calculateFlightScore(String filePath) {
        //instance of PassengerCalculationStrategy
        FlightCalculationStrategy flightStrategy = new FlightCalculationStrategy();
        // Use the calculate method
        flightScore = flightStrategy.calculate(filePath);
        return flightScore;
    }

    private void handleIOException(IOException e) {
        // Handle IOException
        e.printStackTrace();
    }
}