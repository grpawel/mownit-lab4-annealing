package pl.edu.agh.mownit.lab4.problems.crystallization;

import pl.edu.agh.mownit.lab4.problems.IProblem;
import pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods.Neighbourhood;
import pl.edu.agh.mownit.lab4.utils.Utils;

import java.util.Random;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class Crystallization implements IProblem{

    private final Pixel[][] image;

    private final int size;
    private final Neighbourhood neighbourhood;

    public Crystallization(final int size, final double density, final Neighbourhood neighbourhood) {
        this.size = size;
        this.image = new Pixel[size][size];
        this.neighbourhood = neighbourhood;
        generateImage(density);
    }

    private Crystallization(final int size, final Pixel[][] image, final Neighbourhood neighbourhood){
        this.size = size;
        this.image = image;
        this.neighbourhood = neighbourhood;
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

    @Override
    public IProblem generateNextState() {
        final Random random = new Random();
        final int randomPixel1 = random.nextInt(size * size);
        int randomPixel2;
        do {
            randomPixel2 = random.nextInt(size * size);
        }
        while(randomPixel1 == randomPixel2);
        //swap in new copy of image
        final Pixel[][] copy = Utils.deepCopy(image);
        final Pixel tmp = copy[randomPixel1 % size][randomPixel1 / size];
        copy[randomPixel1 % size][randomPixel1 / size] = copy[randomPixel2%size][randomPixel2/size];
        copy[randomPixel2 % size][randomPixel2 / size] = tmp;

        return new Crystallization(size, copy, neighbourhood);
    }

    @Override
    public double calculateEnergy() {
        return neighbourhood.calculateEnergy(image);
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    public Pixel[][] getImage() {
        return image;
    }
}
