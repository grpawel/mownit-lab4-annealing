package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.crystallization.Pixel;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public abstract class Neighbourhood {
    public double calculateEnergy(final Pixel[][] pixels) {
        int energy = 0;
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                if (pixels[x][y] == Pixel.BLACK) {
                    energy += onePixelEnergy(x, y, pixels);
                }
            }
        }
        return energy;
    }

    protected abstract double onePixelEnergy(final int x, final int y, final Pixel[][] pixels);

    protected Pixel safeGetPixel(Pixel[][] pixels, int x, int y) {
        if (x < 0) {
            x += pixels.length;
        } else if (x >= pixels.length) {
            x -= pixels.length;
        }
        if (y < 0) {
            y += pixels[0].length;
        } else if (y >= pixels[0].length) {
            y -= pixels[0].length;
        }
        return pixels[x][y];
    }
    protected int isPixelBlack(Pixel[][] pixels, int x, int y) {
        return safeGetPixel(pixels, x, y) == Pixel.BLACK ? 1 : 0;
    }
}
