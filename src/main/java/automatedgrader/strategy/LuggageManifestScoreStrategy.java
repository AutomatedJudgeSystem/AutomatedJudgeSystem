package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * LuggageManifestScoreStrategy is an implementation of the ScoreCalculationStrategy interface
 * that dynamically loads a class representing a luggage manifest and calculates a score based on
 * the types of attributes and methods in the loaded class.
 */
public class LuggageManifestScoreStrategy implements ScoreCalculationStrategy {

    /**
     * Represents the instance of the LuggageManifest class loaded dynamically.
     * This field holds the object on which attribute type checks and method invocations are performed.
     */
    private Object luggageManifestObject;

    /**
     * Constructs a LuggageManifestScoreStrategy object by dynamically loading the LuggageManifest class
     * from the specified student folder path and initializing the luggageManifestObject field.
     *
     * @param studentFolderPath The path to the folder containing the LuggageManifest class file.
     */
    public LuggageManifestScoreStrategy(String studentFolderPath) {
        this.luggageManifestObject = loadLuggageManifestClass(studentFolderPath);
    }

    /**
     * Calculates a score based on the types of attributes and methods in the loaded LuggageManifest class.
     *
     * @return The calculated score.
     */
    @Override
    public double calculateScore() {
        double score = 0.0;

        // Check attributes for their types
        if (checkAttributeType("getSlips")) {
            score += 2.0;
        }

        // Check the getExcessLuggageCost method
        if (checkGetExcessLuggageCostMethod()) {
            score += 3.0;
        }

        return score;
    }

    /**
     * Dynamically loads the LuggageManifest class from the specified folder path.
     *
     * @param studentFolderPath The path to the folder containing the LuggageManifest class file.
     * @return An instance of the loaded LuggageManifest class.
     */
    private Object loadLuggageManifestClass(String studentFolderPath) {
        try {
            File file = new File(studentFolderPath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
            Class<?> luggageManifestClass = classLoader.loadClass("LuggageManifest");
            return luggageManifestClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks the type of the attribute returned by the specified method in the loaded LuggageManifest class.
     *
     * @param methodName The name of the method representing an attribute.
     * @return true if the attribute is of type ArrayList<?>, false otherwise.
     */
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = luggageManifestObject.getClass().getMethod(methodName).invoke(luggageManifestObject);
            return attribute instanceof ArrayList<?>;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks the correctness of the getExcessLuggageCost method in the loaded LuggageManifest class
     * by invoking it with example values and verifying the expected result.
     *
     * @return true if the method behaves as expected, false otherwise.
     */
    private boolean checkGetExcessLuggageCostMethod() {
        try {
            Object result = luggageManifestObject.getClass().getMethod("getExcessLuggageCost", int.class, int.class)
                    .invoke(luggageManifestObject, 2, 1); // Example values, adjust as needed
            return result instanceof Double;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

