package automatedgrader.strategy;

public class CompositeCalculationStrategy implements CalculationStrategy {
    private CalculationStrategy[] strategies;

    public CompositeCalculationStrategy(CalculationStrategy... strategies) {
        this.strategies = strategies;
    }

    public CompositeCalculationStrategy(NamingConventionEvaluation namingConventionEvaluation,
            ReturnTypeEvaluation returnTypeEvaluation, BehaviourEvaluation behaviourEvaluation,
            InheritanceEvaluation inheritanceEvaluation,
            LuggageManifestCalculationsStrategy luggageManifestCalculationStrategy,
            PassengerCalculationStrategy passengerCalculationStrategy) {
    }

    @Override
    public int calculate(String filePath) {
        int totalScore = 0;
        for (CalculationStrategy strategy : strategies) {
            totalScore += strategy.calculate(filePath);
        }
        return totalScore;
    }
}
