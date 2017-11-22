package pl.edu.agh.mownit.lab4;

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
                .map(as -> CompletableFuture.supplyAsync(() -> {
                    as.simulate();
                    return as;
                }, executors))
                .collect(Collectors.toList())
                .stream()
                .map(future -> future.thenApply(Program::createGraph))
                .map(CompletableFuture::join)
                .forEach(Program::printAnnealingResults);
        executors.shutdown();
    }

    private static synchronized void printAnnealingResults(final AnnealingSimulator as) {
        System.out.println(as.resultToString());
    }

    private static AnnealingSimulator createGraph(final AnnealingSimulator annealing) {
        final Plotter plotter = new Plotter(annealing.getEnergyHistory(), "Energy function", "Iteration", "Energy");
        try {
            final Path path =  Paths.get("plots/" + annealing.getIdentifier() + ".png");
            Files.createDirectories(path.getParent());
            plotter.saveGraph(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return annealing;

    }
}
