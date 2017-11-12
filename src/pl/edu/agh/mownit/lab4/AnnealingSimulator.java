package pl.edu.agh.mownit.lab4;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class AnnealingSimulator {
    private final IProblem initialState;
    private final TemperatureFunction tempFunction;
    //probability of accepting worse state, takes temperature as argument
    private final ProbabilityFunction probabilityFunction;

    private IProblem finalState = null;
    private IProblem bestState = null;
    private double bestStateEnergy = Double.MAX_VALUE;
    private int totalIterations = 0;

    private final int maxIterations;

    public AnnealingSimulator(final IProblem initialState, final TemperatureFunction tempFunction, final ProbabilityFunction probabilityFunction, final int maxIterations) {
        this.initialState = initialState;
        this.tempFunction = tempFunction;
        this.probabilityFunction = probabilityFunction;
        this.maxIterations = maxIterations;
    }

    public void simulate() {
        int iteration = 0;
        IProblem currentState = initialState;
        double currentEnergy = currentState.calculateEnergy();
        while (iteration < maxIterations) {
            final Double currentTemp = tempFunction.calculate(iteration);
            final IProblem nextState = currentState.generateNextState();
            final double nextEnergy = nextState.calculateEnergy();
            if (currentEnergy > nextEnergy) {
                currentState = nextState;
                currentEnergy = nextEnergy;
                if(bestStateEnergy > nextEnergy) {
                    bestState = nextState;
                    bestStateEnergy = nextEnergy;
                }
            } else {
                final Boolean acceptWorseSolution = probabilityFunction.calculate(currentTemp, currentEnergy, nextEnergy);
                if (acceptWorseSolution) {
                    currentState = nextState;
                    currentEnergy = nextEnergy;
                }
            }
            iteration++;
        }
        finalState = currentState;
        totalIterations = iteration;
    }

    public IProblem getFinalState() {
        return finalState;
    }

    public IProblem getBestState() {
        return bestState;
    }

    public int getTotalIterations() {
        return totalIterations;
    }
}
