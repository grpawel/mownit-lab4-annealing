package pl.edu.agh.mownit.lab4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Created by Paweł Grochola on 13.11.2017.
 */

/**
 * File structure:
 * Each line contains separate settings.
 * Problem type / file name for sudoku, temperature function, probability function, initial temperature, max iterations
 * Eg:
 * sudoku1.txt linear boltzmann 30000 1000000
 */
public class AnnealingSettingsReader {
    private final String fileName;

    public AnnealingSettingsReader(final String fileName) {
        this.fileName = fileName;
    }

    public Stream<AnnealingSimulator> readFromFile() throws IOException {
        return Files.lines(new File(fileName).toPath())
                .map(String::trim)
                .map(line -> line.split("\\s+"))
                .map(this::createSettingsFromLine);
    }

    private AnnealingSimulator createSettingsFromLine(final String[] tokens) {
        final IProblem initialState = createInitialState(tokens[0]);
        final double initialTemp = Double.parseDouble(tokens[3]);
        final int maxIterations = Integer.parseInt(tokens[4]);
        final TemperatureFunction temperatureFunction = createTempFunction(tokens[1], initialTemp, maxIterations);
        final ProbabilityFunction probabilityFunction = createProbabilityFunction(tokens[2]);
        return new AnnealingSimulatorBuilder().setInitialState(initialState)
                .setMaxIterations(maxIterations)
                .setTempFunction(temperatureFunction)
                .setProbabilityFunction(probabilityFunction)
                .createAnnealingSimulator();
    }

    private IProblem createInitialState(final String problemType) {
        if (problemType.toLowerCase().contains("sudoku")) {
            return new SudokuReader(problemType).readFromFile();
        }
        return null;
    }

    private TemperatureFunction createTempFunction(final String functionName, final double initialTemp, final int maxIterations) {
        switch (functionName) {
            case "exp00001":
                return iteration -> initialTemp * Math.pow(0.99999, iteration);
            case "exp0001":
                return iteration -> initialTemp * Math.pow(0.9999, iteration);
            case "exp001":
                return iteration -> initialTemp * Math.pow(0.999, iteration);
            case "exp01":
                return iteration -> initialTemp * Math.pow(0.99, iteration);
            case "linear":
            default:
                return iteration -> -initialTemp * iteration * 1.0 / maxIterations + initialTemp;
        }
    }

    private ProbabilityFunction createProbabilityFunction(final String functionName) {
        switch (functionName) {
            case "boltzmann":
            default:
                return (temperature, currentEnergy, nextEnergy) -> Math.exp((currentEnergy - nextEnergy) / temperature) > Math.random();
        }
    }

}
