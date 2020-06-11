import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedButtonListener implements ActionListener {
    int milliseconds;
    GraphLayout graphLayout;

    public SpeedButtonListener(int milliseconds, GraphLayout graphLayout) {
        this.milliseconds = milliseconds;
        this.graphLayout = graphLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graphLayout.changeTimerDelay(milliseconds);
    }
}
