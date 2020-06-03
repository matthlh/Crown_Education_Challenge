import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphListener implements ActionListener{

    XYChart chart;
    JPanel chartPanel;

    public GraphListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chart = new XYChartBuilder().width(600).height(350).title("Area Chart").xAxisTitle("Time (s)").yAxisTitle("Number of people").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

//        XYSeries susceptibleSeries = chart.addSeries("Susceptible", );
//        XYSeries infectedSeries = chart.addSeries("Infected", );
//        XYSeries recoveredSeries = chart.addSeries("Recovered", );

//        susceptibleSeries.setEnabled(true);
//        infectedSeries.setEnabled(true);
//        recoveredSeries.setEnabled(true);

    }
}

