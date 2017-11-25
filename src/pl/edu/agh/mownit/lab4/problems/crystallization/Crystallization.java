package pl.edu.agh.mownit.lab4.problems.crystallization;

import pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods.Neighbourhood;

import java.util.Random;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class Crystallization {

    private final Pixel[][] image;

    private final int size;
    private final Neighbourhood neighbourhood;

    public Crystallization(final int size, final double density, final Neighbourhood neighbourhood) {
        this.size = size;
        this.image = new Pixel[size][size];
        this.neighbourhood = neighbourhood;
        generateImage(density);
    }

    private void generateImage(final double density) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                image[i][j] = Pixel.WHITE;
            }
        }
        final Random random = new Random();
        final long blackPixelsTarget = Math.round(size * size * density);
        long blackPixels = 0;
        while (blackPixels < blackPixelsTarget) {
            final int pos = random.nextInt(size * size);
            if (image[pos % size][pos / size] == Pixel.WHITE) {
                image[pos % size][pos / size] = Pixel.BLACK;
                blackPixels++;
            }
        }
    }

    public Pixel[][] getImage() {
        return image;
    }
}
