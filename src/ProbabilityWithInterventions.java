public class ProbabilityWithInterventions {

    GraphLayout graphLayout;
    ControlLayout controlLayout;
    ODESolver ode;

    private double[] allInterventions;

    private double socialDistancingPct;
    private double handWashingPct;
    private double maskPct;
    private double glovePct;
    private double gownPct;
//    private double N95Pct;
    private double handMaskGownPct;

    private double SOCIALDISTANCING_EFFECTIVENESS = 0;
    private double HANDWASHING_EFFECTIVENESS = 0.55;
    private double MASK_EFFECTIVENESS = 0.68;
    private double GLOVE_EFFECTIVENESS = 0.57;
    private double GOWN_EFFECTIVENESS = 0.77;
//    private double N95_MASK_EFFECTIVENESS = 0.91;
//    private double HAND_MASK_GOWN_EFFECTIVENESS = 0.91;

    private double probabilityOfInfection;

    public ProbabilityWithInterventions(ODESolver ode) {
        graphLayout = new GraphLayout();
        controlLayout = new ControlLayout(graphLayout);

        this.ode = ode;

        allInterventions = controlLayout.getInterventions();
        socialDistancingPct = allInterventions[0];
        handWashingPct = allInterventions[1];
        maskPct = allInterventions[2];
        glovePct = allInterventions[3];
        gownPct = allInterventions[4];

        System.out.println(socialDistancingPct);
        System.out.println(handWashingPct);
        System.out.println(maskPct);
        System.out.println(glovePct);
        System.out.println(gownPct);

        System.out.println("+================");

        probabilityOfInfection = ode.getProbabilityOfInfection();
    }

    public double calculate() {
        probabilityOfInfection *= 1 - (SOCIALDISTANCING_EFFECTIVENESS * socialDistancingPct);
        System.out.println("Probability After SD: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (HANDWASHING_EFFECTIVENESS * handWashingPct);
        System.out.println("Probability After HW: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (MASK_EFFECTIVENESS * maskPct);
        System.out.println("Probability After MW: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (GLOVE_EFFECTIVENESS * glovePct);
        System.out.println("Probability After GlW: " + probabilityOfInfection);
        probabilityOfInfection *= 1 - (GOWN_EFFECTIVENESS * gownPct);
        System.out.println("Probability After GOW: " + probabilityOfInfection);

        System.out.println("============================================");

        return probabilityOfInfection;
    }
}
