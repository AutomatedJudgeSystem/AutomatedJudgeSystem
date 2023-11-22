package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;

/**
 * The `FlightScoreStrategy` class is an implementation of the `ScoreCalculationStrategy` interface.
 * It dynamically loads a class representing a flight and calculates a score based on the types of attributes
 * and methods in the loaded class.
 */
public class FlightScoreStrategy implements ScoreCalculationStrategy {

    /**
     * Represents the instance of the Flight class loaded dynamically.
     * This field holds the object on which attribute type checks and method invocations are performed.
     */
    private Object flightObject;

    /**
     * Constructs a `FlightScoreStrategy` object by dynamically loading the Flight class
     * from the specified student folder path and initializing the `flightObject` field.
     *
     * @param studentFolderPath The path to the folder containing the Flight class file.
     */
    public FlightScoreStrategy(String studentFolderPath) {
        this.flightObject = loadFlightClass(studentFolderPath);
    }

    /**
     * Calculates a score based on the types of attributes and methods in the loaded Flight class.
     *
     * @return The calculated score.
     */
    @Override
    public double calculateScore() {
        double score = 0.0;

        // Check attributes for their types
        if (checkAttributeType("getFlightNo")) {
            score += 1.0;
        }

        if (checkAttributeType("getDestination")) {
            score += 1.0;
        }

        if (checkAttributeType("getOrigin")) {
            score += 1.0;
        }

        if (checkAttributeType("getFlightDate")) {
            score += 1.0;
        }

        // Check the `getAllowedLuggage` method
        if (checkGetAllowedLuggageMethod()) {
            score += 2.0;
        }

        return score;
    }

    /**
     * Dynamically loads the Flight class from the specified folder path.
     *
     * @param studentFolderPath The path to the folder containing the Flight class file.
     * @return An instance of the loaded Flight class.
     */
    private Object loadFlightClass(String studentFolderPath) {
        try {
            File file = new File(studentFolderPath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
            Class<?> flightClass = classLoader.loadClass("Flight");
            return flightClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks the type of the attribute returned by the specified method in the loaded Flight class.
     *
     * @param methodName The name of the method representing an attribute.
     * @return `true` if the attribute is of type `String` or `LocalDateTime`, `false` otherwise.
     */
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = flightObject.getClass().getMethod(methodName).invoke(flightObject);
            return attribute instanceof String || attribute instanceof LocalDateTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks the correctness of the `getAllowedLuggage` method in the loaded Flight class
     * by invoking it with different cabin classes and verifying the expected results.
     *
     * @return `true` if the method behaves as expected, `false` otherwise.
     */
    private boolean checkGetAllowedLuggageMethod() {
        try {
            char cabinClass = 'F';
            Object result = flightObject.getClass().getMethod("getAllowedLuggage", char.class).invoke(flightObject, cabinClass);

            switch (cabinClass) {
                case 'F':
                    return (int) result == 3;
                case 'B':
                    return (int) result == 2;
                case 'P':
                    return (int) result == 1;
                case 'E':
                    return (int) result == 0;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
