package automatedgrader.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The `TestSubject` class represents a subject that can be observed by `TestObserver`s.
 * It maintains a list of observers and notifies them when there are updates about test information.
 */
public class TestSubject {
    private List<TestObserver> observers = new ArrayList<>();

    /**
     * Adds a `TestObserver` to the list of observers.
     *
     * @param observer The `TestObserver` to be added.
     */
    public void addObserver(TestObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers with the provided test information.
     *
     * @param className Name of the test class.
     * @param passed    Indicates whether the test passed.
     * @param score     The score achieved by the test.
     * @param feedback  Additional feedback or comments about the test.
     */
    public void notifyObservers(String className, boolean passed, double score, String feedback) {
        for (TestObserver observer : observers) {
            observer.update(className, passed, score, feedback);
        }
    }

    /**
     * Retrieves the list of observers registered with this `TestSubject`.
     *
     * @return The list of `TestObserver`s.
     */
    public List<TestObserver> getObservers() {
        return observers;
    }
}

