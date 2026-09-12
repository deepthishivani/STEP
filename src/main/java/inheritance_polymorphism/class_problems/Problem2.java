package inheritance_polymorphism.class_problems;

class P2EventTicket {
    protected double balance;

    P2EventTicket(String id, double price) {
        balance = price;
    }

    double getBalanceDue() {
        return balance;
    }

    String printTicket() {
        return "Standard Event Ticket | Balance Due: " + balance;
    }
}

class P2WorkshopTicket extends P2EventTicket {
    protected String track;

    P2WorkshopTicket(String id, double price, String track) {
        super(id, price);
        this.track = track;
    }

    @Override
    String printTicket() {
        return "Workshop Ticket | Track: " + track +
               " | Balance Due: " + balance;
    }
}

class P2PremiumWorkshopTicket extends P2WorkshopTicket {
    private double kitFee;

    P2PremiumWorkshopTicket(String id, double price,
                            String track, double kitFee) {
        super(id, price, track);
        this.kitFee = kitFee;
    }

    @Override
    String printTicket() {
        return "Premium Workshop Ticket | Track: " + track +
               " | Kit Fee: " + kitFee +
               " | Balance Due: " + balance;
    }
}

class P2HackathonTicket extends P2EventTicket {
    private String teamName;

    P2HackathonTicket(String id, double price, String teamName) {
        super(id, price);
        this.teamName = teamName;
    }

    @Override
    String printTicket() {
        return "Hackathon Ticket | Team: " + teamName +
               " | Balance Due: " + balance;
    }
}

public class Problem2 {
    static String classifyGeneration(P2EventTicket ticket) {
        if (ticket instanceof P2PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";
        if (ticket instanceof P2HackathonTicket)
            return "Hierarchical sibling (independent branch)";
        return "Base/Workshop";
    }

    static double getTotalBalanceDue(P2EventTicket[] tickets) {
        double total = 0;
        for (P2EventTicket t : tickets)
            total += t.getBalanceDue();
        return total;
    }

    public static void main(String[] args) {
        P2EventTicket a = new P2EventTicket("STU1", 500);
        P2WorkshopTicket b =
            new P2WorkshopTicket("STU2", 1200, "AI/ML");
        P2PremiumWorkshopTicket c =
            new P2PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        P2HackathonTicket d =
            new P2HackathonTicket("STU4", 800, "Byte Force");

        System.out.println(a.printTicket());
        System.out.println(b.printTicket());
        System.out.println(c.printTicket());
        System.out.println(d.printTicket());
        System.out.println(classifyGeneration(c));
        System.out.println(classifyGeneration(d));
        System.out.println(getTotalBalanceDue(
            new P2EventTicket[]{a, b, c, d}));
    }
}
