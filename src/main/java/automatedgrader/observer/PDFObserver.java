package automatedgrader.observer;

import java.util.List;
import automatedgrader.strategy.EvaluationResult;

/**
 * The PDFObserver interface represents an observer that is notified of changes
 * in a SubmissionSubject.
 */
public interface PDFObserver {

  /**
   * Updates the PDF with evaluation results for a specific submission.
   *
   * @param submission   The Submission for which the PDF is being updated.
   * @param testResults  List of EvaluationResult objects representing the
   *                     results of the submission evaluation.
   */
  void updatePDF(Submission submission, List<EvaluationResult> testResults);
}
