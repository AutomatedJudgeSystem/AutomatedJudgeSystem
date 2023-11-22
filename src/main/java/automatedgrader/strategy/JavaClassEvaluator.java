package automatedgrader.strategy;

import java.util.List;

public interface JavaClassEvaluator {
    void evaluate(String javaCode);
    List<String> getEvaluationResults();
}
