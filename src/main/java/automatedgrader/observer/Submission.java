package automatedgrader.observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Submission class represents a student's assignment submission and
 * implements the SubmissionSubject interface to notify observers of changes.
 */
public class Submission implements SubmissionSubject {

    // Attributes
    private String studentName;
    private String studentId;
    private int assignmentNumber;
    private String fileName;
    private String filePath;  // New attribute for file path

    private List<PDFObserver> observers;

    /**
     * Constructs a new Submission object.
     *
     * @param studentName       The name of the student submitting the assignment.
     * @param studentId         The unique identifier for the student.
     * @param assignmentNumber  The assignment number.
     * @param fileName          The name of the submitted file.
     * @param filePath          The path to the submitted file.
     */
    public Submission(String studentName, String studentId, int assignmentNumber, String fileName, String filePath) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.assignmentNumber = assignmentNumber;
        this.fileName = fileName;
        this.filePath = filePath;
        this.observers = new ArrayList<>();
    }

    /**
     * Attaches a PDFObserver to receive notifications.
     *
     * @param observer  The observer to attach.
     */
    public void attachObserver(PDFObserver observer) {
        observers.add(observer);
    }

    /**
     * Detaches a PDFObserver from receiving notifications.
     *
     * @param observer  The observer to detach.
     */
    public void detachObserver(PDFObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all attached observers of changes in the submission.
     *
     * @param results  List of strings representing the results of the submission evaluation.
     */
    public void notifyObservers(List<String> results) {
        for (PDFObserver observer : observers) {
            try {
                observer.updatePDF(studentId, results, filePath);
            } catch (IOException e) {
                // Handle the exception or log it
                e.printStackTrace();
            }
        }
    }

    // Getter methods

    /**
     * Gets the unique identifier of the student.
     *
     * @return The student's unique identifier.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the name of the student.
     *
     * @return The name of the student.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the assignment number.
     *
     * @return The assignment number.
     */
    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    /**
     * Gets the name of the submitted file.
     *
     * @return The name of the submitted file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the path to the submitted file.
     *
     * @return The path to the submitted file.
     */
    public String getFilePath() {
        return filePath;
    }

    // Setter methods

    /**
     * Sets the unique identifier of the student.
     *
     * @param studentId  The new unique identifier for the student.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Sets the name of the student.
     *
     * @param name  The new name of the student.
     */
    public void setStudentName(String name) {
        this.studentName = name;
    }

    /**
     * Sets the name of the submitted file.
     *
     * @param fileName  The new name of the submitted file.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the assignment number.
     *
     * @param assignmentNumber  The new assignment number.
     */
    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    /**
     * Sets the path to the submitted file.
     *
     * @param filePath  The new path to the submitted file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
