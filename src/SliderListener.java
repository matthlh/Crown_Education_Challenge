import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

    private JLabel label;
    private JSlider slider;
    private boolean isPercentage;

    public SliderListener(JLabel label, JSlider slider, boolean isPercentage) {
        this.label = label;
        this.slider = slider;
        this.isPercentage = isPercentage;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(isPercentage) {
            label.setText(slider.getValue() + "%");
        } else {
            label.setText("" + slider.getValue());
        }
    }
}
