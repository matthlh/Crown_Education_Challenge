import java.util.LinkedList;
import java.util.Queue;

public class OrdinaryDifferentialEquations {
    private double population;
    private double s;
    private double i;
    private double r;
    private Queue<Double> dailyInfections;
    private double a;
    private double b;
    private double contactRatePerDay;
    private double transmissionPercentge;
    private double r0;
    private double probabilityOfInfection;

    public OrdinaryDifferentialEquations(double population, double s, double i, double r, double b) {
        this.population = population;
        this. s = s;
        this.i = i;
        this.r = r;
        this.b = b;
        dailyInfections = new LinkedList<>();
        dailyInfections.add(i);
        contactRatePerDay = 0.2945;
        transmissionPercentge = 0.6;
        a = contactRatePerDay * b;
        r0 = 3.5;
        probabilityOfInfection = r0/a;
        probabilityOfInfection = new ProbabilityWithInterventions(probabilityOfInfection).calculate();
    }

    public double getinfectedArrayectedArray() {
        return i;
    }

    public double getProbabilityOfInfection() {
        return probabilityOfInfection;
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
        int newlyRecovered = b * i

        s -= newlyInfected;
        i += newlyInfected - newlyRecovered;
        r += newlyRecovered;

        a = transmission from Infected to susceptible chance
        b = recovery rate

        r0 = a/b --> infection rate / recovery rate
        a = t * c
        t = probability of infecting if contacted
        c = average rate of contact
        v = 1/b = infection length

        r0 = t * c * v

        I am using forward euler method or forward euler integration to solve
        */


        // Number of people who will be infected this round
        double newlyInfected = (probabilityOfInfection * s * i)/population;
        if(s < newlyInfected) {
            newlyInfected = s;
        }

        dailyInfections.add(newlyInfected);

        // Number of people who will be recovered this time period
        double newlyRecovered = i/b;
//        if(newlyRecovered.size() >= b) {
//            newlyRecovered = (newlyRecovered.poll());
//            if(b == 0) {
//                newlyRecovered = 0;
//                newlyRecovered = 0;
//            }
//        }

        // update the stats
        s -= newlyInfected;
        i += newlyInfected - newlyRecovered;
        r += newlyRecovered;

        // update the arrays
        graphLayout.susceptibleArray.add(s);
        graphLayout.infectedArray.add(i);
        graphLayout.recoveredArray.add(r);

        System.out.println("b: " + b);
        System.out.println("a: " + a);
        System.out.println("newlyinfectedArrayectedArray: " + newlyInfected);
        System.out.println("newlyrecoveredArrayoveredArray: " + newlyRecovered);
        System.out.println("dailyinfectedArrayections: " + dailyInfections);
        System.out.println("s: " + s);
        System.out.println("i: " + i);
        System.out.println("r: " + r + "\n");

        if(s + i + r > population) {
            System.out.println("THERES AN ERRRRROROROROR");
        }
    }
}
