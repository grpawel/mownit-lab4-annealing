package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.crystallization.Pixel;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class HalfEmptyCross extends Neighbourhood {
    @Override
    protected double onePixelEnergy(final int x, final int y, final Pixel[][] pixels) {
        int blackPixels = 0;
        blackPixels += isPixelBlack(pixels, x + 1, y - 1);
        blackPixels += isPixelBlack(pixels, x + 1, y + 1);
        return blackPixels;
    }
}
