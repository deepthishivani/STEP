package inheritance_polymorphism.class_problems;

class P4EventTicket {
    protected double balance;

    P4EventTicket(double price) {
        balance = price;
    }

    String printTicket() {
        return "Standard | Balance: " + balance;
    }
}

class P4WorkshopTicket extends P4EventTicket {
    private String track;

    P4WorkshopTicket(double price, String track) {
        super(price);
        this.track = track;
    }

    String getTrack() {
        return track;
    }

    @Override
    String printTicket() {
        return "Workshop | Track: " + track +
               " | Balance: " + balance;
    }
}

public class Problem4 {
    static String batchPrint(P4EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (P4EventTicket t : tickets) {
            sb.append(t.printTicket());

            if (t instanceof P4WorkshopTicket) {
                P4WorkshopTicket w = (P4WorkshopTicket)t;
                sb.append(" [Track via downcast: ")
                  .append(w.getTrack())
                  .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        P4EventTicket[] tickets = {
            new P4EventTicket(500),
            new P4WorkshopTicket(1200, "AI/ML")
        };

        System.out.println(batchPrint(tickets));
    }
}
