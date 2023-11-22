package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;

public class FlightScoreStrategy implements ScoreCalculationStrategy {
    private Object flightObject; // Object representing the instance of Flight class

    public FlightScoreStrategy(String studentFolderPath) {
        this.flightObject = loadFlightClass(studentFolderPath);
    }

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

        // Check the getAllowedLuggage method
        if (checkGetAllowedLuggageMethod()) {
            score += 2.0;
        }

        return score;
    }

    // Helper method to load Flight class dynamically
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

    // Helper method to check attribute type
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = flightObject.getClass().getMethod(methodName).invoke(flightObject);
            return attribute instanceof String || attribute instanceof LocalDateTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to check getAllowedLuggage method
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
