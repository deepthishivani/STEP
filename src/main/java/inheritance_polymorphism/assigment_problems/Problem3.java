package inheritance_polymorphism.assigment_problems;

import java.util.Arrays;

class A3RaceEntry {
    private double balance;
    private double[] history = new double[10];
    private int count = 0;

    A3RaceEntry(String bib, double fee) {
        balance = fee;
    }

    void pay(double amount) {
        balance -= amount;
    }

    double getBalanceDue() {
        return balance;
    }

    protected void applyLateFee(double amount) {
        balance += amount;
        history[count++] = amount;
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(history, count);
    }
}

class A3RunnerEntry extends A3RaceEntry {
    A3RunnerEntry(String bib, double fee, String category) {
        super(bib, fee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        A3RunnerEntry r =
            new A3RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(
            Arrays.toString(r.getLateFeeHistory()));
    }
}
