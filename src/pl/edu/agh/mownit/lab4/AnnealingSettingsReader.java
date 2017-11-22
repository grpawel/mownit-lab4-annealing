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
                .filter(line -> !line.isEmpty())
                .map(line -> line.split("\\s+"))
                .map(this::createSettingsFromLine);
    }

    private AnnealingSimulator createSettingsFromLine(final String[] tokens) {
        final double initialTemp = Double.parseDouble(tokens[3]);
        final int maxIterations = Integer.parseInt(tokens[4]);
        final AnnealingSettings annealingSettings = new AnnealingSettingsBuilder()
                .setInitialState(createInitialState(tokens[0]))
                .setProblemName(tokens[0])
                .setTempFunction(createTempFunction(tokens[1], initialTemp, maxIterations))
                .setTempFunctionName(tokens[1])
                .setInitialTemp(initialTemp)
                .setProbabilityFunction(createProbabilityFunction(tokens[2]))
                .setProbabilityFunctionName(tokens[2])
                .setMaxIterations(maxIterations)
                .createAnnealingSettings();
        return new AnnealingSimulator(annealingSettings);
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
            case "quadratic":
                return iteration -> - initialTemp / (maxIterations*maxIterations) * iteration * iteration + initialTemp;
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
