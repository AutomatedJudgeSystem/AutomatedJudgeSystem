package automatedgrader.strategy;

public interface FeedbackGenerator {
    EvaluationResult createResult (String filePath);
    void TestPassed(EvaluationResult evaluationResult);
    void generateFeedback(String filePath);
}

