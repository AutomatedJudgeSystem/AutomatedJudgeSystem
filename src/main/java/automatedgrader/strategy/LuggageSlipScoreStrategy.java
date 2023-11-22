package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LuggageSlipScoreStrategy implements ScoreCalculationStrategy {
    private Object luggageSlipObject; // Object representing the instance of LuggageSlip class

    public LuggageSlipScoreStrategy(String studentFolderPath) {
        this.luggageSlipObject = loadLuggageSlipClass(studentFolderPath);
    }

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

    // Helper method to load LuggageSlip class dynamically
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

    // Helper method to check attribute type
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


    
    // Helper method to check hasOwner method
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
