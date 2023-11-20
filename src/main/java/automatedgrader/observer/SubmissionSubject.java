package automatedgrader.observer;

import java.util.List;
import automatedgrader.strategy.EvaluationResult;

/**
 * The SubmissionSubject interface represents a subject that allows observers
 * to attach, detach, and be notified of changes.
 */
public interface SubmissionSubject {

  /**
   * Attaches a PDFObserver to the subject.
   *
   * @param observer The PDFObserver to be attached.
   */
  void attachObserver(PDFObserver observer);

  /**
   * Detaches a PDFObserver from the subject.
   *
   * @param observer The PDFObserver to be detached.
   */
  void detachObserver(PDFObserver observer);

  /**
   * Notifies all attached PDFObservers of a change in the subject.
   */
  void notifyObserver(List<EvaluationResult> testResults);
}

