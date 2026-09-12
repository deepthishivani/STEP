package inheritance_polymorphism.class_problems;

class EventTicket {
    private String attendeeId;
    private double balanceDue;

    EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException();

        this.attendeeId = attendeeId;
        this.balanceDue = basePrice;
    }

    void pay(double amount) {
        balanceDue -= amount;
    }

    double getBalanceDue() {
        return balanceDue;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class Problem1 {

    static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0, rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};
        System.out.println(registerBatch(ids, 500));
    }
}
