package pl.edu.agh.mownit.lab4;

import pl.edu.agh.mownit.lab4.annealing.AnnealingSettingsReader;
import pl.edu.agh.mownit.lab4.annealing.AnnealingSimulator;
import pl.edu.agh.mownit.lab4.annealing.Plotter;
import pl.edu.agh.mownit.lab4.problems.IProblem;
import pl.edu.agh.mownit.lab4.problems.ImageCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public class Program {
    public static void main(String[] args) throws IOException {
        final ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final AnnealingSettingsReader reader = new AnnealingSettingsReader(args[0]);
        reader.readFromFile()
                .map(AnnealingSimulator::new)
                .filter(annealing -> !doEnergyPlotFileExist(annealing))
                .map(annealing -> CompletableFuture.supplyAsync(() -> {
                    annealing.simulate();
                    return annealing;
                }, executors))
                .map(future -> future.thenApply(Program::createGraph))
                .map(future -> future.thenApply(annealing -> {
                    saveImage(annealing.getInitialState(), annealing.getIdentifier(), "initial");
                    return annealing;
                }))
                .map(future -> future.thenApply(annealing -> {
                    saveImage(annealing.getBestState(), annealing.getIdentifier(), "result");
                    return annealing;
                }))
                .collect(Collectors.toList())
                .forEach(CompletableFuture::join);
        executors.shutdown();
    }

    private static AnnealingSimulator createGraph(final AnnealingSimulator annealing) {
        final Plotter plotter = new Plotter(annealing.getEnergyHistory(), "Energy function", "Iteration", "Energy");
        try {
            final Path path = Paths.get("plots/" + annealing.getIdentifier() + "_plot.png");
            Files.createDirectories(path.getParent());
            plotter.saveGraph(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return annealing;
    }

    private static void saveImage(final IProblem problem, final String fileNameCore, final String fileNameAppendix) {
        final ImageCreator imageCreator = new ImageCreator();
        try {
            final Path path = Paths.get("images/" + fileNameCore + "_" + fileNameAppendix + ".png");
            Files.createDirectories(path.getParent());
            imageCreator.saveImage(problem, path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean doEnergyPlotFileExist(final AnnealingSimulator annealing) {
        final File f = new File("plots/" + annealing.getIdentifier() + "_plot.png");
        return f.exists() && !f.isDirectory();
    }
}
