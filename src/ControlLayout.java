import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ControlLayout extends JPanel{
    GraphLayout graphLayout;
    InformationButtonListener infoListener = new InformationButtonListener();

    double socialDistancePercent;
    double handWashingPercent;
    double wearingMaskPercent;
    double wearingGlovePercent;

    JButton population;
    JSlider populationSlider;
    JLabel chosenPopulation;

    JButton duration;
    JSlider durationSlider;
    JLabel chosenDuration;

    JButton transmissionRisk;
    JSlider transmissionRiskSlider;
    JLabel chosenTransmissionRisk;

    JButton averageContactRate;
    JSlider averageContactRateSlider;
    JLabel chosenAverageContactRate;

    JButton socialDistance;
    JSlider socialDistanceSlider;
    JLabel chosenSocialDistance;

    JButton handWashing;
    JSlider handWashingSlider;
    JLabel chosenHandWashing;

    JButton wearingMask;
    JSlider wearingMaskSlider;
    JLabel chosenWearingMask;

    JButton wearingGlove;
    JSlider wearingGloveSlider;
    JLabel chosenWearingGlove;

    JButton info;
    JButton start;
    JButton reset;

    public ControlLayout(GraphLayout gl) {
        this.graphLayout = gl;
        setLayout(new GridLayout(9, 3));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        drawComponents();
    }

    public void drawComponents() {
        // Initializes all the components
        population = new JButton("Population Size");
        populationSlider = new JSlider(0, 10000);
        chosenPopulation = new JLabel("1000 People", SwingConstants.CENTER);

        duration = new JButton("Disease Duration (Days)");
        durationSlider = new JSlider(0, 50);
        chosenDuration = new JLabel("10 Days", SwingConstants.CENTER);

        transmissionRisk = new JButton("Probability of Transmission");
        transmissionRiskSlider = new JSlider(0, 100);
        chosenTransmissionRisk = new JLabel("40%", SwingConstants.CENTER);

        averageContactRate = new JButton("Average Rate of Contact");
        averageContactRateSlider = new JSlider(0, 50);
        chosenAverageContactRate = new JLabel("12 People", SwingConstants.CENTER);

        socialDistance = new JButton("<html>Percentage of People Social Distancing: " +
                "<a href=\'https://www.google.com/\'>Click Here!</a></html>");
        socialDistanceSlider = new JSlider(0, 100);
        chosenSocialDistance = new JLabel("0%", SwingConstants.CENTER);

        handWashing = new JButton("Percentage of People Washing Hands");
        handWashingSlider = new JSlider(0, 100);
        chosenHandWashing = new JLabel("0%", SwingConstants.CENTER);

        wearingMask = new JButton("Percentage of People Wearing Masks");
        wearingMaskSlider = new JSlider(0, 100);
        chosenWearingMask = new JLabel("0%", SwingConstants.CENTER);

        wearingGlove = new JButton("Percentage of People Wearing Gloves");
        wearingGloveSlider = new JSlider(0, 100);
        chosenWearingGlove = new JLabel("0%", SwingConstants.CENTER);

        info = new JButton("More Information");
        start = new JButton("Start Simulation");
        reset = new JButton("Reset Simulation");

        start.setName("Play Button");
        reset.setName("Reset Button");

        // Add tooltips
        population.setToolTipText("Sets the population for the simulation. " +
                "Click for More!");
        duration.setToolTipText("Sets the disease  duration. " +
                "Click for More!");
        transmissionRisk.setToolTipText("Change the probability of infecting per contact. " +
                "Click for More!");
        averageContactRate.setToolTipText("Change the average contact per person. " +
                "Click for More!");
        socialDistance.setToolTipText("Sets the percentage of people social distancing. " +
                "Click for More!");
        handWashing.setToolTipText("Sets the percentage of people who washes their hands frequently. " +
                "Click for More!");
        wearingMask.setToolTipText("Sets the percentage of people wearing a mask. " +
                "Click for More!");
        wearingGlove.setToolTipText("Sets the percentage of people wearing a glove. " +
                "Click for More!");

        population.setBorderPainted(false);
        population.setOpaque(false);

        duration.setBorderPainted(false);
        duration.setOpaque(false);

        transmissionRisk.setBorderPainted(false);
        transmissionRisk.setOpaque(false);

        averageContactRate.setBorderPainted(false);
        averageContactRate.setOpaque(false);

        socialDistance.setBorderPainted(false);
        socialDistance.setOpaque(false);

        handWashing.setBorderPainted(false);
        handWashing.setOpaque(false);

        wearingMask.setBorderPainted(false);
        wearingMask.setOpaque(false);

        wearingGlove.setBorderPainted(false);
        wearingGlove.setOpaque(false);

        try {
            population.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            duration.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            transmissionRisk.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            averageContactRate.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            socialDistance.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            handWashing.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            wearingMask.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
            wearingGlove.addActionListener(new HTTPListener(new URI("https://www.google.com/")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sets the limits and ticks for the sliders
        setFactorSettings(populationSlider, 1000, 2000, 100);
        setFactorSettings(durationSlider, 10, 10, 1);
        setFactorSettings(transmissionRiskSlider, 20, 10, 1);
        setFactorSettings(averageContactRateSlider, 8, 10, 1);
        setFactorSettings(socialDistanceSlider, 0, 10, 1);
        setFactorSettings(handWashingSlider, 0, 10, 1);
        setFactorSettings(wearingMaskSlider, 0, 10, 1);
        setFactorSettings(wearingGloveSlider, 0, 10, 1);

        // Adds ActionListener to each Slider
        populationSlider.addChangeListener(new SliderListener(chosenPopulation, populationSlider, " People"));
        durationSlider.addChangeListener(new SliderListener(chosenDuration, durationSlider, " Days"));
        averageContactRateSlider.addChangeListener(new SliderListener(chosenAverageContactRate, averageContactRateSlider, " People"));
        transmissionRiskSlider.addChangeListener(new SliderListener(chosenTransmissionRisk, transmissionRiskSlider, "%"));
        socialDistanceSlider.addChangeListener(new SliderListener(chosenSocialDistance, socialDistanceSlider, "%"));
        handWashingSlider.addChangeListener(new SliderListener(chosenHandWashing, handWashingSlider, "%"));
        wearingMaskSlider.addChangeListener(new SliderListener(chosenWearingMask, wearingMaskSlider, "%"));
        wearingGloveSlider.addChangeListener(new SliderListener(chosenWearingGlove, wearingGloveSlider, "%"));

        // Add ActionListener to each button
        info.addActionListener(infoListener);
        start.addActionListener(new StartListener(this, graphLayout, start));
        reset.addActionListener(new StartListener(this, graphLayout, reset));

        // Adds all the components to the JPanel
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

        add(info);
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

        double[] allInterventions = {socialDistancePercent, handWashingPercent,
                wearingMaskPercent, wearingGlovePercent};

        return allInterventions;
    }
}
