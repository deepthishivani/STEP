package inheritance_polymorphism.assigment_problems;

class A4RaceEntry {
    protected String bib;
    protected double balance;

    A4RaceEntry(String bib, double fee) {
        this.bib = bib;
        balance = fee;
    }

    String announce() {
        return "Race Entry | Bib: " + bib +
               " | Balance: " + balance;
    }
}

class A4RunnerEntry extends A4RaceEntry {
    private String category;

    A4RunnerEntry(String bib, double fee, String category) {
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

class A4RelayTeamEntry extends A4RaceEntry {
    private int teamSize;

    A4RelayTeamEntry(String bib, double fee, int teamSize) {
        super(bib, fee);
        this.teamSize = teamSize;
    }

    int getTeamSize() {
        return teamSize;
    }

    @Override
    String announce() {
        return "Relay Team | Bib: " + bib +
               " | Team Size: " + teamSize +
               " | Balance: " + balance;
    }
}

public class Problem4 {
    static String announceAll(A4RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();

        for (A4RaceEntry e : entries) {
            sb.append(e.announce());

            if (e instanceof A4RelayTeamEntry) {
                A4RelayTeamEntry r =
                    (A4RelayTeamEntry) e;

                sb.append(" [Team size via downcast: ")
                  .append(r.getTeamSize())
                  .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        A4RaceEntry runner =
            new A4RunnerEntry(
                "BIB2001", 90, "Open 10K");

        A4RaceEntry relay =
            new A4RelayTeamEntry(
                "BIB4001", 300, 4);

        System.out.println(
            announceAll(new A4RaceEntry[]{runner, relay}));
    }
}
