package project.strategy;

//import java.io.IOException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;

public class EvaluationContext {
    private EvaluationStrategy evaluationStrategy;

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public void evaluateJavaClasses(String filePath) {
        if (evaluationStrategy != null) {
            evaluationStrategy.evaluate(filePath);
        } else {
            System.err.println("No evaluation strategy set.");
        }
    }
}