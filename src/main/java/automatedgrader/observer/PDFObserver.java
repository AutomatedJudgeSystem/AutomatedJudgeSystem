package automatedgrader.observer;

import java.io.IOException;
import java.util.List;

/**
 * The PDFObserver interface represents an observer that is notified of changes
 * in a SubmissionSubject.
 */
public interface PDFObserver {

    /**
     * Updates the PDF with evaluation results for a specific submission.
     *
     * @param studentId          The unique identifier for the student.
     * @param Results            List of strings representing the results of the submission evaluation.
     * @param studentFolderPath  The path to the folder where the student's files are located.
     * @throws IOException       If an I/O error occurs while updating the PDF.
     */
    void updatePDF(String studentId, List<String> Results, String studentFolderPath) throws IOException;
}
