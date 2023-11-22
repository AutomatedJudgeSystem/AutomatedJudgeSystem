package automatedgrader.strategy;

import java.util.List;

/**
 * The `JavaClassEvaluator` interface defines the contract for classes that perform
 * evaluations on Java code and provide evaluation results.
 */
public interface JavaClassEvaluator {

    /**
     * Evaluates the provided Java code and performs specific checks or analyses.
     *
     * @param javaCode The Java code to evaluate.
     */
    void evaluate(String javaCode);

    /**
     * Gets the list of evaluation results generated during the evaluation process.
     *
     * @return The list of evaluation results.
     */
    List<String> getEvaluationResults();
}
