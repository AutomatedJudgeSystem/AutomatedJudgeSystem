package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class LuggageManifestScoreStrategy implements ScoreCalculationStrategy {
    private Object luggageManifestObject; // Object representing the instance of LuggageManifest class

    public LuggageManifestScoreStrategy(String studentFolderPath) {
        this.luggageManifestObject = loadLuggageManifestClass(studentFolderPath);
    }

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

    // Helper method to load LuggageManifest class dynamically
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

    // Helper method to check attribute type
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = luggageManifestObject.getClass().getMethod(methodName).invoke(luggageManifestObject);
            return attribute instanceof ArrayList<?>;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to check getExcessLuggageCost method
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
