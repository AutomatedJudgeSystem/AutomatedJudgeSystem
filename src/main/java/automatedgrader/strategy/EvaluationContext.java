package automatedgrader.strategy;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EvaluationContext {
    private EvaluationStrategy evaluationStrategy;

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public void evaluateJavaClasses(String folderPath) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : directoryStream) {
                if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".java")) {
                    if (evaluationStrategy != null) {
                        evaluationStrategy.evaluate(filePath.toString());
                    } else {
                        System.err.println("No evaluation strategy set.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IOException during reading the directory: " + e.getMessage());
        }
    }

    // Additional method to integrate assignment evaluation
    public int evaluateAssignment(String filePath) {
        AssignmentEvaluator assignmentEvaluator = new AssignmentEvaluator(new CompositeCalculationStrategy(
                new NamingConventionEvaluation(),
                new ReturnTypeEvaluation(),
                new BehaviourEvaluation(),
                new InheritanceEvaluation(),
                new LuggageManifestCalculationStrategy(),
                new PassengerCalculationStrategy()
        ));
        return assignmentEvaluator.evaluateAssignment(filePath);
    }
}
