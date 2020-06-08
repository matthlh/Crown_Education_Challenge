import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GraphLayout implements ActionListener {

    XYChart chart;
    JPanel panel;
    ArrayList<Double> susceptibleArray = new ArrayList<>();
    ArrayList<Double> infectedArray = new ArrayList<>();
    ArrayList<Double> recoveredArray = new ArrayList<>();

    Timer timer;

    double population;
    double s;
    double i;
    double r;
    int duration;
    int delay;

    OrdinaryDifferentialEquations calculator;
    ControlLayout controlLayoutPane;

    public GraphLayout() {
        drawComponents();
    }

    public void drawComponents() {
        controlLayoutPane = new ControlLayout(this);
        population = 1000;
        s = population - 1;
        i = population - s;
        r = 0;
        delay = 0;

        susceptibleArray.add(s);
        infectedArray.add(i);
        recoveredArray.add(r);

        // Initialized the chart
        chart = new XYChartBuilder().width(600).height(350).title("SIR Model Graph")
                .xAxisTitle("Day (s)").yAxisTitle("Number of people").build();

        XYSeries susceptibleArrayceptibleArraySeries = chart.addSeries("susceptibleArray", susceptibleArray);
        XYSeries infectedArrayectedArraySeries = chart.addSeries("infectedArray", infectedArray);
        XYSeries recoveredArrayoveredArraySeries = chart.addSeries("recoveredArray", recoveredArray);

        //Puts legend inside graph
//        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        chart.getStyler().setMarkerSize(0);

        susceptibleArrayceptibleArraySeries.setFillColor(Color.BLUE);
        infectedArrayectedArraySeries.setFillColor(Color.ORANGE);
        recoveredArrayoveredArraySeries.setFillColor(Color.GRAY);

        susceptibleArrayceptibleArraySeries.setLineColor(Color.BLUE);
        infectedArrayectedArraySeries.setLineColor(Color.ORANGE);
        recoveredArrayoveredArraySeries.setLineColor(Color.GRAY);

        susceptibleArrayceptibleArraySeries.setEnabled(true);
        infectedArrayectedArraySeries.setEnabled(true);
        recoveredArrayoveredArraySeries.setEnabled(true);

        panel = new XChartPanel<>(chart);

        repaint();
    }

    public void addPoints(int pop, int duration) {
        // Initializes variables
        population = pop;
        s = population - 1;
        i = population - s;
        r = 0;
        this.duration = duration;
        calculator = new OrdinaryDifferentialEquations(population, s, i, r, duration);

        // Loops with a 1 second delay
        timer = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // Calculates the new number of infectedArrayectedArray and recoveredArrayoveredArray
        calculator.calculate(this);

        // Updates the chart
        chart.updateXYSeries("susceptibleArray", null, susceptibleArray, null);
        chart.updateXYSeries("infectedArray", null, infectedArray, null);
        chart.updateXYSeries("recoveredArray", null, recoveredArray, null);

        repaint();

        // Checks if there is no more infectedArrayectedArray, so it can stop
        if(Math.round(calculator.getinfectedArrayectedArray()) == 0) {
            if (delay >= 3) {
                stopSimulation();
            } else {
                delay++;
            }
        }
    }

    public void stopSimulation() {
        timer.stop();
    }

    public void startSimulation() {
        timer.start();
    }

    public void repaint() {
        panel.revalidate();
        panel.repaint();
    }

    public void resetChart() {
        susceptibleArray.add(s);
        infectedArray.add(i);
        recoveredArray.add(r);

        chart.updateXYSeries("susceptibleArray", null, susceptibleArray, null);
        chart.updateXYSeries("infectedArray", null, infectedArray, null);
        chart.updateXYSeries("recoveredArray", null, recoveredArray, null);

        repaint();
    }
}