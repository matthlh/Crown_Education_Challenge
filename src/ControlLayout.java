import javax.swing.*;
import java.awt.*;

public class ControlLayout extends JPanel {

    JButton start;

    JLabel density;
    JSlider densitySlider;
    JLabel chosenDensity;

    JLabel duration;
    JSlider durationSlider;
    JLabel chosenDuration;

    JLabel socialDistance;
    JSlider socialDistanceSlider;
    JLabel chosenSocialDistance;

    JLabel wearingMask;
    JSlider wearingMaskSlider;
    JLabel chosenWearingMask;

    GraphLayout graphLayout;

    public ControlLayout(GraphLayout gl) {
        this.graphLayout = gl;
        setLayout(new GridLayout(5, 3));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        drawComponents();
    }

    public void drawComponents() {
        start = new JButton("Start Simulation");

        density = new JLabel("Population Size:");
        densitySlider = new JSlider(0, 10000);
        chosenDensity = new JLabel("1000", SwingConstants.CENTER);

        duration = new JLabel("Disease Duration:");
        durationSlider = new JSlider(0, 50);
        chosenDuration = new JLabel("10", SwingConstants.CENTER);

        socialDistance = new JLabel("Percentage of People Social Distancing:");
        socialDistanceSlider = new JSlider(0, 100);
        chosenSocialDistance = new JLabel("0%", SwingConstants.CENTER);

        wearingMask = new JLabel("Percentage of People Wearing Masks:");
        wearingMaskSlider = new JSlider(0, 100);
        chosenWearingMask = new JLabel("0%", SwingConstants.CENTER);

        setFactorSettings(densitySlider, 1000, 2000, 100);
        setFactorSettings(durationSlider, 10, 10, 1);
        setFactorSettings(socialDistanceSlider, 0, 10, 1);
        setFactorSettings(wearingMaskSlider, 0, 10, 1);

        start.setBackground(Color.GREEN);

        densitySlider.addChangeListener(new SliderListener(chosenDensity, densitySlider, false));
        durationSlider.addChangeListener(new SliderListener(chosenDuration, durationSlider, false));

        socialDistanceSlider.addChangeListener(new SliderListener(chosenSocialDistance, socialDistanceSlider, true));
        wearingMaskSlider.addChangeListener(new SliderListener(chosenWearingMask, wearingMaskSlider, true));

        start.addActionListener(new ButtonListener(this, graphLayout));

        add(density);
        add(densitySlider);
        add(chosenDensity);

        add(duration);
        add(durationSlider);
        add(chosenDuration);

        add(socialDistance);
        add(socialDistanceSlider);
        add(chosenSocialDistance);

        add(wearingMask);
        add(wearingMaskSlider);
        add(chosenWearingMask);

        add(new JLabel());
        add(start);
        add(new JLabel());
    }

    public void setFactorSettings(JSlider slider, int value, int majorTick, int minorTick) {
        slider.setValue(value);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(minorTick);

        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
    }
}
