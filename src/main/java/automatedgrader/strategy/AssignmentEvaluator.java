package automatedgrader.strategy;

/**
 * Evaluates assignments using a specified calculation strategy.
 */
public class AssignmentEvaluator {
    private CalculationStrategy calculationStrategy;

    /**
     * Initializes the AssignmentEvaluator with a specific calculation strategy.
     *
     * @param calculationStrategy The calculation strategy to be used.
     */
    public AssignmentEvaluator(CalculationStrategy calculationStrategy){
        this.calculationStrategy = calculationStrategy;
    }

    /**
     * Evaluates an assignment based on the provided file path.
     *
     * @param filePath The path to the file to be evaluated.
     * @return The calculated score.
     */
   public int evaluateAssignment(String filepath){
        return calculationStrategy.calculate(filepath); 
    }
}


