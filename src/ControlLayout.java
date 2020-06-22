import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ControlLayout extends JPanel{
    GraphLayout graphLayout;
    InformationButtonListener infoListener;
    StartListener startListener;

    double socialDistancePercent;
    double handWashingPercent;
    double wearingMaskPercent;
    double wearingGlovePercent;

    String[] presetString = {"Default", "COVID-19", "SARS", "H1N1", "Custom"};

    JButton start;
    JButton reset;
    JButton R0;

    JButton normalSpeed;
    JButton doubleSpeed;
    JButton x1000Speed;

    JButton presets;
    JComboBox<String> presetList;
    JLabel chosenPresets;

    JButton population;
    JSlider populationSlider;
    JLabel chosenPopulation;

    JButton duration;
    JSlider durationSlider;
    JLabel chosenDuration;

    JButton transmissionRisk;
    JSlider transmissionRiskSlider;
    JLabel chosenTransmissionRisk;

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

    public ControlLayout(GraphLayout gl) {
        this.graphLayout = gl;
        startListener = new StartListener(this, graphLayout);
        infoListener = new InformationButtonListener();
        setLayout(new GridLayout(10, 3));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        drawComponents();
    }

    public void drawComponents() {
        // Initializes all the components
        start = new JButton("Start Simulation");
        reset = new JButton("Reset Simulation");
        R0 = new JButton("R0: ");

        normalSpeed = new JButton("Normal Speed");
        doubleSpeed = new JButton("x2 Speed");
        x1000Speed = new JButton("x1000 Speed");

        presets = new JButton("Presets:");
        presetList = new JComboBox(presetString);
        chosenPresets = new JLabel("Default", SwingConstants.CENTER);

        population = new JButton("Population Size");
        populationSlider = new JSlider(0, 10000);
        chosenPopulation = new JLabel("1000 People", SwingConstants.CENTER);

        duration = new JButton("Disease Duration (Days)");
        durationSlider = new JSlider(0, 50);
        chosenDuration = new JLabel("10 Days", SwingConstants.CENTER);

        transmissionRisk = new JButton("Probability of Transmission");
        transmissionRiskSlider = new JSlider(0, 100);
        chosenTransmissionRisk = new JLabel("20%", SwingConstants.CENTER);

        socialDistance = new JButton("Percentage of People Social Distancing: ");
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

        start.setName("Play Button");
        reset.setName("Reset Button");

        // Add tooltips
        R0.setToolTipText("This is the current R0 in this model. " +
                "Click for More!");
        population.setToolTipText("Sets the population for the simulation. " +
                "Click for More!");
        duration.setToolTipText("Sets the disease  duration. " +
                "Click for More!");
        transmissionRisk.setToolTipText("Change the probability of infecting per contact. " +
                "Click for More!");
        socialDistance.setToolTipText("Sets the percentage of people social distancing. " +
                "Click for More!");
        handWashing.setToolTipText("Sets the percentage of people who washes their hands frequently. " +
                "Click for More!");
        wearingMask.setToolTipText("Sets the percentage of people wearing a mask. " +
                "Click for More!");
        wearingGlove.setToolTipText("Sets the percentage of people wearing a glove. " +
                "Click for More!");

        R0.setBorderPainted(false);
        R0.setOpaque(false);

        presets.setBorderPainted(false);
        presets.setOpaque(false);

        population.setBorderPainted(false);
        population.setOpaque(false);

        duration.setBorderPainted(false);
        duration.setOpaque(false);

        transmissionRisk.setBorderPainted(false);
        transmissionRisk.setOpaque(false);

        socialDistance.setBorderPainted(false);
        socialDistance.setOpaque(false);

        handWashing.setBorderPainted(false);
        handWashing.setOpaque(false);

        wearingMask.setBorderPainted(false);
        wearingMask.setOpaque(false);

        wearingGlove.setBorderPainted(false);
        wearingGlove.setOpaque(false);

        try {
            R0.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#R0")));
            presets.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#presets")));
            population.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#SIR_Model")));
            duration.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#Equations")));
            transmissionRisk.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#Infection_and_Removal_Rate")));
            socialDistance.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#Social_Distancing")));
            handWashing.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#Washing_Hands")));
            wearingMask.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#Wearing_Masks")));
            wearingGlove.addActionListener(new HTTPListener
                    (new URI("https://www.laptopand.me/behind-the-curve#Wearing_Gloves")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sets the limits and ticks for the sliders
        setFactorSettings(populationSlider, 1000, 2000, 100);
        setFactorSettings(durationSlider, 10, 10, 1);
        setFactorSettings(transmissionRiskSlider, 20, 10, 1);
        setFactorSettings(socialDistanceSlider, 0, 10, 1);
        setFactorSettings(handWashingSlider, 0, 10, 1);
        setFactorSettings(wearingMaskSlider, 0, 10, 1);
        setFactorSettings(wearingGloveSlider, 0, 10, 1);

        // Adds ActionListener to each button
        start.addActionListener(startListener);
        reset.addActionListener(startListener);

        // Add ActionListener to speed buttons
        normalSpeed.addActionListener(new SpeedButtonListener(1000, graphLayout));
        doubleSpeed.addActionListener(new SpeedButtonListener(500, graphLayout));
        x1000Speed.addActionListener(new SpeedButtonListener(1, graphLayout));

        // Adds ActionsListener to ComboBox
        presetList.addActionListener(new PresetListener(presetList, chosenPresets,this));

        // Adds ActionListener to each Slider
        populationSlider.addChangeListener(new SliderListener(chosenPopulation, populationSlider, " People", this));
        durationSlider.addChangeListener(new SliderListener(chosenDuration, durationSlider, " Days", this));
        transmissionRiskSlider.addChangeListener(new SliderListener(chosenTransmissionRisk, transmissionRiskSlider, "%", this));
        socialDistanceSlider.addChangeListener(new SliderListener(chosenSocialDistance, socialDistanceSlider, "%", this));
        handWashingSlider.addChangeListener(new SliderListener(chosenHandWashing, handWashingSlider, "%", this));
        wearingMaskSlider.addChangeListener(new SliderListener(chosenWearingMask, wearingMaskSlider, "%", this));
        wearingGloveSlider.addChangeListener(new SliderListener(chosenWearingGlove, wearingGloveSlider, "%", this));

        // Adds all the components to the JPanel
        add(start);
        add(reset);
        add(R0);

        add(normalSpeed);
        add(doubleSpeed);
        add(x1000Speed);

        add(presets);
        add(presetList);
        add(chosenPresets);

        add(population);
        add(populationSlider);
        add(chosenPopulation);

        add(duration);
        add(durationSlider);
        add(chosenDuration);

        add(transmissionRisk);
        add(transmissionRiskSlider);
        add(chosenTransmissionRisk);

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
