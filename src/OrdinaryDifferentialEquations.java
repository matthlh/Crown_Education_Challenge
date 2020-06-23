public class OrdinaryDifferentialEquations {
    private final double population;
    private double s;
    private double i;
    private double r;
    private final double duration;
    private final double r0;
    private double transmissionProbability;
    private final double averageContactRate;
    private final double removalRate;
    private final double beta;
    private final ControlLayout controlLayout;

    public OrdinaryDifferentialEquations(double population, double s, double i, double r, double duration, double[] interventions, double averageContactRate, double transmissionProbability, ControlLayout controlLayout) {
        this.population = population;
        this. s = s;
        this.i = i;
        this.r = r;
        this.duration = duration;
        this.averageContactRate = averageContactRate;
        this.transmissionProbability = transmissionProbability;
        this.controlLayout = controlLayout;

        if(duration != 0) {
            this.removalRate = 1/duration;
        } else {
            this.removalRate = 1;
        }
        this.transmissionProbability = new ProbabilityWithInterventions(transmissionProbability, interventions).calculate();

        this.beta = this.averageContactRate * this.transmissionProbability;
        System.out.println("BETA: " + beta);
        System.out.println("averageContactRate: " + this.averageContactRate);
        System.out.println("transmissionProbability: " + this.transmissionProbability);
        System.out.println("recover Rate: " + this.removalRate);
        System.out.println("===============================================");

        this.r0 = beta * duration;


    }

    public double getinfected() {
        return i;
    }

    public void calculate(GraphLayout graphLayout) {
        // Number of people who will be infected this round
        double newlyInfected = ((beta * s * i)/population);
        if(s < newlyInfected) {
            newlyInfected = s;
        }

        // Number of people who will be recovered this time period
        double newlyRecovered = (removalRate * i);

        // update the stats
        s -= newlyInfected;
        i += newlyInfected;
        i -= newlyRecovered;
        r += newlyRecovered;

        // update the arrays
        graphLayout.susceptibleArray.add(s);
        graphLayout.infectedArray.add(i);
        graphLayout.recoveredArray.add(r);

        System.out.println("duration: " + duration);
        System.out.println("transmissionProbability: " + transmissionProbability);
        System.out.println("newlyinfected: " + newlyInfected);
        System.out.println("newlyrecovered: " + newlyRecovered);
        System.out.println("s: " + s);
        System.out.println("i: " + i);
        System.out.println("r: " + r + "\n");
        System.out.println("Total: " + (s + i + r));

        controlLayout.R0.setText("R0: " + String.format("%.2f", r0));
    }
}
