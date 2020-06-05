import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private ControlLayout controlPane;
    private GraphLayout graphLayout;

    public ButtonListener(ControlLayout controlLayout, GraphLayout graphLayout) {
        this.controlPane = controlLayout;
        this.graphLayout = graphLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graphLayout.sus.clear();
        graphLayout.inf.clear();
        graphLayout.rec.clear();

        int duration = Integer.parseInt(controlPane.chosenDuration.getText());

        graphLayout.addPoints(controlPane.densitySlider.getValue(), duration);
    }
}
