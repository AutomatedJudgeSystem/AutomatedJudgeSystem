package automatedgrader.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * TestSubject Class
 * 
 * Represents a subject being observed, allowing observers to be added and notified of changes.
 * 
 * Class-level:
 * - No additional class-level documentation.
 * 
 * Method-level:
 * - `void addObserver(TestObserver observer)`: 
 *   Adds a TestObserver to the list of observers.
 *   Parameters:
 *   - `observer`: The TestObserver to be added.
 * 
 * - `void notifyObservers(String className, boolean passed, double score, String feedback)`: 
 *   Notifies all registered observers with the provided test information.
 *   Parameters:
 *   - `className`: Name of the test class.
 *   - `passed`: Indicates whether the test passed.
 *   - `score`: The score achieved by the test.
 *   - `feedback`: Additional feedback or comments about the test.
 * 
 * Field-level:
 * - `private List<TestObserver> observers`: 
 *   Stores a list of TestObservers registered with the subject.
 */
public class TestSubject {
    private List<TestObserver> observers = new ArrayList<>();

    /**
     * Adds a TestObserver to the list of observers.
     * 
     * @param observer The TestObserver to be added.
     */
    public void addObserver(TestObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers with the provided test information.
     * 
     * @param className Name of the test class.
     * @param passed Indicates whether the test passed.
     * @param score The score achieved by the test.
     * @param feedback Additional feedback or comments about the test.
     */
    public void notifyObservers(String className, boolean passed, double score, String feedback) {
        for (TestObserver observer : observers) {
            observer.update(className, passed, score, feedback);
        }
    }
}
