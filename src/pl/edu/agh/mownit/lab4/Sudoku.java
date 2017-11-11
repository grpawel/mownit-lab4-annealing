package pl.edu.agh.mownit.lab4;

import pl.edu.agh.mownit.lab4.utils.RandomSet;
import pl.edu.agh.mownit.lab4.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class Sudoku implements IProblem {
    private static final int SIZE = 9;
    private static final int SQUARE_SIZE = 3;
    private final int[][] digits;
    private final boolean[][] movable;
    private final Random random = new Random();

    /**
     * @param digits 2D array of digits; empty spaces should have zeroes
     *               in each square there must be at least two empty spaces
     */
    public Sudoku(final int[][] digits) {
        this.digits = digits;
        this.movable = new boolean[SIZE][SIZE];
        fillMovableArray();
        fillSquaresWithRandomDigits();
    }

    private Sudoku(final int[][] digits, final boolean[][] movable) {
        this.digits = digits;
        this.movable = movable;
    }

    @Override
    public IProblem generateNextState() {
        // 0 1 2
        // 3 4 5
        // 6 7 8
        // select square
        final int squareId = random.nextInt(SIZE);
        final int squareCol = squareId % SQUARE_SIZE;
        final int squareRow = squareId / SQUARE_SIZE;
        // find which indices can be swapped
        final List<Integer> indicesAvailableToSwap = IntStream.range(0, SIZE)
                .filter(i -> movable[squareCol + i % SQUARE_SIZE][squareRow + i / SQUARE_SIZE])
                .boxed()
                .collect(Collectors.toList());
        // pick two indices
        Collections.shuffle(indicesAvailableToSwap);
        final int col1 = indicesAvailableToSwap.get(0) % SQUARE_SIZE;
        final int row1 = indicesAvailableToSwap.get(0) / SQUARE_SIZE;
        final int col2 = indicesAvailableToSwap.get(1) % SQUARE_SIZE;
        final int row2 = indicesAvailableToSwap.get(1) / SQUARE_SIZE;
        // swap in copy of sudoku table
        final int[][] new_digits = Utils.deepCopy(digits);
        final int tmp = new_digits[col1][row1];
        new_digits[col1][row1] = new_digits[col2][row2];
        new_digits[col2][row2] = tmp;
        return new Sudoku(new_digits, movable);
    }

    @Override
    public double calculateEnergy() {
        return calculateRepeatingElementsInColumns()
                + calculateRepeatingElementsInRows();
    }


    private int calculateRepeatingElementsInRows() {
        int repeatingElements = 0;
        for (int row = 0; row < SIZE; row++) {
            final int row_final = row;
            final int distinctDigitsInRow = IntStream.range(0, SIZE)
                    .map(col -> digits[col][row_final])
                    .boxed()
                    .collect(Collectors.toSet())
                    .size();
            repeatingElements += SIZE - distinctDigitsInRow;
        }
        return repeatingElements;
    }

    private int calculateRepeatingElementsInColumns() {
        int repeatingElements = 0;
        for (int col = 0; col < SIZE; col++) {
            final int col_final = col;
            final int distinctDigitsInColumn = IntStream.range(0, SIZE)
                    .map(row -> digits[col_final][row])
                    .boxed()
                    .collect(Collectors.toSet())
                    .size();
            repeatingElements += SIZE - distinctDigitsInColumn;
        }
        return repeatingElements;
    }


    private void fillMovableArray() {
        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row < SIZE; row++) {
                movable[col][row] = digits[col][row] == 0;
            }
        }
    }

    /**
     * Fills squares with random values so in each square there is one digit from [1,9]
     */
    private void fillSquaresWithRandomDigits() {
        for (int squareCol = 0; squareCol < SIZE / SQUARE_SIZE; squareCol++) {
            for (int squareRow = 0; squareRow < SIZE / SQUARE_SIZE; squareRow++) {
                fillSquareWithRandomDigits(squareCol, squareRow);
            }
        }
    }


    private void fillSquareWithRandomDigits(int squareCol, int squareRow) {
        // find digits that are missing from square
        final RandomSet<Integer> missingDigits = new RandomSet<>(IntStream.range(1, SIZE + 1).boxed().collect(Collectors.toSet()));
        for (int col = squareCol; col < squareCol + SQUARE_SIZE; col++) {
            for (int row = squareRow; row < squareRow + SQUARE_SIZE; row++) {
                missingDigits.remove(digits[col][row]);
            }
        }
        // fill empty spaces with random digits
        for (int col = squareCol; col < squareCol + SQUARE_SIZE; col++) {
            for (int row = squareRow; row < squareRow + SQUARE_SIZE; row++) {
                if (digits[col][row] == 0) {
                    digits[col][row] = missingDigits.pollRandom(random);
                }
            }
        }
    }
}
