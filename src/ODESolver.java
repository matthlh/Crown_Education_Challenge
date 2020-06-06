import java.util.LinkedList;
import java.util.Queue;

public class ODESolver {
    private ControlLayout controlLayout;

    private double n;
    private double s;
    private double i;
    private double r;
    private Queue<Double> dailyInfections;
    private double a;
    private double b;
    private double contactRate_PerDay;
    private double transmissionPercentge;

    private double HANDWASHING_EFFECTIVENESS = 0.55;
    private double MASK_EFFECTIVENESS = 0.68;
    private double N95_MASK_EFFECTIVENESS = 0.91;
    private double GLOVE_EFFECTIVENESS = 0.57;
    private double GOWN_EFFECTIVENESS = 0.77;
    private double HAND_MASK_GOWN_EFFECTIVENESS = 0.91;

    public ODESolver(double n, double s, double i, double r, double b) {
        this.n = n;
        this. s = s;
        this.i = i;
        this.r = r;
        this.b = b;
        dailyInfections = new LinkedList<>();
        dailyInfections.add(i);
        contactRate_PerDay = 0.2945;
        transmissionPercentge = 0.2;
        a = contactRate_PerDay * transmissionPercentge;
    }

    public double getI() {
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


        S' = (-a * SI)/n
        I' = (a * SI)/n - bI
        R' = bI



        int newlyInfected = (a * s * i)/n; force of infection
        int newlyRecovered = b * i

        s -= newlyInfected;
        i += newlyInfected - newlyRecovered;
        r += newlyRecovered;

        a = transmission from infected to susceptible chance
        b = recovery rate

        r0 = a/b --> recovery rate /infection rate
        a = t * c
        t = probability of infecting if contacted
        c = average rate of contact
        v = 1/b = infection length

        r0 = t * c * v

        I am using forward euler method or forward euler integration to solve
        */


        // Number of people who will be infected this round
        double newlyInfected = Math.round((a * s * i)/n);
        if(s < newlyInfected) {
            newlyInfected = s;
        }

            dailyInfections.add(newlyInfected);

        // Number of people who will be recovered this time period
        double newlyRecovered = 0.0;
        if(dailyInfections.size() >= b) {
            newlyRecovered = Math.round(dailyInfections.poll());
            if(b == 0) {
                newlyInfected = 0;
                newlyRecovered = 0;
            }
        }

        // update the stats
        s -= newlyInfected;
        i += newlyInfected - newlyRecovered;
        r += newlyRecovered;

        // update the arrays
        graphLayout.sus.add(s);
        graphLayout.inf.add(i);
        graphLayout.rec.add(r);

        System.out.println("b: " + b);
        System.out.println("a: " + a);
        System.out.println("newlyInfected: " + newlyInfected);
        System.out.println("newlyRecovered: " + newlyRecovered);
        System.out.println("dailyInfections: " + dailyInfections);
        System.out.println("s: " + s);
        System.out.println("i: " + i);
        System.out.println("r: " + r + "\n");

        if(s + i + r > n) {
            System.out.println("THERES AN ERRRRROROROROR");
        }
    }
}
