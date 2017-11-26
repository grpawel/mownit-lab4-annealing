package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.crystallization.Pixel;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class EmptySquare25 extends Neighbourhood {
    @Override
    protected double onePixelEnergy(final int x, final int y, final Pixel[][] pixels) {
        int blackPixels = 0;
        //top and bottom row
        for (int i = -2; i <= 2; i++) {
            blackPixels += isPixelBlack(pixels, x + i, y + 2);
            blackPixels += isPixelBlack(pixels, x + i, y - 2);
        }
        //left and right without corners
        for (int i = -1; i <= 1; i++) {
            blackPixels += isPixelBlack(pixels, x - 2, y + i);
            blackPixels += isPixelBlack(pixels, x + 2, y + i);
        }
        int whitePixels = 0;
        //inside square
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                whitePixels += isPixelWhite(pixels, x + i, y + j);
            }
        }

        return blackPixels + whitePixels;
    }
}
