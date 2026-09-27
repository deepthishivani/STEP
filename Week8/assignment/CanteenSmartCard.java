import java.math.*;
import java.util.*;

public class CanteenSmartCard {
    interface PricingPlan {
        long price(long paise);
    }

    static long percent(long paise, int percentage) {
        return BigDecimal.valueOf(paise)
                .multiply(BigDecimal.valueOf(percentage))
                .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
                .longValueExact();
    }

    static class DayScholar implements PricingPlan {
        public long price(long paise) { return paise; }
    }
    static class Hosteller implements PricingPlan {
        public long price(long paise) { return percent(paise, 90); }
    }
    static class Staff implements PricingPlan {
        public long price(long paise) { return percent(paise, 80); }
    }

    enum Kind { TOP_UP, PURCHASE, REFUND }

    static class Transaction {
        private final int id;
        private final Kind kind;
        private final String description;
        private final long amount;
        private final Integer purchaseId;

        private Transaction(int id, Kind kind, String description,
                            long amount, Integer purchaseId) {
            this.id = id;
            this.kind = kind;
            this.description = description;
            this.amount = amount;
            this.purchaseId = purchaseId;
        }
    }

    static String money(long paise) {
        return BigDecimal.valueOf(paise, 2).toPlainString();
    }

    static class SmartCard {
        private static final long MAX_BALANCE = 500000;
        private final String id;
        private final PricingPlan plan;
        private final List<Transaction> transactions = new ArrayList<>();
        private boolean blocked;

        SmartCard(String id, PricingPlan plan) {
            this.id = id;
            this.plan = plan;
        }

        long balance() {
            long total = 0;
            for (Transaction transaction : transactions)
                total = Math.addExact(total, transaction.amount);
            return total;
        }

        private void requireActive() {
            if (blocked)
                throw new IllegalStateException("Card is blocked.");
        }

        private int record(Kind kind, String description,
                           long amount, Integer purchaseId) {
            int number = transactions.size() + 1;
            transactions.add(new Transaction(number, kind, description,
                    amount, purchaseId));
            return number;
        }

        void topUp(long paise) {
            requireActive();
            if (paise < 10000)
                throw new IllegalArgumentException("Minimum top-up is Rs.100.00.");
            if (paise > MAX_BALANCE - balance())
                throw new IllegalArgumentException("Maximum balance is Rs.5000.00.");

            record(Kind.TOP_UP, "Top-up", paise, null);
            System.out.println(id + " topped up with Rs." + money(paise)
                    + ". Balance: Rs." + money(balance()) + ".");
        }

        int purchase(String item, long listedPaise) {
            requireActive();
            if (listedPaise <= 0)
                throw new IllegalArgumentException("Item price must be positive.");

            long charged = plan.price(listedPaise);
            if (charged < 0)
                throw new IllegalArgumentException("Invalid plan price.");
            if (charged > balance())
                throw new IllegalStateException(
                        "Purchase failed: Insufficient balance (required Rs."
                        + money(charged) + ", available Rs." + money(balance()) + ").");

            int purchaseId = record(Kind.PURCHASE, item, -charged, null);
            System.out.println(item + " purchased for Rs." + money(charged)
                    + ". Balance: Rs." + money(balance()) + ".");
            return purchaseId;
        }

        void refund(int purchaseId) {
            Transaction original = null;
            for (Transaction transaction : transactions)
                if (transaction.id == purchaseId && transaction.kind == Kind.PURCHASE)
                    original = transaction;

            if (original == null)
                throw new IllegalArgumentException("Purchase does not belong to this card.");

            for (Transaction transaction : transactions)
                if (transaction.kind == Kind.REFUND
                        && Objects.equals(transaction.purchaseId, purchaseId))
                    throw new IllegalStateException("Refund rejected: "
                            + original.description + " has already been refunded.");

            long amount = -original.amount;
            if (amount > MAX_BALANCE - balance())
                throw new IllegalStateException("Refund rejected: Maximum balance would be exceeded.");

            record(Kind.REFUND, original.description, amount, purchaseId);
            System.out.println("Refund of Rs." + money(amount) + " for "
                    + original.description + " processed. Balance: Rs."
                    + money(balance()) + ".");
        }

        void block() { blocked = true; }
        void unblock() { blocked = false; }

        void statement() {
            StringJoiner values = new StringJoiner(", ");
            for (Transaction transaction : transactions)
                values.add((transaction.amount >= 0 ? "+" : "")
                        + money(transaction.amount));
            System.out.println("Mini-statement for " + id + ": "
                    + values + " = Rs." + money(balance()) + ".");
        }
    }

    static void attempt(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new Hosteller());
        card.topUp(50000);
        int thali = card.purchase("Veg Thali", 12000);
        card.purchase("Cold Coffee", 6000);
        attempt(() -> card.purchase("Other items", 40000));

        card.refund(thali);
        attempt(() -> card.refund(thali));
        card.statement();

        card.block();
        attempt(() -> card.topUp(10000));
        attempt(() -> card.purchase("Tea", 1000));
        card.unblock();
    }
}
