package abstraction_interface.category_a_practice;

abstract class PaymentMethod {
    private static int nextId = 100;
    private final String transactionId;

    protected PaymentMethod() {
        transactionId = "TXN-" + (++nextId);
    }

    public String getTransactionId() { return transactionId; }
    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }
}

class CreditCardPayment extends PaymentMethod {
    private final String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " + cardNumberLastFour
                + " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {
    public CashPayment() { super(); }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn " + getTransactionId();
    }
}

public class Problem1 {
    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc = new CreditCardPayment("4471");
        CashPayment cash = new CashPayment();

        printConfirmation(cc, 250.0);
        System.out.println(cc.processPayment(250.0, "Birthday gift"));
        printConfirmation(cash, 40.0);

        // PaymentMethod ref = cc; is upcasting.
        // new PaymentMethod() cannot compile because PaymentMethod is abstract.
    }
}
