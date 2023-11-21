package automatedgrader.observer;

import java.util.List;

import automatedgrader.strategy.EvaluationResult;

// Subject Interface
public interface SubmissionSubject {
  void attachObserver(PDFObserver observer);
  void detachObserver(PDFObserver observer);
  void notifyObservers(List<EvaluationResult> testResults);
} 

