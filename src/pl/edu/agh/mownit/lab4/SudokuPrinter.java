package pl.edu.agh.mownit.lab4;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class SudokuPrinter {
    public String print(final int[][] digits, final int size, final int squareSize) {
        final StringBuilder builder = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                builder.append(digits[col][row]);
                if( (col + 1) % squareSize == 0 && col + 1 != size) {
                    builder.append(" | ");
                } else {
                    builder.append("   ");
                }
            }
            builder.append(System.lineSeparator());
            if((row+1) % squareSize == 0 && (row+1) != size) {
                for(int i = 0; i < size * 4 - 3; i++) {
                    builder.append("-");
                }
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
