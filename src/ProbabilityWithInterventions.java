public class ProbabilityWithInterventions {

    GraphLayout graphLayout;
    ControlLayout controlLayout;

    private final double[] allInterventions;

    private final double socialDistancingPercent;
    private final double handWashingPercent;
    private final double maskPercent;
    private final double glovePercent;

    private final double SOCIALDISTANCING_EFFECTIVENESS = 0.81;
    private final double HANDWASHING_EFFECTIVENESS = 0.55;
    private final double MASK_EFFECTIVENESS = 0.68;
    private final double GLOVE_EFFECTIVENESS = 0.57;

    private double probabilityOfInfection;

    public ProbabilityWithInterventions(double probabilityOfInfection, double[] interventions) {
        this.probabilityOfInfection = probabilityOfInfection;

        graphLayout = new GraphLayout();
        controlLayout = new ControlLayout(graphLayout);

        this.allInterventions = interventions;
        socialDistancingPercent = allInterventions[0]/100;
        handWashingPercent = allInterventions[1]/100;
        maskPercent = allInterventions[2]/100;
        glovePercent = allInterventions[3]/100;
    }

    public double calculate() {
        // Calculates the new probability of infection with interventions
        System.out.println("Probability before everything: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (SOCIALDISTANCING_EFFECTIVENESS * socialDistancingPercent);
        System.out.println("Probability After SD: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (HANDWASHING_EFFECTIVENESS * handWashingPercent);
        System.out.println("Probability After HW: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (MASK_EFFECTIVENESS * maskPercent);
        System.out.println("Probability After MW: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (GLOVE_EFFECTIVENESS * glovePercent);
        System.out.println("Probability After GlW: " + probabilityOfInfection);

        System.out.println("============================================");

        return probabilityOfInfection;
    }
}
