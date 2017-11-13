package pl.edu.agh.mownit.lab4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Paweł Grochola on 12.11.2017.
 */
public class SudokuReader {
    private final String fileName;

    public SudokuReader(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads sudoku from file. Empty fields can be any token different from digits.
     *
     * @return Sudoku with values from file
     * @throws SudokuFileException
     */
    public Sudoku readFromFile() throws SudokuFileException {
        final int[][] sudokuTable;
        final Set<String> possibleDigits = IntStream.range(1, Sudoku.SIZE + 1)
                .mapToObj(Integer::toString)
                .collect(Collectors.toSet());

        try {
            //returning an int[][] does not wo
            sudokuTable = Files.lines(new File(fileName).toPath())
                    .map(String::trim)
                    .map(line -> line.split("\\s+"))
                    .map(tokens -> Arrays.stream(tokens)
                            .mapToInt(token -> possibleDigits.contains(token) ? Integer.parseInt(token) : 0)
                            .toArray())
                    .toArray(int[][]::new);
        } catch (IOException e) {
            throw new SudokuFileException(e);
        }
        assertCorrectSudoku(sudokuTable);
        return new Sudoku(sudokuTable);
    }

    private void assertCorrectSudoku(final int[][] sudokuTable) {
        if (sudokuTable.length != Sudoku.SIZE) {
            throw new SudokuFileException(String.format("Wrong number of lines in file '%s': %d", fileName, sudokuTable.length));
        }
        for (int[] row : sudokuTable) {
            if (row.length != Sudoku.SIZE) {
                throw new SudokuFileException(String.format("Too many digits in file '%s' line '%s': %d", fileName, Arrays.toString(row), row.length));
            }
        }
    }

    public static class SudokuFileException extends RuntimeException {
        public SudokuFileException(final String message) {
            super(message);
        }

        public SudokuFileException(final Throwable cause) {
            super(cause);
        }
    }
}
