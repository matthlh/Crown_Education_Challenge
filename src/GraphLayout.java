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
    int seed = 1;

    Calculations calculator;

    public GraphLayout() {
        drawComponents();
    }

    public void drawComponents() {
        n = 1000;
        s = n - 1;
        i = n - s;
        r = 0;

        sus.add(s);
        inf.add(i);
        rec.add(r);

        chart = new XYChartBuilder().width(600).height(350).title("Area Chart")
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
        panel.revalidate();
        panel.repaint();
    }

    public void addPoints(int pop, int duration) {

        n = pop;
        s = n - 1;
        i = n - s;
        r = 0;
        this.duration = duration;
        calculator = new Calculations(n, s, i, r, duration, 3);

        Timer timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        calculator.calculate(this);

        chart.updateXYSeries("Susceptible", null, sus, null);
        chart.updateXYSeries("Infected", null, inf, null);
        chart.updateXYSeries("Recovered", null, rec, null);

        panel.revalidate();
        panel.repaint();

        if(i == 0) {
            timer.stop();
        }
    }
}