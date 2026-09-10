package main.java.constructors_java_keywords.class_problems;

public class FleetReconciliationEngine {

    static class BusTicketAccount {
        protected String bookingId;
        protected double ticketFare;
        static String depot;

        static {
            depot = "Central Depot";
        }

        public BusTicketAccount(String bookingId, double ticketFare) {
            if (bookingId == null || bookingId.trim().isEmpty() ||
                ticketFare < 0)
                throw new IllegalArgumentException();

            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0);
        }

        final double calculatePenalty(int minutesLate) {
            if (minutesLate < 0)
                throw new IllegalArgumentException();

            if (minutesLate == 0)
                return 0;

            int first = Math.min(minutesLate, 5);
            int second = Math.min(Math.max(minutesLate - 5, 0), 10);
            int third = Math.max(minutesLate - 15, 0);

            return ticketFare *
                (first * 0.005 + second * 0.01 + third * 0.02);
        }
    }

    static class Sleeper extends BusTicketAccount {
        Sleeper(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }
    }

    static void processAccount(BusTicketAccount account,
                               double amount, int minutesLate) {
        if (account == null)
            return;

        if (amount < 0 || minutesLate < 0)
            throw new IllegalArgumentException();

        if (account instanceof Sleeper)
            System.out.println(account.bookingId +
                " settled as sleeper | Amount: Rs " + amount);
        else
            System.out.println(account.bookingId +
                " settled as regular | Amount: Rs " + amount);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts == null || amounts == null ||
            minutesLateArray == null ||
            accounts.length != amounts.length ||
            accounts.length != minutesLateArray.length)
            throw new IllegalArgumentException("Mismatched batch data");

        int processed = 0, skipped = 0, sleeper = 0, regular = 0;
        double totalPenalty = 0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount a = accounts[i];

            if (a == null) {
                skipped++;
                continue;
            }

            processAccount(a, amounts[i], minutesLateArray[i]);

            double penalty = a.calculatePenalty(minutesLateArray[i]);
            totalPenalty += penalty;
            processed++;

            if (a instanceof Sleeper)
                sleeper++;
            else
                regular++;
        }

        System.out.println(processed + " processed | " +
            skipped + " null skipped | " +
            sleeper + " sleeper | " +
            regular + " regular | grand total penalties = Rs " +
            totalPenalty);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLate = {10, 5, 0};

        processBatch(accounts, amounts, minutesLate);
    }
}
