package abstraction_interface.practice_problems;

import java.util.*;

public class Problem5 {
    interface PaymentMethod {
        boolean pay(long cents);
        String name();
    }

    static class CreditCard implements PaymentMethod {
        public boolean pay(long cents) { return true; }
        public String name() { return "Credit Card"; }
    }

    static class DigitalWallet implements PaymentMethod {
        private final boolean succeeds;
        DigitalWallet(boolean succeeds) { this.succeeds = succeeds; }
        public boolean pay(long cents) { return succeeds; }
        public String name() { return "Digital Wallet"; }
    }

    interface Notifier {
        void send(Customer customer, String message);
    }

    static class ConsoleNotifier implements Notifier {
        public void send(Customer customer, String message) {
            System.out.println("Notification to " + customer.name + ": " + message);
        }
    }

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
    }

    static class Restaurant {
        private final String name;
        private final List<FoodItem> menu = new ArrayList<>();
        Restaurant(String name) { this.name = name; }

        FoodItem add(String name, long cents) {
            if (cents <= 0)
                throw new IllegalArgumentException("Price must be positive.");
            FoodItem item = new FoodItem(this, name, cents);
            menu.add(item);
            return item;
        }
    }

    static class FoodItem {
        private final Restaurant restaurant;
        private final String name;
        private final long cents;

        private FoodItem(Restaurant restaurant, String name, long cents) {
            this.restaurant = restaurant;
            this.name = name;
            this.cents = cents;
        }
    }

    static class LineItem {
        private final FoodItem food;
        private final int quantity;
        private LineItem(FoodItem food, int quantity) {
            this.food = food;
            this.quantity = quantity;
        }
        long total() {
            return Math.multiplyExact(food.cents, quantity);
        }
    }

    enum Status { Draft, PendingPayment, Paid }

    static class Order {
        private final int id;
        private final Customer customer;
        private final Notifier notifier;
        private final List<LineItem> items = new ArrayList<>();
        private Status status = Status.Draft;

        Order(int id, Customer customer, Notifier notifier) {
            this.id = id;
            this.customer = customer;
            this.notifier = notifier;
        }

        void add(FoodItem item, int quantity) {
            if (status != Status.Draft)
                throw new IllegalStateException("Order items are locked.");
            if (quantity <= 0)
                throw new IllegalArgumentException("Quantity must be positive.");
            items.add(new LineItem(item, quantity));
            System.out.println("Added " + item.name + " (Qty " + quantity + ").");
        }

        void place(PaymentMethod method) {
            if (items.isEmpty()) {
                System.out.println("Cannot place order: Order must contain at least one item.");
                return;
            }
            if (status == Status.Paid)
                throw new IllegalStateException("Order already paid.");

            long total = 0;
            for (LineItem item : items)
                total = Math.addExact(total, item.total());

            status = Status.PendingPayment;
            if (method.pay(total)) {
                status = Status.Paid;
                System.out.println("Payment via " + method.name()
                        + " successful. Order placed successfully. Status: Paid.");
                notifier.send(customer, "Order #" + id + " placed and paid.");
            } else {
                System.out.println("Payment via " + method.name()
                        + " failed. Status: Pending Payment.");
                notifier.send(customer, "Order #" + id + " awaiting payment.");
            }
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Asha");
        Notifier notifier = new ConsoleNotifier();
        Restaurant restaurant = new Restaurant("Campus Cafe");

        Order order = new Order(123, customer, notifier);
        order.add(restaurant.add("Pizza", 1000), 2);
        order.add(restaurant.add("Soda", 200), 1);

        new Order(125, customer, notifier).place(new CreditCard());
        order.place(new CreditCard());

        Order other = new Order(124, customer, notifier);
        other.add(restaurant.add("Burger", 500), 1);
        other.place(new DigitalWallet(false));
    }
}
