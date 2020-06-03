import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;

public class GraphLayout extends JPanel {

    XYChart chart;
    XChartPanel panel;
    JPanel pane = new JPanel();

    public GraphLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        drawComponents();
    }

    public void drawComponents() {
        chart = new XYChartBuilder().width(600).height(350).title("Area Chart").xAxisTitle("Time (s)").yAxisTitle("Number of people").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

        panel = new XChartPanel(chart);
    }
}
