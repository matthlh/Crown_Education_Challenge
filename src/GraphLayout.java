import org.knowm.xchart.*;

import javax.swing.*;
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

        XYSeries susceptibleSeries = chart.addSeries("Susceptible", susceptibleArray);
        XYSeries infectedSeries = chart.addSeries("Infected", infectedArray);
        XYSeries recoveredSeries = chart.addSeries("Recovered", recoveredArray);

        //Puts legend inside graph
//        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        chart.getStyler().setMarkerSize(0);
//
//        susceptibleSeries.setFillColor(Color.BLUE);
//        infectedSeries.setFillColor(Color.ORANGE);
//        recoveredSeries.setFillColor(Color.GRAY);
//
//        susceptibleSeries.setLineColor(Color.BLUE);
//        infectedSeries.setLineColor(Color.ORANGE);
//        recoveredSeries.setLineColor(Color.GRAY);

        susceptibleSeries.setEnabled(true);
        infectedSeries.setEnabled(true);
        recoveredSeries.setEnabled(true);

        panel = new XChartPanel<>(chart);

        repaint();
    }

    public void addPoints(int pop, int duration, double[] interventions, int averageContactRate, double transmissionProbability) {
        // Initializes variables
        population = pop;
        s = population - 1;
        i = population - s;
        r = 0;
        calculator = new OrdinaryDifferentialEquations(population, s, i, r, duration, interventions, averageContactRate, transmissionProbability);

        // Loops with a 1 second delay
        timer = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // Calculates the new number of infected and recovered
        calculator.calculate(this);

        // Updates the chart
        chart.updateXYSeries("Susceptible", null, susceptibleArray, null);
        chart.updateXYSeries("Infected", null, infectedArray, null);
        chart.updateXYSeries("Recovered", null, recoveredArray, null);

        repaint();

        // Checks if there is no more infected, so it can stop
        if(Math.round(calculator.getinfected()) == 0) {
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

        chart.updateXYSeries("Susceptible", null, susceptibleArray, null);
        chart.updateXYSeries("Infected", null, infectedArray, null);
        chart.updateXYSeries("Recovered", null, recoveredArray, null);

        repaint();
    }
}