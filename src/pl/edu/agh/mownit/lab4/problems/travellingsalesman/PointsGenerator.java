package pl.edu.agh.mownit.lab4.problems.travellingsalesman;

import pl.edu.agh.mownit.lab4.utils.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Paweł Grochola on 26.11.2017.
 */
public class PointsGenerator {
    public enum Type {
        //NORMAL_DISTRIBUTION,
        UNIFORM,
        MULTIPLE_GROUPS
    }
    private final int size;
    private final int amount;
    private final Type type;

    public PointsGenerator(final int size, final int amount, final Type type) {
        this.size = size;
        this.amount = amount;
        this.type = type;
    }

    public List<Point> generate() {
        switch (type) {
            case UNIFORM:
                return generateUniform();
            case MULTIPLE_GROUPS:
                return generateGroups();
            default:
                throw new AssertionError("No branch for value of enum");
        }
    }

    private List<Point> generateUniform() {
        final Random random = new Random();
        final Stream<Integer> xs = random.ints(amount, 0, size).boxed();
        final Stream<Integer> ys = random.ints(amount, 0, size).boxed();
        return Utils.zip(xs, ys, Point::new).collect(Collectors.toList());
    }

    private List<Point> generateGroups() {
        final List<Point> points = new ArrayList<Point>();
        final int groupSize = size / 5;
        final Random random = new Random();
        for (int i = 0; i < amount; i++) {
            final int x = random.nextInt(size / 5);
            final int y = random.nextInt(size / 5);
            final int groupNumber = i % 9;
            if (0 <= groupNumber && groupNumber <= 2) {
                final int offsetY = groupNumber * 2 * groupSize;
                points.add(new Point(x, y + offsetY));
            }
            if (3 <= groupNumber && groupNumber <= 5) {
                final int offsetX = 2 * groupSize;
                final int offsetY = (groupNumber - 3) * 2 * groupSize;
                points.add(new Point(x + offsetX, y + offsetY));
            }
            if (groupNumber >= 6 && groupNumber <= 8) {
                final int offsetX = 4 * groupSize;
                final int offsetY = (groupNumber - 6) * 2 * groupSize;
                points.add(new Point(x + offsetX, y + offsetY));
            }
        }
        Collections.shuffle(points);
        return points;
    }
}
