package pl.edu.agh.mownit.lab4.annealing;

import pl.edu.agh.mownit.lab4.problems.IProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class AnnealingSimulator {
    private static final Logger LOGGER = Logger.getLogger(AnnealingSimulator.class.getName());
    private final AnnealingSettings settings;

    private IProblem finalState = null;
    private IProblem bestState;
    private double initialEnergy;
    private double bestStateEnergy;
    private int totalIterations = 0;
    private final List<Double> energyHistory = new ArrayList<>();

    public AnnealingSimulator(final AnnealingSettings settings) {
        this.settings = settings;
        bestState = settings.getInitialState();
        bestStateEnergy = settings.getInitialState().calculateEnergy();
    }

    public void simulate() {
        LOGGER.info("Starting simulation of " + getIdentifier());
        int iteration = 0;
        IProblem currentState = settings.getInitialState();
        double currentEnergy = currentState.calculateEnergy();
        initialEnergy = currentEnergy;
        while (iteration < settings.getMaxIterations() && !currentState.isSolved()) {
            energyHistory.add(currentEnergy);
            final Double currentTemp = settings.getTempFunction().calculate(iteration);
            final IProblem nextState = currentState.generateNextState();
            final double nextEnergy = nextState.calculateEnergy();
            if (currentEnergy > nextEnergy) {
                currentState = nextState;
                currentEnergy = nextEnergy;
                if (bestStateEnergy > nextEnergy) {
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
            if (iteration % 100000 == 0) {
                LOGGER.info(getIdentifier() + ": iteration " + iteration);
            }
        }
        energyHistory.add(currentEnergy);
        finalState = currentState;
        totalIterations = iteration;
        LOGGER.info("Finished. " + resultToString());
    }

    public IProblem getInitialState() {
        return settings.getInitialState();
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

    public String getIdentifier() {
        return settings.getIdentifier();
    }

    public List<Double> getEnergyHistory() {
        return energyHistory;
    }

    public double getInitialEnergy() {
        return initialEnergy;
    }

    public String resultToString() {
        return settings.toString() + "\t" + "iterations: " + totalIterations + " energy: " + bestStateEnergy +" initial: " + initialEnergy;
    }
}
