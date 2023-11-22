package automatedgrader.observer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Concrete Strategy Class for generating PDF reports based on grading results.
 * Implements the PDFObserver interface.
 */
public class PDFGenerator implements PDFObserver {

    /**
     * Updates the PDF document with grading results for a specific student.
     *
     * @param studentId          The unique identifier for the student.
     * @param results            The list of grading results for the student.
     * @param studentFolderPath  The path to the folder where the student's files are located.
     * @throws IOException      If an I/O error occurs while creating or writing to the PDF.
     */
    @Override
    public void updatePDF(String studentId, List<String> results, String studentFolderPath) throws IOException {
        // Resolve the path for the PDF document based on student information
        Path studentPath = Paths.get(studentFolderPath);
        String pdfPath = studentPath.resolveSibling(studentId + "SubmissionPDFReport.pdf").toString();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(20, 700);
                contentStream.showText("Student ID: " + studentId);
                contentStream.newLineAtOffset(0, -20);

                // Add grading results to the PDF
                for (String result : results) {
                    contentStream.showText(result);
                    contentStream.newLineAtOffset(0, -15);
                }

                contentStream.showText("Generated on: " +
                        java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                contentStream.endText();
            }

            // Save the document to the student's folder
            document.save(pdfPath.toString());
        }
    }
}


 