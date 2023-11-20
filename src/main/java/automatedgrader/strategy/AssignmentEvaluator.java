public class AssignmentEvaluator {
    private CalculationStrategy calculationStrategy;

    public AssignmentEvaluator(CalculationStrategy calculationStrategy){
        this.calculationStrategy = calculationStrategy;
    }

    public int evaluateAssignment(String filepath){
        return calculationStrategy.calculate(filepath);
    }
}
