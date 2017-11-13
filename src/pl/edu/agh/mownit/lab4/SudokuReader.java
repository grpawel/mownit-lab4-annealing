package pl.edu.agh.mownit.lab4;

import java.io.*;
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
     * @return Sudoku with values from file
     * @throws SudokuFileException
     */
    public Sudoku readFromFile() throws SudokuFileException {
        final File file = new File(fileName);
        int rowNumber = 0;
        final int[][] puzzle = new int[Sudoku.SIZE][Sudoku.SIZE];
        final Set<String> possibleDigits = IntStream.range(1, Sudoku.SIZE + 1)
                .mapToObj(Integer::toString)
                .collect(Collectors.toSet());

        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (rowNumber == Sudoku.SIZE) {
                    throw new SudokuFileException(String.format("Too many rows in file %s", fileName));
                }
                final int[] sudokuRow = Arrays.stream(line.split("\\s+"))
                        .map(digit -> possibleDigits.contains(digit) ? Integer.parseInt(digit) : 0)
                        .mapToInt(Integer::intValue)
                        .toArray();
                if (sudokuRow.length != Sudoku.SIZE) {
                    throw new SudokuFileException(String.format("Wrong number of digits in row %d in %s: '%s'", rowNumber, fileName, line));
                }
                puzzle[rowNumber] = sudokuRow;
                rowNumber++;
            }
        } catch (IOException e) {
            throw new SudokuFileException(e);
        }
        return new Sudoku(puzzle);
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
