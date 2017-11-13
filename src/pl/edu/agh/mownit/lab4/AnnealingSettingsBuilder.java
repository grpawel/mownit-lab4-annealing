package pl.edu.agh.mownit.lab4;

public class AnnealingSettingsBuilder {
    private IProblem initialState;
    private String problemName;
    private TemperatureFunction tempFunction;
    private Double initialTemp;
    private String tempFunctionName;
    private ProbabilityFunction probabilityFunction;
    private String probabilityFunctionName;
    private int maxIterations;

    public AnnealingSettingsBuilder setInitialState(final IProblem initialState) {
        this.initialState = initialState;
        return this;
    }

    public AnnealingSettingsBuilder setProblemName(final String problemName) {
        this.problemName = problemName;
        return this;
    }

    public AnnealingSettingsBuilder setTempFunction(final TemperatureFunction tempFunction) {
        this.tempFunction = tempFunction;
        return this;
    }

    public AnnealingSettingsBuilder setInitialTemp(final Double initialTemp) {
        this.initialTemp = initialTemp;
        return this;
    }

    public AnnealingSettingsBuilder setTempFunctionName(final String tempFunctionName) {
        this.tempFunctionName = tempFunctionName;
        return this;
    }

    public AnnealingSettingsBuilder setProbabilityFunction(final ProbabilityFunction probabilityFunction) {
        this.probabilityFunction = probabilityFunction;
        return this;
    }

    public AnnealingSettingsBuilder setProbabilityFunctionName(final String probabilityFunctionName) {
        this.probabilityFunctionName = probabilityFunctionName;
        return this;
    }

    public AnnealingSettingsBuilder setMaxIterations(final int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public AnnealingSettings createAnnealingSettings() {
        return new AnnealingSettings(initialState, problemName, tempFunction, initialTemp, tempFunctionName, probabilityFunction, probabilityFunctionName, maxIterations);
    }
}