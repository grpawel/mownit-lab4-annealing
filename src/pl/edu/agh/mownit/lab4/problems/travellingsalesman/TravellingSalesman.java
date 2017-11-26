package pl.edu.agh.mownit.lab4.problems.travellingsalesman;

import pl.edu.agh.mownit.lab4.problems.IProblem;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Paweł Grochola on 26.11.2017.
 */
public class TravellingSalesman implements IProblem {
    public enum SwapStrategy {
        CONSECUTIVE,
        ARBITRARY
    }

    private final List<Point> points;
    private final SwapStrategy swapStrategy;
    private final Random random = new Random();

    public TravellingSalesman(final PointsGenerator generator, final SwapStrategy swapStrategy) {
        points = generator.generate();
        this.swapStrategy = swapStrategy;
    }

    private TravellingSalesman(final List<Point> points, final SwapStrategy swapStrategy) {
        this.points = points;
        this.swapStrategy = swapStrategy;
        Collections.shuffle(points);
    }

    @Override
    public IProblem generateNextState() {
        switch (swapStrategy) {
            case CONSECUTIVE: {
                final int first = random.nextInt(points.size());
                final int second = (first + 1) % points.size();
                final List<Point> pointsCopy = new ArrayList<>(points);
                swap(first, second, pointsCopy);
                return new TravellingSalesman(pointsCopy, swapStrategy);
            }
            case ARBITRARY: {
                final int first = random.nextInt(points.size());
                int second;
                do {
                    second = random.nextInt(points.size());
                } while (first == second);
                final List<Point> pointsCopy = new ArrayList<>(points);
                swap(first, second, pointsCopy);
                return new TravellingSalesman(pointsCopy, swapStrategy);
            }
            default:
                throw new AssertionError("No branch for value of enum");
        }
    }

    private static void swap(final int first, final int second, final List<Point> points) {
        final Point tmp = points.get(first);
        points.set(first, points.get(second));
        points.set(second, tmp);
    }

    @Override
    public double calculateEnergy() {
        double energy = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            energy += points.get(i).distance(points.get((i+1)));
        }
        energy += points.get(points.size() - 1).distance(points.get(0));
        return energy;
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    public List<Point> getPoints() {
        return points;
    }
}
