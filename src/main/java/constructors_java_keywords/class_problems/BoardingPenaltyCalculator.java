package main.java.constructors_java_keywords.class_problems;

public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0)
            throw new IllegalArgumentException();

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0)
            throw new IllegalArgumentException();

        if (minutesLate == 0)
            return 0;

        int first = Math.min(minutesLate, 5);
        int second = Math.min(Math.max(minutesLate - 5, 0), 10);
        int third = Math.max(minutesLate - 15, 0);

        double tiered = ticketFare *
            (first * 0.005 + second * 0.01 + third * 0.02);

        double floor = ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(tiered, floor);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator c =
            new BoardingPenaltyCalculator(1);

        System.out.println("Rs " + c.calculatePenalty(1000, 0));
        System.out.println("Rs " + c.calculatePenalty(1000, 1));
        System.out.println("Rs " + c.calculatePenalty(1000, 16));
    }
}
