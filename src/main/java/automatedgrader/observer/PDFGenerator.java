package automatedgrader.observer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import automatedgrader.strategy.EvaluationResult;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path; 
import java.util.List;

// Concrete Observer Class
public class PDFGenerator implements PDFObserver {
    
    public void updatePDF(Submission submission, List<EvaluationResult> testResults) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            String fileName = submission.getFileName();
            String studentId = submission.getStudentId();
            int assignmentNumber = submission.getAssignmentNumber();

            String pdfFileName = String.format("%s_%s_Assignment%d_feedback.pdf", studentId, fileName, assignmentNumber);

            String outputDirectory = "submissions";
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(20, 700);
                contentStream.showText("Feedback for " + studentId + ":");
                contentStream.newLineAtOffset(0, -20);

                for (EvaluationResult result : testResults) {
                    contentStream.showText(result.getTestName() + ": " + (result.isPassed() ? "Passed" : "Failed"));
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("Feedback: " + result.getFeedback());
                    contentStream.newLineAtOffset(0, -15);
                }

                contentStream.showText("Overall Score: " + submission.getOverallScore());
                contentStream.newLineAtOffset(0, -20);

                contentStream.showText("Generated on: " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                contentStream.endText();
            }

            // Save PDF in the submission folder
            Path outputDirectoryPath = FileSystems.getDefault().getPath(outputDirectory);
            if (!Files.exists(outputDirectoryPath)) {
                Files.createDirectories(outputDirectoryPath);
            }

            Path outputPath = outputDirectoryPath.resolve(pdfFileName);
            
            document.save(outputPath.toString());
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }
}
