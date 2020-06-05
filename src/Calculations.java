import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Calculations {

    private double n;
    private double s;
    private double i;
    private double r;
    private int count;
    private Queue<Double> infectedPerDay;

    private int reproduction;
    private int duration;
    public Calculations(double n, double s, double i, double r, int duration, int reproduction) {
        this.n = n;
        this. s = s;
        this.i = i;
        this.r = r;
        this.duration = duration;
        this.reproduction = reproduction;
        count = 0;
        infectedPerDay = new LinkedList<>();
    }

    public double getI() {
        return i;
    }

    public double getR() {
        return r;
    }

    public double getS() {
        return s;
    }

    public void calculate(GraphLayout graphLayout) {
        double fractionS = s/n;
        infectedPerDay.add(i);
        double newInfected = i * reproduction * fractionS;
        i += newInfected;

        if(count > 10) {
            r += infectedPerDay.poll();
        }

        s -= newInfected;

        graphLayout.sus.add(s);
        graphLayout.inf.add(i);
        graphLayout.rec.add(r);

        System.out.println(s);
        System.out.println(i);
        System.out.println(r);

        if(s + i + r > n) {
            System.out.println("THERES AN ERRRRROROROROR");
        }
        count++;
    }
}
