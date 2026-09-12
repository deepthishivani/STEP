package inheritance_polymorphism.class_problems;

import java.util.Arrays;

class P3EventTicket {
    private double balance;
    private double[] history = new double[10];
    private int count = 0;

    P3EventTicket(double price) {
        balance = price;
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

class P3WorkshopTicket extends P3EventTicket {
    P3WorkshopTicket(double price) {
        super(price);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        P3WorkshopTicket w = new P3WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(
            Arrays.toString(w.getLateFeeHistory()));
    }
}
