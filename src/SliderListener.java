import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

    private JLabel label;
    private JSlider slider;
    private String suffix;
    private ControlLayout controlLayout;

    public SliderListener(JLabel label, JSlider slider,String suffix, ControlLayout controlLayout) {
        this.label = label;
        this.slider = slider;
        this.suffix = suffix;
        this.controlLayout = controlLayout;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Displays value of the slider if the slider was moved
        label.setText(slider.getValue() + suffix);

        // Changes the preset to custom
        controlLayout.presetList.setSelectedItem("Custom");
        controlLayout.chosenPresets.setText((String) controlLayout.presetList.getSelectedItem());
    }
}
