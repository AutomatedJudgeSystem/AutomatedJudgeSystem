package automatedgrader.observer;
/**
 * TestObserver Interface
 * 
 * Defines the contract for objects observing and reacting to changes in test results.
 * 
 * How to use:
 * Implementations should override the 'update' method to handle test result updates.
 * Example:
 * ```java
 * public class MyObserver implements TestObserver {
 *    @Override
 *    public void update(String className, boolean passed, double score, String feedback) {
 *        // Custom logic to handle test result updates
 *    }
 * }
 * ```
 * 
 * Method:
 * - `void update(String className, boolean passed, double score, String feedback)`: 
 *   Notifies the observer about a change in test result.
 *   Parameters:
 *   - `className`: Name of the test class.
 *   - `passed`: Indicates whether the test passed.
 *   - `score`: The score achieved by the test.
 *   - `feedback`: Additional feedback or comments about the test.
 */
public interface TestObserver {
    void update(String className, boolean passed, double score, String feedback);
}
