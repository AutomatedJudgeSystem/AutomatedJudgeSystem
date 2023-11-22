package automatedgrader.observer;

/**
 * The `TestObserver` interface represents an observer that receives updates
 * about the test results, including class name, pass/fail status, score, and feedback.
 */
public interface TestObserver {
    
    /**
     * This method is called to update the observer with information about a test.
     *
     * @param className The name of the class being tested.
     * @param passed    A boolean indicating whether the test passed or not.
     * @param score     The score obtained in the test.
     * @param feedback  Additional feedback or comments about the test.
     */
    void update(String className, boolean passed, double score, String feedback);
}

