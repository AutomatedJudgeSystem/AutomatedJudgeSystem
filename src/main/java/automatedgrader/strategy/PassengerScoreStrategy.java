package automatedgrader.strategy;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Random;

public class PassengerScoreStrategy implements ScoreCalculationStrategy {
    private Object passengerObject; // Object representing the instance of Passenger class

    public PassengerScoreStrategy(String studentFolderPath) {
        this.passengerObject = loadPassengerClass(studentFolderPath);
    }

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

    // Helper method to load Passenger class dynamically
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

    // Helper method to check attribute type
    private boolean checkAttributeType(String methodName) {
        try {
            Object attribute = passengerObject.getClass().getMethod(methodName).invoke(passengerObject);
            return attribute instanceof String || attribute instanceof Integer || attribute instanceof Character;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to assign a random cabin class
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
