import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

    private JLabel label;
    private JSlider slider;
    private String suffix;

    public SliderListener(JLabel label, JSlider slider, String suffix) {
        this.label = label;
        this.slider = slider;
        this.suffix = suffix;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Displays value of the slider if the slider was moved
        label.setText(slider.getValue() + suffix);
    }
}
