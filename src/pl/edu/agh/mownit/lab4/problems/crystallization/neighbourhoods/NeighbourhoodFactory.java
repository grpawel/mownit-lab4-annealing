package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class NeighbourhoodFactory {
    public Neighbourhood create(final String name) {
        switch (name) {
            case "4Cross":
                return new Neighbourhood4Cross();
            case "4Plus":
                return new Neighbourhood4Plus();
            case "6LongPlus":
                return new Neighbourhood6LongPlus();
            case "8Square":
                return new Neighbourhood8Square();
            case "16_8EmptySquare":
                return new Neighbourhood16_8EmptySquare();
        }
        return null;
    }
}
