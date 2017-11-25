package pl.edu.agh.mownit.lab4.problems.crystallization.neighbourhoods;

/**
 * Created by Paweł Grochola on 25.11.2017.
 */
public class NeighbourhoodFactory {
    public Neighbourhood create(final String name) {
        switch (name) {
            case "Cross":
                return new Cross();
            case "Plus":
                return new Plus();
            case "LongPlus":
                return new LongPlus();
            case "Square9":
                return new Square9();
            case "Square25":
                return new Square25();
            case "EmptySquare25":
                return new EmptySquare25();
            case "HorizontalLines":
                return new HorizontalLines();
            case "VerticalLines":
                return new VerticalLines();
        }
        return null;
    }
}
