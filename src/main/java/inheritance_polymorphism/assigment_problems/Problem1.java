package inheritance_polymorphism.assigment_problems;

class A1RaceEntry {
    private double balance;

    A1RaceEntry(String bib, double fee) {
        if (bib == null || bib.trim().length() < 4)
            throw new IllegalArgumentException();
        balance = fee;
    }

    void pay(double amount) { balance -= amount; }
    double getBalanceDue() { return balance; }
}

class A1RunnerEntry extends A1RaceEntry {
    A1RunnerEntry(String bib, double fee, String category) {
        super(bib, fee);
    }
}

public class Problem1 {
    static String registerBatch(String[] bibs, double fee) {
        int registered = 0, rejected = 0;

        for (String bib : bibs) {
            try {
                new A1RaceEntry(bib, fee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new A1RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        A1RunnerEntry r =
            new A1RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        System.out.println(r.getBalanceDue());

        System.out.println(registerBatch(
            new String[]{"BIB1","B1","BIB2"}, 80));
    }
}
