package inheritance_polymorphism.assigment_problems;

class A2RaceEntry {
    protected String bib;
    protected double balance;

    A2RaceEntry(String bib, double fee) {
        this.bib = bib;
        balance = fee;
    }

    double getBalanceDue() { return balance; }

    String announce() {
        return "Race Entry | Bib: " + bib +
               " | Balance: " + balance;
    }
}

class A2RunnerEntry extends A2RaceEntry {
    protected String category;

    A2RunnerEntry(String bib, double fee, String category) {
        super(bib, fee);
        this.category = category;
    }

    @Override
    String announce() {
        return "Runner Entry | Bib: " + bib +
               " | Category: " + category +
               " | Balance: " + balance;
    }
}

class A2EliteRunnerEntry extends A2RunnerEntry {
    private double sponsorBonus;

    A2EliteRunnerEntry(String bib, double fee,
                       String category, double sponsorBonus) {
        super(bib, fee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    String announce() {
        return "Elite Runner | Bib: " + bib +
               " | Category: " + category +
               " | Sponsor Bonus: " + sponsorBonus +
               " | Balance: " + balance;
    }
}

class A2RelayTeamEntry extends A2RaceEntry {
    private int teamSize;

    A2RelayTeamEntry(String bib, double fee, int teamSize) {
        super(bib, fee);
        this.teamSize = teamSize;
    }

    @Override
    String announce() {
        return "Relay Team | Bib: " + bib +
               " | Team Size: " + teamSize +
               " | Balance: " + balance;
    }
}

public class Problem2 {
    static String classifyGeneration(A2RaceEntry e) {
        if (e instanceof A2EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";
        if (e instanceof A2RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";
        return "Base/Runner";
    }

    static double getTotalBalanceDue(A2RaceEntry[] entries) {
        double total = 0;

        for (A2RaceEntry e : entries)
            total += e.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        A2RunnerEntry r =
            new A2RunnerEntry("BIB2001", 80, "Open 10K");

        A2EliteRunnerEntry e =
            new A2EliteRunnerEntry(
                "BIB3001", 150, "Elite Full Marathon", 500);

        A2RelayTeamEntry t =
            new A2RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(r.announce());
        System.out.println(e.announce());
        System.out.println(t.announce());

        System.out.println(classifyGeneration(e));
        System.out.println(classifyGeneration(t));

        System.out.println(getTotalBalanceDue(
            new A2RaceEntry[]{r, e, t}));
    }
}
