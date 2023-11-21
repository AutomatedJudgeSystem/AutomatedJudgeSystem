package automatedgrader.observer;

import java.util.ArrayList;
import java.util.List;

import automatedgrader.strategy.EvaluationResult;

public class Submission implements SubmissionSubject {
    private String studentId;
    private String fileName;
    private int assignmentNumber;
    private List<PDFObserver> observers;

    public Submission(String studentId, String fileName, int assignmentNumber) {
        this.studentId = studentId;
        this.fileName = fileName;
        this.assignmentNumber = assignmentNumber;
        this.observers = new ArrayList<>();
    }
    
    public void attachObserver(PDFObserver observer) {
        observers.add(observer);
    }

    public void detachObserver(PDFObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(EvaluationResult evaluationResult) {
        for (PDFObserver observer : observers) {
            observer.updatePDF(this, evaluationResult);
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }
}
