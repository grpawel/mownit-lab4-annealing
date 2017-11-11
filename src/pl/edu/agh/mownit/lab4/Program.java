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

        final int[][] table =
                {
                        {0, 0, 3, 0, 0, 0, 9, 4, 0},
                        {0, 1, 8, 6, 0, 0, 0, 7, 0},
                        {0, 0, 9, 0, 8, 4, 0, 5, 1},
                        {9, 0, 0, 0, 1, 0, 0, 6, 0},
                        {0, 2, 0, 7, 0, 0, 0, 1, 0},
                        {0, 0, 5, 0, 3, 0, 8, 0, 0},
                        {0, 3, 0, 0, 7, 1, 2, 0, 6},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 6, 0, 2, 0, 9, 0, 0, 4}};
        final Sudoku sudoku = new Sudoku(table);
        final AnnealingSimulator annealingSimulator = new AnnealingSimulator(sudoku, temperatureFunction, probabilityFunction, 10);
        annealingSimulator.simulate();
        System.out.println(annealingSimulator.getBestState());
    }
}
