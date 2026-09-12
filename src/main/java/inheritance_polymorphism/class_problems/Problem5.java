package inheritance_polymorphism.class_problems;

class P5EventTicket {
    private static int count = 0;
    final String ticketId;
    private double balance;

    P5EventTicket(double price) {
        count++;
        ticketId = "TCK-" + (1000 + count);
        balance = price;
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

    static int getTicketsIssued() {
        return count;
    }

    static boolean isValidPromoCode(String code) {
        return code != null &&
               code.length() == 5 &&
               code.charAt(0) == 'F' &&
               Character.isDigit(code.charAt(1)) &&
               Character.isDigit(code.charAt(2)) &&
               Character.isDigit(code.charAt(3)) &&
               Character.isUpperCase(code.charAt(4));
    }
}

class P5GroupTicket extends P5EventTicket {
    P5GroupTicket(double price, int groupSize) {
        super(price);
    }
}

public class Problem5 {
    static String processNightlySettlement(P5EventTicket[] tickets) {
        int processed = 0, skipped = 0, group = 0, individual = 0;

        for (P5EventTicket t : tickets) {
            if (t == null) {
                skipped++;
                continue;
            }

            processed++;

            if (t instanceof P5GroupTicket)
                group++;
            else
                individual++;
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args) {
        P5EventTicket t1 = new P5EventTicket(500);

        System.out.println(t1.ticketId);
        System.out.println(P5EventTicket.getTicketsIssued());

        System.out.println(P5EventTicket.isValidPromoCode("F123A"));
        System.out.println(P5EventTicket.isValidPromoCode("F12A"));
        System.out.println(P5EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(t1.getBalanceDue());

        System.out.println(processNightlySettlement(
            new P5EventTicket[]{
                new P5GroupTicket(2000, 5),
                null,
                new P5EventTicket(500)
            }));
    }
}
