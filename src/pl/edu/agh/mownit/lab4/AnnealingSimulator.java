package pl.edu.agh.mownit.lab4;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class AnnealingSimulator {
    private final AnnealingSettings settings;

    private IProblem finalState = null;
    private IProblem bestState = null;
    private double bestStateEnergy = Double.MAX_VALUE;
    private int totalIterations = 0;

    public AnnealingSimulator(final AnnealingSettings settings) {
        this.settings = settings;
    }

    public void simulate() {
        int iteration = 0;
        IProblem currentState = settings.getInitialState();
        double currentEnergy = currentState.calculateEnergy();
        while (iteration < settings.getMaxIterations() && !currentState.isSolved()) {
            final Double currentTemp = settings.getTempFunction().calculate(iteration);
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
                final Boolean acceptWorseSolution = settings.getProbabilityFunction().calculate(currentTemp, currentEnergy, nextEnergy);
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

    public String resultToString() {
        return settings.toString() + "\t" + "iterations: " + totalIterations + " energy: " + bestStateEnergy;
    }
}
