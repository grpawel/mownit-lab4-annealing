package pl.edu.agh.mownit.lab4.problems;

import pl.edu.agh.mownit.lab4.problems.crystallization.CrystalImageCreator;
import pl.edu.agh.mownit.lab4.problems.crystallization.Crystallization;
import pl.edu.agh.mownit.lab4.problems.travellingsalesman.TSPImageCreator;
import pl.edu.agh.mownit.lab4.problems.travellingsalesman.TravellingSalesman;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paweł Grochola on 26.11.2017.
 */
public class ImageCreator {
    public void saveImage(final IProblem problem, final File file) throws IOException {
        if(problem instanceof TravellingSalesman) {
            new TSPImageCreator().saveImage(((TravellingSalesman) problem).getPoints(), file);
        }
        else if(problem instanceof Crystallization) {
            new CrystalImageCreator().saveImage(((Crystallization) problem).getImage(), file);
        }
    }
}
