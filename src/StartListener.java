import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {

    private ControlLayout controlPane;
    private GraphLayout graphLayout;

    private boolean hasStarted = false;
    private boolean isSimulating = false;

    public StartListener(ControlLayout controlLayout, GraphLayout graphLayout) {
        this.controlPane = controlLayout;
        this.graphLayout = graphLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (button.getName().equalsIgnoreCase("Reset Button")) {
            graphLayout.stopSimulation();

            graphLayout.resetChart();

            controlPane.start.setText("Start Simulation");
            isSimulating = false;
            hasStarted = false;
            return;
        }

        runFirstTime();

        //Checks if the use restarted the simulation
        if (isSimulating && (button.getName().equalsIgnoreCase("Play Button"))) {
            graphLayout.stopSimulation();

            isSimulating = false;
            controlPane.start.setText("Start Simulation");
        } else if(button.getName().equalsIgnoreCase("Play Button")) {
            graphLayout.startSimulation();

            isSimulating = true;
            controlPane.start.setText("Pause Simulation");
        }
    }

    private void runFirstTime() {
        if (!hasStarted) {
            int population = controlPane.populationSlider.getValue();
            int duration = controlPane.durationSlider.getValue();
            double[] interventions = controlPane.getInterventions();
            int averageContactRate = controlPane.averageContactRateSlider.getValue();
            double transmissionProbability = controlPane.transmissionRiskSlider.getValue() * 0.01;

            graphLayout.addPoints(population, duration, interventions, averageContactRate, transmissionProbability);

            controlPane.start.setText("Pause Simulation");
            hasStarted = true;

            graphLayout.changeTimerDelay(1000);
            graphLayout.startSimulation();
        }
    }
}
