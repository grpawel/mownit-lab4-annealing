package pl.edu.agh.mownit.lab4;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class Program {
    public static void main(String[] args) {
        final int maxIterations = 10000;
        final int initialTemp = 30000;

        final TemperatureFunction temperatureFunction = (iteration) -> -initialTemp * iteration * 1.0 / maxIterations + initialTemp;
        final ProbabilityFunction probabilityFunction = (temperature, currentEnergy, nextEnergy) -> Math.exp((currentEnergy - nextEnergy) / temperature) > Math.random();

        final String fileName = "sudoku1.txt";
        final SudokuReader sudokuReader = new SudokuReader(fileName);
        final Sudoku sudoku = sudokuReader.readFromFile();
        final AnnealingSimulator annealingSimulator = new AnnealingSimulator(sudoku, temperatureFunction1, probabilityFunction, maxIterations);
        annealingSimulator.simulate();
        System.out.println(annealingSimulator.getBestState());
    }
}
