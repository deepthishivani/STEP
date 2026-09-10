package main.java.constructors_java_keywords.class_problems;

import java.util.Arrays;

public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty() ||
            totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException();

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {
        long paise = Math.round(totalFare * 100);
        long base = paise / passengerCount;
        long remainder = paise % passengerCount;

        double[] shares = new double[passengerCount];

        for (int i = 0; i < passengerCount; i++)
            shares[i] = base / 100.0;

        for (int i = passengerCount - (int)remainder;
             i < passengerCount; i++)
            if (i >= 0)
                shares[i] += 0.01;

        return shares;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter a = new FareSplitter("TRIP001", 100000, 3);
        FareSplitter b = new FareSplitter("TRIP003");

        System.out.println(Arrays.toString(a.fareBreakdown()));
        System.out.println(Arrays.toString(b.fareBreakdown()));
    }
}
