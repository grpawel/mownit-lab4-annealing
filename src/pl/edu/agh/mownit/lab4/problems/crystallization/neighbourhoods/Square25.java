package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.crystallization.Pixel;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class Square25 extends Neighbourhood {
    @Override
    protected double onePixelEnergy(final int x, final int y, final Pixel[][] pixels) {
        int blackPixels = 0;
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                blackPixels += isPixelBlack(pixels, x + i, y + j);
            }
        }
        return blackPixels;
    }
}
