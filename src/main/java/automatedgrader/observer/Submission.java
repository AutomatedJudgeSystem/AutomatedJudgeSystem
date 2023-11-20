package automatedgrader.observer;

import java.util.ArrayList;
import java.util.List;

import automatedgrader.strategy.EvaluationResult;

// Concrete Subject Class and Context Class for State Design Pattern
public class Submission implements SubmissionSubject {
    private String studentId;
    private String fileName;
    private int assignmentNumber;
    private double overallScore;
    private List<PDFObserver> observers;

    public Submission(String studentId, String fileName, int assignmentNumber) {
        this.studentId = studentId;
        this.fileName = fileName;
        this.assignmentNumber = assignmentNumber;
        this.observers = new ArrayList<>();
        // You can initialize other properties as needed
    }
    
    public void attachObserver(PDFObserver observer) {
        observers.add(observer);
    }

    public void detachObserver(PDFObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserver(List<EvaluationResult> testResults) {
        for (PDFObserver observer : observers) {
            observer.updatePDF(this, testResults);
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
