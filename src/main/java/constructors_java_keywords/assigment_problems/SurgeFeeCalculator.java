package main.java.constructors_java_keywords.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0)
            throw new IllegalArgumentException();

        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue,
                                   int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0)
            throw new IllegalArgumentException();

        if (delayMinutes == 0)
            return 0;

        int first = Math.min(delayMinutes, 5);
        int second =
            Math.min(Math.max(delayMinutes - 5, 0), 10);
        int third =
            Math.max(delayMinutes - 15, 0);

        double tiered =
            orderValue *
            (first * 0.005 +
             second * 0.01 +
             third * 0.02);

        double floor =
            orderValue * minimumSurgePercent / 100.0;

        return Math.max(tiered, floor);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator c =
            new SurgeFeeCalculator(1);

        System.out.printf("Rs %.1f%n",
            c.calculateSurgeFee(500, 0));

        System.out.printf("Rs %.1f%n",
            c.calculateSurgeFee(500, 1));

        System.out.printf("Rs %.1f%n",
            c.calculateSurgeFee(500, 16));
    }
}
