package pl.edu.agh.mownit.lab4.problems.crystallization;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class ImageCreator {
    public void saveImage(final Pixel[][] image, final File file) throws IOException {
        final BufferedImage bufferedImage = createImage(image);
        saveImage(bufferedImage, file);
    }

    private BufferedImage createImage(final Pixel[][] image) {
        final int width = image.length;
        final int height = image[0].length;
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                switch(image[x][y]) {
                    case BLACK:
                        bufferedImage.setRGB(x, y, Color.BLACK.getRGB());
                        break;
                    case WHITE:
                        bufferedImage.setRGB(x,y,Color.WHITE.getRGB());
                        break;
                }
            }
        }
        return bufferedImage;
    }

    private void saveImage(final BufferedImage image, final File file) throws IOException {
        ImageIO.write(image, "png", file);
    }
}
