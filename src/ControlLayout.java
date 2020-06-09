import javax.swing.*;
import java.awt.*;

public class ControlLayout extends JPanel{
    double socialDistancePercent;
    double handWashingPercent;
    double wearingMaskPercent;
    double wearingGlovePercent;
    double wearingGownPercent;

    JLabel population;
    JSlider populationSlider;
    JLabel chosenPopulation;

    JLabel duration;
    JSlider durationSlider;
    JLabel chosenDuration;

    JLabel transmissionRisk;
    JSlider transmissionRiskSlider;
    JLabel chosenTransmissionRisk;

    JLabel averageContactRate;
    JSlider averageContactRateSlider;
    JLabel chosenAverageContactRate;

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
        setLayout(new GridLayout(10, 3));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        drawComponents();
    }

    public void drawComponents() {
        population = new JLabel("Population Size:");
        populationSlider = new JSlider(0, 10000);
        chosenPopulation = new JLabel("1000 People", SwingConstants.CENTER);

        duration = new JLabel("Disease Duration (Days):");
        durationSlider = new JSlider(0, 50);
        chosenDuration = new JLabel("10 Days", SwingConstants.CENTER);

        transmissionRisk = new JLabel("Probability of Transmission:");
        transmissionRiskSlider = new JSlider(0, 100);
        chosenTransmissionRisk = new JLabel("40%", SwingConstants.CENTER);

        averageContactRate = new JLabel("Average Rate of Contact:");
        averageContactRateSlider = new JSlider(1, 50);
        chosenAverageContactRate = new JLabel("12 People", SwingConstants.CENTER);

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

        setFactorSettings(populationSlider, 1000, 2000, 100);
        setFactorSettings(durationSlider, 10, 10, 1);
        setFactorSettings(transmissionRiskSlider, 40, 10, 1);
        setFactorSettings(averageContactRateSlider, 12, 10, 1);
        setFactorSettings(socialDistanceSlider, 0, 10, 1);
        setFactorSettings(handWashingSlider, 0, 10, 1);
        setFactorSettings(wearingMaskSlider, 0, 10, 1);
        setFactorSettings(wearingGloveSlider, 0, 10, 1);
        setFactorSettings(wearingGownSlider, 0, 10, 1);

        populationSlider.addChangeListener(new SliderListener(chosenPopulation, populationSlider, " People"));
        durationSlider.addChangeListener(new SliderListener(chosenDuration, durationSlider, " Days"));
        averageContactRateSlider.addChangeListener(new SliderListener(chosenAverageContactRate, averageContactRateSlider, " People"));
        transmissionRiskSlider.addChangeListener(new SliderListener(chosenTransmissionRisk, transmissionRiskSlider, "%"));
        socialDistanceSlider.addChangeListener(new SliderListener(chosenSocialDistance, socialDistanceSlider, "%"));
        handWashingSlider.addChangeListener(new SliderListener(chosenHandWashing, handWashingSlider, "%"));
        wearingMaskSlider.addChangeListener(new SliderListener(chosenWearingMask, wearingMaskSlider, "%"));
        wearingGloveSlider.addChangeListener(new SliderListener(chosenWearingGlove, wearingGloveSlider, "%"));
        wearingGownSlider.addChangeListener(new SliderListener(chosenWearingGown, wearingGownSlider, "%"));

        start.addActionListener(new ButtonListener(this, graphLayout, start));
        reset.addActionListener(new ButtonListener(this, graphLayout, reset));

        add(population);
        add(populationSlider);
        add(chosenPopulation);

        add(duration);
        add(durationSlider);
        add(chosenDuration);

        add(transmissionRisk);
        add(transmissionRiskSlider);
        add(chosenTransmissionRisk);

        add(averageContactRate);
        add(averageContactRateSlider);
        add(chosenAverageContactRate);

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
        socialDistancePercent = socialDistanceSlider.getValue();
        handWashingPercent = handWashingSlider.getValue();
        wearingMaskPercent = wearingMaskSlider.getValue();
        wearingGlovePercent = wearingGloveSlider.getValue();
        wearingGownPercent = wearingGownSlider.getValue();

        double[] allInterventions = {socialDistancePercent, handWashingPercent,
                wearingMaskPercent, wearingGlovePercent, wearingGownPercent};

        return allInterventions;
    }
}
