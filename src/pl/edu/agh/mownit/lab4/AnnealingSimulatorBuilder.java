package pl.edu.agh.mownit.lab4;

public class AnnealingSimulatorBuilder {
    private IProblem initialState;
    private TemperatureFunction tempFunction;
    private ProbabilityFunction probabilityFunction;
    private int maxIterations;

    public AnnealingSimulatorBuilder setInitialState(final IProblem initialState) {
        this.initialState = initialState;
        return this;
    }

    public AnnealingSimulatorBuilder setTempFunction(final TemperatureFunction tempFunction) {
        this.tempFunction = tempFunction;
        return this;
    }

    public AnnealingSimulatorBuilder setProbabilityFunction(final ProbabilityFunction probabilityFunction) {
        this.probabilityFunction = probabilityFunction;
        return this;
    }

    public AnnealingSimulatorBuilder setMaxIterations(final int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public AnnealingSimulator createAnnealingSimulator() {
        return new AnnealingSimulator(initialState, tempFunction, probabilityFunction, maxIterations);
    }
}