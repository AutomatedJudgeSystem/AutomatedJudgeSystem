package automatedgrader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PDFGenerator {
    public static void generateReport(String studentId, List<String> testResults, String studentFolderPath) throws IOException {
        Path studentPath = Paths.get(studentFolderPath);
        String pdfPath = studentPath.resolve(studentId + "SubmissionPDFReport.pdf").toString();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(20, 700);
                contentStream.showText("Student ID: " + studentId);
                contentStream.newLineAtOffset(0, -20);

                for (String result : testResults) {
                    contentStream.showText(result);
                    contentStream.newLineAtOffset(0, -15);
                }

                contentStream.endText();
            }

            // Save the document to the student's folder
            document.save(pdfPath);
        }
    }
}
