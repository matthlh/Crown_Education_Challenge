import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private ControlLayout controlPane;
    private GraphLayout graphLayout;
    private JButton button;

    private boolean hasStarted = false;
    private boolean isSimulating = false;

    public ButtonListener(ControlLayout controlLayout, GraphLayout graphLayout, JButton button) {
        this.controlPane = controlLayout;
        this.graphLayout = graphLayout;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runFirstTime();

        //Checks if the use restarted the simulation
        if (button.getText().equalsIgnoreCase("Reset Simulation")) {
            graphLayout.sus.clear();
            graphLayout.inf.clear();
            graphLayout.rec.clear();

            graphLayout.resetChart();
            graphLayout.stopSimulation();

            isSimulating = false;
            hasStarted = false;
            controlPane.start.setText("Start Simulation");
        }  else if (isSimulating && !(button.getText().equalsIgnoreCase("Reset Simulation"))) {
            // TODO: implement a way to stop the simulation here
            graphLayout.stopSimulation();

            isSimulating = false;
            controlPane.start.setText("Start Simulation");
        } else {
            graphLayout.startSimulation();

            isSimulating = true;
            controlPane.start.setText("Pause Simulation");
        }
    }

    private void runFirstTime() {
        if (!hasStarted) {
            int duration = controlPane.durationSlider.getValue();
            graphLayout.addPoints(controlPane.densitySlider.getValue(), duration);

            //TODO: add all the other features


            controlPane.start.setText("Pause Simulation");
            hasStarted = true;
        }
    }
}
