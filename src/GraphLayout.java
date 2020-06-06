import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GraphLayout implements ActionListener {

    XYChart chart;
    JPanel panel;
    ArrayList<Double> sus = new ArrayList<>();
    ArrayList<Double> inf = new ArrayList<>();
    ArrayList<Double> rec = new ArrayList<>();

    Timer timer;

    double n;
    double s;
    double i;
    double r;
    int duration;
    int delay;
    int seed = 1;

    ODESolver calculator;
    ControlLayout controlLayoutPane;

    public GraphLayout() {
        drawComponents();
    }

    public void drawComponents() {
        controlLayoutPane = new ControlLayout(this);
        n = 1000;
        s = n - 1;
        i = n - s;
        r = 0;
        delay = 0;

        sus.add(s);
        inf.add(i);
        rec.add(r);

        // Initialized the chart
        chart = new XYChartBuilder().width(600).height(350).title("SIR Model Graph")
                .xAxisTitle("Day (s)").yAxisTitle("Number of people").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

        XYSeries susceptibleSeries = chart.addSeries("Susceptible", sus);
        XYSeries infectedSeries = chart.addSeries("Infected", inf);
        XYSeries recoveredSeries = chart.addSeries("Recovered", rec);

        susceptibleSeries.setSmooth(true);
        infectedSeries.setSmooth(true);
        recoveredSeries.setSmooth(true);

        susceptibleSeries.setEnabled(true);
        infectedSeries.setEnabled(true);
        recoveredSeries.setEnabled(true);

        panel = new XChartPanel<>(chart);
        repaint();
    }

    public void addPoints(int pop, int duration) {
        // Initializes variables
        n = pop;
        s = n - 1;
        i = n - s;
        r = 0;
        this.duration = duration;
        calculator = new ODESolver(n, s, i, r, duration);

        // Loops with a 1 second delay
        timer = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // Calculates the new number of infected and recovered
        calculator.calculate(this);

        // Updates the chart
        chart.updateXYSeries("Susceptible", null, sus, null);
        chart.updateXYSeries("Infected", null, inf, null);
        chart.updateXYSeries("Recovered", null, rec, null);

        repaint();

        // Checks if there is no more infected, so it can stop
        if(calculator.getI() == 0) {
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
        sus.add(s);
        inf.add(i);
        rec.add(r);

        chart.updateXYSeries("Susceptible", null, sus, null);
        chart.updateXYSeries("Infected", null, inf, null);
        chart.updateXYSeries("Recovered", null, rec, null);

        repaint();
    }
}