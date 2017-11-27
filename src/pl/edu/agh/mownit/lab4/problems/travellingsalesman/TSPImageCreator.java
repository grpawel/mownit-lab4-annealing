package pl.edu.agh.mownit.lab4.problems.travellingsalesman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class TSPImageCreator {
    public void saveImage(final List<Point> points, final File file) throws IOException {
        final BufferedImage bufferedImage = createImage(points);
        saveImage(bufferedImage, file);
    }

    private BufferedImage createImage(final List<Point> points) {
        final int width = points.stream().mapToInt(point -> (int) point.getX()).max().getAsInt();
        final int height = points.stream().mapToInt(point -> (int) point.getY()).max().getAsInt();
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        for (int i = 0; i < points.size(); i++) {
            final Point point = points.get(i);
            final Point nextPoint = points.get((i + 1) % points.size());
            graphics.fillOval(point.x, point.y, 5, 5);
            graphics.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
        }
        return image;
    }

    private void saveImage(final BufferedImage image, final File file) throws IOException {
        ImageIO.write(image, "png", file);
    }
}
