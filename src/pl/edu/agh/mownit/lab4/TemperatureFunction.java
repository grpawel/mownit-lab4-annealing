package pl.edu.agh.mownit.lab4;

/**
 * Created by Paweł Grochola on 11.11.2017.
 */
@FunctionalInterface
public interface TemperatureFunction {
    double calculate(int iteration);
}
