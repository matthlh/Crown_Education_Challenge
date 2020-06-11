public class OrdinaryDifferentialEquations {
    private double population;
    private double s;
    private double i;
    private double r;
    private double duration;
    private double r0;
    private double transmissionProbability;
    private int averageContactRate;
    private double removalRate;
    private double beta;

    public OrdinaryDifferentialEquations(double population, double s, double i, double r, double duration, double[] interventions, int averageContactRate, double transmissionProbability) {
        this.population = population;
        this. s = s;
        this.i = i;
        this.r = r;
        this.duration = duration;
        this.averageContactRate = averageContactRate;
        this.transmissionProbability = transmissionProbability;

        this.removalRate = 1/duration;
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
        /*
        SIR disease model without vital dynamics

        R0 implies
            no vaccinated
            No way to control spread of disease
            Everyone is susceptible

        R0 is also usually misinterpreted
            It is not a rate over time, but is a


        S' = (-a * SI)/population
        I' = (a * SI)/population - bI
        R' = bI



        int newlyInfected = (a * s * i)/population; force of infection
        int newlyRecovered = duration * i

        s -= newlyInfected;
        i += newlyInfected - newlyRecovered;
        r += newlyRecovered;

        a = transmission from Infected to susceptible chance
        duration = recovery rate

        r0 = a/duration --> infection rate / recovery rate
        a = t * c
        t = probability of infecting if contacted
        c = average rate of contact
        v = 1/duration = infection length

        r0 = t * c * v

        I am using forward euler method or forward euler integration to solve

        My model is consideredd deterministic
        */


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

        System.out.println("R0: " + r0 + "\n");
    }
}
