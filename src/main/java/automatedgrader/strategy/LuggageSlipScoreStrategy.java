package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * LuggageSlipScoreStrategy is an implementation of the ScoreCalculationStrategy interface
 * that dynamically loads a class representing a luggage slip and calculates a score based on
 * the types of attributes and methods in the loaded class.
 */
public class LuggageSlipScoreStrategy implements ScoreCalculationStrategy {

    /**
     * Represents the instance of the LuggageSlip class loaded dynamically.
     * This field holds the object on which attribute type checks and method invocations are performed.
     */
    private Object luggageSlipObject;

    /**
     * Constructs a LuggageSlipScoreStrategy object by dynamically loading the LuggageSlip class
     * from the specified student folder path and initializing the luggageSlipObject field.
     *
     * @param studentFolderPath The path to the folder containing the LuggageSlip class file.
     */
    public LuggageSlipScoreStrategy(String studentFolderPath) {
        this.luggageSlipObject = loadLuggageSlipClass(studentFolderPath);
    }

    /**
     * Calculates a score based on the types of attributes and methods in the loaded LuggageSlip class.
     *
     * @return The calculated score.
     */
    @Override
    public double calculateScore() {
        double score = 0.0;

        // Check attributes for their types
        if (checkAttributeType("getOwner")) {
            score += 1.0;
        }

        if (checkAttributeType("getLuggageSlipIDCounter")) {
            score += 1.0;
        }

        if (checkAttributeType("getLuggageSlipID")) {
            score += 1.0;
        }

        if (checkAttributeType("getLabel")) {
            score += 1.0;
        }

        // Check the hasOwner method
        if (checkHasOwnerMethod()) {
            score += 2.0;
        }

        return score;
    }

    /**
     * Dynamically loads the LuggageSlip class from the specified folder path.
     *
     * @param studentFolderPath The path to the folder containing the LuggageSlip class file.
     * @return An instance of the loaded LuggageSlip class.
     */
    private Object loadLuggageSlipClass(String studentFolderPath) {
        try {
            File file = new File(studentFolderPath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});

            // Dynamically load the Passenger class with its fully qualified name
            Class<?> passengerClass = classLoader.loadClass("projectv2.model.Passenger");

            // Create an instance of Passenger
            Object passengerInstance = passengerClass.getDeclaredConstructor().newInstance();

            // Dynamically load the LuggageSlip class
            Class<?> luggageSlipClass = classLoader.loadClass("projectv2.model.LuggageSlip");

            // Create an instance of LuggageSlip
            return luggageSlipClass.getDeclaredConstructor(passengerClass, int.class, String.class, String.class)
                    .newInstance(passengerInstance, 0, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks the type of the attribute returned by the specified method in the loaded LuggageSlip class.
     *
     * @param methodName The name of the method representing an attribute.
     * @return true if the attribute is of type Integer, String, or an array type, false otherwise.
     */
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = luggageSlipObject.getClass().getMethod(methodName).invoke(luggageSlipObject);

            // Check if the attribute is of a valid type
            return attribute instanceof Integer || attribute instanceof String ||
                    (attribute != null && attribute.getClass().isArray());  // Additional check for array types
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks the correctness of the hasOwner method in the loaded LuggageSlip class
     * by invoking it with an example passport number and verifying the expected result.
     *
     * @return true if the method behaves as expected, false otherwise.
     */
    private boolean checkHasOwnerMethod() {
        try {
            Object result = luggageSlipObject.getClass().getMethod("hasOwner", String.class).invoke(luggageSlipObject, "examplePassportNumber");
            return result instanceof Boolean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

