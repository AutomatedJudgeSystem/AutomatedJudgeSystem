package automatedgrader.observer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import automatedgrader.strategy.EvaluationResult;
import automatedgrader.strategy.FlightCalculationStrategy;
import automatedgrader.strategy.LuaggageSlipCalculationStrategy;
import automatedgrader.strategy.LuggageManifestCalculationStrategy;
import automatedgrader.strategy.PassengerCalculationStrategy;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import java.util.List;

// Concrete Observer Class
public class PDFGenerator extends EvaluationResult implements PDFObserver {

    public PDFGenerator(String testname, int total, String feedback, boolean status) {
        super(testname, total, feedback, status);
    }

    private static final String OUTPUT_DIRECTORY = "submissions";

    // Variable to store the result of the calculate method
    private int passengerScore;
    private int luggageSlipScore;
    private int luggageManifestScore;
    private int flightScore;

    public void updatePDF(Submission submission, EvaluationResult evaluationResult) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            String fileName = submission.getFileName();
            String studentId = submission.getStudentId();
            int assignmentNumber = submission.getAssignmentNumber();

            String pdfFileName = String.format("%s_%s_Assignment%d_feedback.pdf", studentId, fileName, assignmentNumber);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                addHeader(contentStream, studentId);
                addPassengerEvaluation(contentStream, evaluationResult);
                addLuggageSlipEvaluation(contentStream, evaluationResult);
                addLuggageManifestEvaluation(contentStream, evaluationResult);
                addFlightEvaluation(contentStream, evaluationResult);
                addTestResults(contentStream, evaluationResult);
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

    private void addTestResults(PDPageContentStream contentStream, EvaluationResult result) throws IOException {
            contentStream.showText(result.getTestName() + ": " + (isPassed(result) ? "Passed" : "Failed"));
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Feedback: " + result.getFeedback());
            contentStream.newLineAtOffset(0, -15);
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

    private void addPassengerEvaluation(PDPageContentStream contentStream, EvaluationResult evaluationResult) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Passsenger Class Evaluation: " + passengerScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Passsenger Class Feedback: " + evaluationResult.getFeedback());
    }

    private void addLuggageSlipEvaluation(PDPageContentStream contentStream, EvaluationResult evaluationResult) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("LuggageSlip Class Evaluation: " + luggageSlipScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("LuggageSlip Class Feedback: " + evaluationResult.getFeedback());
    }

    private void addLuggageManifestEvaluation(PDPageContentStream contentStream, EvaluationResult evaluationResult) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("LuggageManifest Class Evaluation: " + luggageManifestScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("LuggageManifest Class Feedback: " + evaluationResult.getFeedback());
    }

    private void addFlightEvaluation(PDPageContentStream contentStream, EvaluationResult evaluationResult) throws IOException{
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Flight Class Evaluation: " + flightScore);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Flight Class Feedback: " + evaluationResult.getFeedback());
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
        LuggageManifestCalculationStrategy luggageManifestStrategy = new LuggageManifestCalculationStrategy();
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