package pl.edu.agh.mownit.lab4;

import java.util.function.Function;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class AnnealingSimulator {
    private final IProblem initialState;
    private final Function<Integer, Double> tempFunction;
    //probability of accepting worse state, takes temperature as argument
    private final Function<Double, Boolean> probabilityFunction;

    private IProblem finalState = null;
    private IProblem bestState = null;
    private int totalIterations = 0;


    private final int maxIterations;

    public AnnealingSimulator(final IProblem initialState, final Function<Integer, Double> tempFunction, final Function<Double, Boolean> probabilityFunction, final int maxIterations) {
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
            final Double currentTemp = tempFunction.apply(iteration);
            final IProblem nextState = currentState.generateNextState();
            final double nextEnergy = nextState.calculateEnergy();
            if (currentEnergy > nextEnergy) {
                currentState = nextState;
                currentEnergy = nextEnergy;
                bestState = nextState;
            } else {
                final Boolean acceptWorseSolution = probabilityFunction.apply(currentTemp);
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
