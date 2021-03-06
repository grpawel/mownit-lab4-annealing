package pl.edu.agh.mownit.lab4.annealing;

import pl.edu.agh.mownit.lab4.problems.IProblem;

/**
 * Created by Paweł Grochola on 13.11.2017.
 */
public class AnnealingSettings {
    private final IProblem initialState;
    private final String problemName;
    private final TemperatureFunction tempFunction;
    private final Double initialTemp;
    private final String tempFunctionName;
    //probability of accepting worse state, takes temperature as argument
    private final ProbabilityFunction probabilityFunction;
    private final String probabilityFunctionName;
    private final int maxIterations;
    private final String identifier;

    public AnnealingSettings(final IProblem initialState, final String problemName, final TemperatureFunction tempFunction, final Double initialTemp, final String tempFunctionName, final ProbabilityFunction probabilityFunction, final String probabilityFunctionName, final int maxIterations, final String identifier) {
        this.initialState = initialState;
        this.problemName = problemName;
        this.tempFunction = tempFunction;
        this.initialTemp = initialTemp;
        this.tempFunctionName = tempFunctionName;
        this.probabilityFunction = probabilityFunction;
        this.probabilityFunctionName = probabilityFunctionName;
        this.maxIterations = maxIterations;
        this.identifier = identifier;
    }

    public IProblem getInitialState() {
        return initialState;
    }

    public String getProblemName() {
        return problemName;
    }

    public TemperatureFunction getTempFunction() {
        return tempFunction;
    }

    public Double getInitialTemp() {
        return initialTemp;
    }

    public String getTempFunctionName() {
        return tempFunctionName;
    }

    public ProbabilityFunction getProbabilityFunction() {
        return probabilityFunction;
    }

    public String getProbabilityFunctionName() {
        return probabilityFunctionName;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return problemName + ": " + " initialTemp: " +initialTemp + " temp: " + tempFunctionName + " " + probabilityFunctionName;
    }
}
