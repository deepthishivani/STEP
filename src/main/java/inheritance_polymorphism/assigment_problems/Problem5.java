package inheritance_polymorphism.assigment_problems;

class A5RaceEntry {
    private static int count = 0;

    final String entryCode;
    protected double balance;

    A5RaceEntry(String bib, double fee) {
        if (bib == null || bib.trim().length() < 4)
            throw new IllegalArgumentException();

        count++;
        entryCode = "ENT-" + (1000 + count);
        balance = fee;
    }

    void pay(double amount) {
        balance -= amount;
    }

    void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    double getBalanceDue() {
        return balance;
    }

    static int getBibCounter() {
        return count;
    }

    static boolean isValidDiscountCode(String code) {
        return code != null &&
               code.length() == 5 &&
               code.charAt(0) == 'M' &&
               Character.isDigit(code.charAt(1)) &&
               Character.isDigit(code.charAt(2)) &&
               Character.isDigit(code.charAt(3)) &&
               Character.isUpperCase(code.charAt(4));
    }
}

class A5RunnerEntry extends A5RaceEntry {
    A5RunnerEntry(String bib, double fee) {
        super(bib, fee);
    }
}

class A5EliteRunnerEntry extends A5RunnerEntry {
    A5EliteRunnerEntry(String bib, double fee) {
        super(bib, fee);
    }
}

class A5RelayTeamEntry extends A5RaceEntry {
    A5RelayTeamEntry(String bib, double fee, int teamSize) {
        super(bib, fee);
    }
}

public class Problem5 {
    static String settleNight(A5RaceEntry[] entries) {
        int processed = 0;
        int skipped = 0;
        int relay = 0;
        int individual = 0;

        for (A5RaceEntry e : entries) {
            if (e == null) {
                skipped++;
                continue;
            }

            processed++;

            if (e instanceof A5RelayTeamEntry)
                relay++;
            else
                individual++;
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               relay + " relay | " +
               individual + " individual";
    }

    public static void main(String[] args) {
        System.out.println(
            A5RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(
            A5RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(
            A5RaceEntry.isValidDiscountCode("X123A"));

        A5RaceEntry r =
            new A5RaceEntry("BIB1001", 80);

        A5EliteRunnerEntry elite =
            new A5EliteRunnerEntry("BIB3001", 150);

        A5RelayTeamEntry relay =
            new A5RelayTeamEntry("BIB4001", 300, 4);

        r.pay(10, "UPI");

        System.out.println(
            settleNight(
                new A5RaceEntry[]{elite, null, relay}));

        System.out.println(
            A5RaceEntry.getBibCounter());
    }
}
