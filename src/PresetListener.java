import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresetListener implements ActionListener {
    JComboBox<String> comboBox;
    JLabel label;
    String s;
    ControlLayout controlLayout;

    public PresetListener(JComboBox<String> comboBox, JLabel label, ControlLayout controlLayout) {
        this.comboBox = comboBox;
        this.label = label;
        this.controlLayout = controlLayout;
        s = (String) comboBox.getSelectedItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s = (String) comboBox.getSelectedItem();
        controlLayout.chosenPresets.setText(s);

        // Checks which preset is selected in the comboBox and changes accordingly
        switch (s) {
            case "Default":
                setSliderAndLabelValue(controlLayout.populationSlider,
                        controlLayout.chosenPopulation, 1000, " People");
                setSliderAndLabelValue(controlLayout.durationSlider,
                        controlLayout.chosenDuration, 10, " Days");
                setSliderAndLabelValue(controlLayout.transmissionRiskSlider,
                        controlLayout.chosenTransmissionRisk, 20, "%");
                comboBox.setSelectedItem(s);
                label.setText(s);
                break;
            case "COVID-19":
                setSliderAndLabelValue(controlLayout.populationSlider,
                        controlLayout.chosenPopulation, 1000, " People");
                setSliderAndLabelValue(controlLayout.durationSlider,
                        controlLayout.chosenDuration, 14, " Days");
                setSliderAndLabelValue(controlLayout.transmissionRiskSlider,
                        controlLayout.chosenTransmissionRisk, 6, "%");
                comboBox.setSelectedItem(s);
                label.setText(s);
                break;
            case "SARS":
                setSliderAndLabelValue(controlLayout.populationSlider,
                        controlLayout.chosenPopulation, 1000, " People");
                setSliderAndLabelValue(controlLayout.durationSlider,
                        controlLayout.chosenDuration, 28, " Days");
                setSliderAndLabelValue(controlLayout.transmissionRiskSlider,
                        controlLayout.chosenTransmissionRisk, 2, "%");
                comboBox.setSelectedItem(s);
                label.setText(s);
                break;
            case "H1N1":
                setSliderAndLabelValue(controlLayout.populationSlider,
                        controlLayout.chosenPopulation, 1000, " People");
                setSliderAndLabelValue(controlLayout.durationSlider,
                        controlLayout.chosenDuration, 7, " Days");
                setSliderAndLabelValue(controlLayout.transmissionRiskSlider,
                        controlLayout.chosenTransmissionRisk, 4, "%");
                comboBox.setSelectedItem(s);
                label.setText(s);
                break;
            default:
                break;
        }
    }

    public void setSliderAndLabelValue(JSlider slider, JLabel label, int sliderValue, String suffix) {
        slider.setValue(sliderValue);
        label.setText(sliderValue + suffix);
    }
}
