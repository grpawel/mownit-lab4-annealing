package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.crystallization.Pixel;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class HorizontalLines extends Neighbourhood {
    @Override
    protected double onePixelEnergy(final int x, final int y, final Pixel[][] pixels) {
        int blackPixels = 0;
        for (int i = -1; i <= 1; i++) {
            blackPixels += isPixelBlack(pixels, x + i, y - 1);
            blackPixels += isPixelBlack(pixels, x + i, y + 1);
        }
        return blackPixels;
    }
}
