package pl.edu.agh.mownit.lab4.problems;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
public interface IProblem {
    IProblem generateNextState();

    double calculateEnergy();

    boolean isSolved();

}
