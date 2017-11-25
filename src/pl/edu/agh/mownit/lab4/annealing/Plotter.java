package pl.edu.agh.mownit.lab4.annealing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Paweł Grochola on 22.11.2017.
 */
public class Plotter {
    private final List<Double> values;
    private final String graphLabel;
    private final String xAxisLabel;
    private final String yAxisLabel;

    public Plotter(final List<Double> values, final String graphLabel, final String xAxisLabel, final String yAxisLabel) {
        this.values = values;
        this.graphLabel = graphLabel;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }

    public void saveGraph(final File file) throws IOException {
        final XYSeries series = new XYSeries("Series0");
        for (int i = 0; i < values.size(); i++) {
            series.add(i, values.get(i));
        }
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                graphLabel,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);
        chart.setBackgroundPaint(Color.white);
        ChartUtilities.saveChartAsPNG(file, chart, 500, 500);
    }


}
