package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Random;

/**
 * PassengerScoreStrategy is an implementation of the ScoreCalculationStrategy interface
 * that dynamically loads a class representing a passenger and calculates a score based on
 * the types of attributes and methods in the loaded class.
 */
public class PassengerScoreStrategy implements ScoreCalculationStrategy {

    /**
     * Represents the instance of the Passenger class loaded dynamically.
     * This field holds the object on which attribute type checks and method invocations are performed.
     */
    private Object passengerObject;

    /**
     * Constructs a PassengerScoreStrategy object by dynamically loading the Passenger class
     * from the specified student folder path and initializing the passengerObject field.
     *
     * @param studentFolderPath The path to the folder containing the Passenger class file.
     */
    public PassengerScoreStrategy(String studentFolderPath) {
        this.passengerObject = loadPassengerClass(studentFolderPath);
    }

    /**
     * Calculates a score based on the types of attributes and methods in the loaded Passenger class.
     *
     * @return The calculated score.
     */
    @Override
    public double calculateScore() {
        double score = 0.0;

        // Check attributes for their types
        if (checkAttributeType("getPassportNumber")) {
            score += 1.0;
        }

        if (checkAttributeType("getFlightNo")) {
            score += 1.0;
        }

        if (checkAttributeType("getFirstName")) {
            score += 1.0;
        }

        if (checkAttributeType("getLastName")) {
            score += 1.0;
        }

        if (checkAttributeType("getNumLuggage")) {
            score += 1.0;
        }

        if (checkAttributeType("getCabinClass")) {
            score += 1.0;
        }

        // Assign a random cabin class
        assignRandomCabinClass();
        score += 2.0;

        return score;
    }

    /**
     * Dynamically loads the Passenger class from the specified folder path.
     *
     * @param studentFolderPath The path to the folder containing the Passenger class file.
     * @return An instance of the loaded Passenger class.
     */
    private Object loadPassengerClass(String studentFolderPath) {
        try {
            File file = new File(studentFolderPath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
            Class<?> passengerClass = classLoader.loadClass("Passenger");
            return passengerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks the type of the attribute returned by the specified method in the loaded Passenger class.
     *
     * @param methodName The name of the method representing an attribute.
     * @return true if the attribute is of type String, Integer, or Character, false otherwise.
     */
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = passengerObject.getClass().getMethod(methodName).invoke(passengerObject);
            return attribute instanceof String || attribute instanceof Integer || attribute instanceof Character;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Assigns a random cabin class to the loaded Passenger class using the setCabinClass method.
     */
    private void assignRandomCabinClass() {
        char[] cabinClasses = {'F', 'B', 'P', 'E'};
        Random random = new Random();
        char randomCabinClass = cabinClasses[random.nextInt(cabinClasses.length)];
        try {
            passengerObject.getClass().getMethod("setCabinClass", char.class).invoke(passengerObject, randomCabinClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

