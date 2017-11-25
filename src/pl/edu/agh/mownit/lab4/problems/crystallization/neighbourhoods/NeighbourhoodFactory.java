package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class NeighbourhoodFactory {
    public Neighbourhood create(final String name) {
        switch (name) {
            case "8Black":
                return new Neighbourhood8Black();
            case "4Plus":
                return new Neighbourhood4Plus();
        }
        return null;
    }
}
