import javax.swing.*;
import java.awt.*;

public class ControlLayout extends JPanel{
    double socialDistancePCT;
    double handWashingPCT;
    double wearingMaskPCT;
    double wearingGlovePCT;
    double wearingGownPCT;

    JLabel density;
    JSlider densitySlider;
    JLabel chosenDensity;

    JLabel duration;
    JSlider durationSlider;
    JLabel chosenDuration;

    JLabel socialDistance;
    JSlider socialDistanceSlider;
    JLabel chosenSocialDistance;

    JLabel handWashing;
    JSlider handWashingSlider;
    JLabel chosenHandWashing;

    JLabel wearingMask;
    JSlider wearingMaskSlider;
    JLabel chosenWearingMask;

    JLabel wearingGlove;
    JSlider wearingGloveSlider;
    JLabel chosenWearingGlove;

    JLabel wearingGown;
    JSlider wearingGownSlider;
    JLabel chosenWearingGown;

    GraphLayout graphLayout;

    JButton start;
    JButton reset;

    public ControlLayout(GraphLayout gl) {
        this.graphLayout = gl;
        setLayout(new GridLayout(8, 3));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        drawComponents();
    }

    public void drawComponents() {
        density = new JLabel("Population Size:");
        densitySlider = new JSlider(0, 10000);
        chosenDensity = new JLabel("1000 People", SwingConstants.CENTER);

        duration = new JLabel("Disease Duration (Days):");
        durationSlider = new JSlider(0, 50);
        chosenDuration = new JLabel("10 Days", SwingConstants.CENTER);

        socialDistance = new JLabel("Percentage of People Social Distancing:");
        socialDistanceSlider = new JSlider(0, 100);
        chosenSocialDistance = new JLabel("0%", SwingConstants.CENTER);

        handWashing = new JLabel("Percentage of People Washing Hands:");
        handWashingSlider = new JSlider(0, 100);
        chosenHandWashing = new JLabel("0%", SwingConstants.CENTER);

        wearingMask = new JLabel("Percentage of People Wearing Masks:");
        wearingMaskSlider = new JSlider(0, 100);
        chosenWearingMask = new JLabel("0%", SwingConstants.CENTER);

        wearingGlove = new JLabel("Percentage of People Wearing Gloves:");
        wearingGloveSlider = new JSlider(0, 100);
        chosenWearingGlove = new JLabel("0%", SwingConstants.CENTER);

        wearingGown = new JLabel("Percentage of People Wearing Gowns:");
        wearingGownSlider = new JSlider(0, 100);
        chosenWearingGown = new JLabel("0%", SwingConstants.CENTER);

        start = new JButton("Start Simulation");
        reset = new JButton("Reset Simulation");

        setFactorSettings(densitySlider, 1000, 2000, 100);
        setFactorSettings(durationSlider, 10, 10, 1);
        setFactorSettings(socialDistanceSlider, 0, 10, 1);
        setFactorSettings(handWashingSlider, 0, 10, 1);
        setFactorSettings(wearingMaskSlider, 0, 10, 1);
        setFactorSettings(wearingGloveSlider, 0, 10, 1);
        setFactorSettings(wearingGownSlider, 0, 10, 1);

        densitySlider.addChangeListener(new SliderListener(chosenDensity, densitySlider, " People"));
        durationSlider.addChangeListener(new SliderListener(chosenDuration, durationSlider, " Days"));

        socialDistanceSlider.addChangeListener(new SliderListener(chosenSocialDistance, socialDistanceSlider, "%"));
        handWashingSlider.addChangeListener(new SliderListener(chosenHandWashing, handWashingSlider, "%"));
        wearingMaskSlider.addChangeListener(new SliderListener(chosenWearingMask, wearingMaskSlider, "%"));
        wearingGloveSlider.addChangeListener(new SliderListener(chosenWearingGlove, wearingGloveSlider, "%"));
        wearingGownSlider.addChangeListener(new SliderListener(chosenWearingGown, wearingGownSlider, "%"));

        start.addActionListener(new ButtonListener(this, graphLayout, start));
        reset.addActionListener(new ButtonListener(this, graphLayout, reset));

        add(density);
        add(densitySlider);
        add(chosenDensity);

        add(duration);
        add(durationSlider);
        add(chosenDuration);

        add(socialDistance);
        add(socialDistanceSlider);
        add(chosenSocialDistance);

        add(handWashing);
        add(handWashingSlider);
        add(chosenHandWashing);

        add(wearingMask);
        add(wearingMaskSlider);
        add(chosenWearingMask);

        add(wearingGlove);
        add(wearingGloveSlider);
        add(chosenWearingGlove);

        add(wearingGown);
        add(wearingGownSlider);
        add(chosenWearingGown);

        add(new JLabel());
        add(start);
        add(reset);
    }

    public void setFactorSettings(JSlider slider, int value, int majorTick, int minorTick) {
        slider.setValue(value);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(minorTick);

        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
    }

    public double[] getInterventions() {
        String socialDistanceText = chosenSocialDistance.getText();
        String handWashingText = chosenSocialDistance.getText();
        String wearingMaskText = chosenSocialDistance.getText();
        String wearingGloveText = chosenSocialDistance.getText();
        String wearingGownText = chosenSocialDistance.getText();

        socialDistanceText = socialDistanceText.substring(0, socialDistanceText.length() - 1);
        handWashingText = handWashingText.substring(0, handWashingText.length() - 1);
        wearingMaskText = wearingMaskText.substring(0, wearingMaskText.length() - 1);
        wearingGloveText = wearingGloveText.substring(0, wearingGloveText.length() - 1);
        wearingGownText = wearingGownText.substring(0, wearingGownText.length() - 1);

        double socialDistancePCT = Double.parseDouble(socialDistanceText);
        double handWashingPCT = Double.parseDouble(handWashingText);
        double wearingMaskPCT = Double.parseDouble(wearingMaskText);
        double wearingGlovePCT = Double.parseDouble(wearingGloveText);
        double wearingGownPCT = Double.parseDouble(wearingGownText);

        System.out.println(chosenSocialDistance.getText());
        System.out.println("====================");

        double[] allInterventions = {socialDistancePCT, handWashingPCT,
                wearingMaskPCT, wearingGlovePCT, wearingGownPCT};

        return allInterventions;
    }
}
