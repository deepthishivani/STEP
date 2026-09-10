package main.java.constructors_java_keywords.assigment_problems;

public class MultiKitchenReconciliation {

    static class DeliveryAccount {
        protected String studentId;
        protected double orderValue;
        static String campus;

        static {
            campus = "SRM Campus";
        }

        public DeliveryAccount(String studentId,
                               double orderValue) {

            if (studentId == null ||
                studentId.trim().isEmpty() ||
                orderValue < 0)
                throw new IllegalArgumentException();

            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0);
        }

        final double calculateSurgeFee(int delayMinutes) {
            if (delayMinutes < 0)
                throw new IllegalArgumentException();

            if (delayMinutes == 0)
                return 0;

            int first = Math.min(delayMinutes, 5);
            int second =
                Math.min(Math.max(delayMinutes - 5, 0), 10);
            int third =
                Math.max(delayMinutes - 15, 0);

            return orderValue *
                (first * 0.005 +
                 second * 0.01 +
                 third * 0.02);
        }
    }

    static class Premium extends DeliveryAccount {
        Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }
    }

    static void processAccount(DeliveryAccount account,
                               double amount,
                               int delayMinutes) {

        if (account == null)
            return;

        if (amount < 0 || delayMinutes < 0)
            throw new IllegalArgumentException();

        if (account instanceof Premium)
            System.out.println(account.studentId +
                " settled as premium | Amount: Rs " +
                String.format("%.2f", amount));
        else
            System.out.println(account.studentId +
                " settled as regular | Amount: Rs " +
                String.format("%.2f", amount));
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts == null ||
            amounts == null ||
            delayMinutesArray == null ||
            accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length)

            throw new IllegalArgumentException(
                "Mismatched batch data");

        int processed = 0;
        int skipped = 0;
        int premium = 0;
        int regular = 0;

        double totalFees = 0;

        for (int i = 0; i < accounts.length; i++) {

            DeliveryAccount a = accounts[i];

            if (a == null) {
                skipped++;
                continue;
            }

            processAccount(
                a,
                amounts[i],
                delayMinutesArray[i]
            );

            totalFees +=
                a.calculateSurgeFee(
                    delayMinutesArray[i]
                );

            processed++;

            if (a instanceof Premium)
                premium++;
            else
                regular++;
        }

        System.out.println(
            processed + " processed | " +
            skipped + " null skipped | " +
            premium + " premium | " +
            regular + " regular | grand total surge fees = Rs " +
            String.format("%.2f", totalFees)
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001",500),
            null,
            new DeliveryAccount("STU002",300)
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delays = {
            10, 5, 0
        };

        processBatch(accounts, amounts, delays);
    }
}
