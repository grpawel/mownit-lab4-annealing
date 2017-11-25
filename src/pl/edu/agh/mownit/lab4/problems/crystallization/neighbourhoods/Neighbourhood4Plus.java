package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.crystallization.Pixel;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class Neighbourhood4Plus extends Neighbourhood {
    @Override
    public double calculateEnergy(final Pixel[][] pixels) {
        int energy = 0;
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                energy += onePixelEnergy(x, y, pixels);
            }
        }
        return energy;
    }

    private double onePixelEnergy(final int x, final int y, final Pixel[][] pixels) {
        int blackPixels = 0;
        blackPixels += isPixelBlack(pixels, x-1,y);
        blackPixels += isPixelBlack(pixels, x,y-1);
        blackPixels += isPixelBlack(pixels, x,y+1);
        blackPixels += isPixelBlack(pixels, x+1,y);
        return blackPixels;
    }

}
