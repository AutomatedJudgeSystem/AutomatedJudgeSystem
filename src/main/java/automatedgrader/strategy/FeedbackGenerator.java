package automatedgrader.strategy;

public interface FeedbackGenerator {
    EvaluationResult createResult (String filePath);
    void generateFeedback(String filePath);
    void printResults(EvaluationResult evaluationResult);
    void TestPassed(EvaluationResult evaluationResult);
}

