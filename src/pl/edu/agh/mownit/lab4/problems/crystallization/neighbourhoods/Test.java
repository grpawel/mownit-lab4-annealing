package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

import pl.edu.agh.mownit.lab4.problems.IProblem;
import pl.edu.agh.mownit.lab4.problems.crystallization.Crystallization;
import pl.edu.agh.mownit.lab4.problems.crystallization.ImageCreator;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        IProblem crystallization = new Crystallization(256, 0.3, new Neighbourhood8Square());
        new ImageCreator().saveImage(((Crystallization) crystallization).getImage(), new File("Test1.png"));
        for (int i = 0; i < 500; i++) {
            crystallization = crystallization.generateNextState();
            System.out.println(crystallization.calculateEnergy());
        }
        new ImageCreator().saveImage(((Crystallization) crystallization).getImage(), new File("Test2.png"));

    }
}
