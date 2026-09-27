import java.util.*;

public class SwiftShip {
    interface ShippingType {
        String name();
        double charge(double kg);
    }

    static class Standard implements ShippingType {
        public String name() { return "Standard"; }
        public double charge(double kg) { return 40 + 10 * kg; }
    }

    static class Express implements ShippingType {
        public String name() { return "Express"; }
        public double charge(double kg) { return 80 + 15 * kg; }
    }

    static class Fragile implements ShippingType {
        private final ShippingType base = new Standard();
        public String name() { return "Fragile"; }
        public double charge(double kg) { return base.charge(kg) + 50; }
    }

    interface NotificationChannel {
        void notify(String message);
    }

    static class SMS implements NotificationChannel {
        public void notify(String message) {
            System.out.println("[SMS] " + message);
        }
    }

    static class Email implements NotificationChannel {
        public void notify(String message) {
            System.out.println("[Email] " + message);
        }
    }

    static class Customer {
        private final String name;
        private final List<NotificationChannel> channels;
        Customer(String name, List<NotificationChannel> channels) {
            this.name = name;
            this.channels = List.copyOf(channels);
        }
    }

    enum Status {
        BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
    }

    static class Parcel {
        private final String id;
        private final Customer customer;
        private final ShippingType shipping;
        private final double kg, charge;
        private Status status = Status.BOOKED;

        private Parcel(String id, Customer customer, ShippingType shipping, double kg) {
            if (!Double.isFinite(kg) || kg <= 0)
                throw new IllegalArgumentException("Weight must be positive and finite.");
            this.id = id;
            this.customer = customer;
            this.shipping = shipping;
            this.kg = kg;
            charge = shipping.charge(kg);
            if (!Double.isFinite(charge) || charge < 0)
                throw new IllegalArgumentException("Invalid charge.");
        }

        private void notifyChannels() {
            for (NotificationChannel channel : customer.channels)
                channel.notify(id + " is now " + status + ".");
        }

        void advance(Status next) {
            Status allowed;
            switch (status) {
                case BOOKED: allowed = Status.PICKED_UP; break;
                case PICKED_UP: allowed = Status.IN_TRANSIT; break;
                case IN_TRANSIT: allowed = Status.OUT_FOR_DELIVERY; break;
                case OUT_FOR_DELIVERY: allowed = Status.DELIVERED; break;
                default: allowed = null;
            }

            if (next == null || next != allowed)
                throw new IllegalStateException("Invalid transition: "
                        + status + " -> " + next + " is not allowed.");
            status = next;
            notifyChannels();
        }

        void cancel() {
            if (status != Status.BOOKED)
                throw new IllegalStateException("Cancellation failed: "
                        + id + " can be cancelled only while BOOKED.");
            status = Status.CANCELLED;
            notifyChannels();
        }
    }

    static class ParcelService {
        private final Map<String, Parcel> parcels = new HashMap<>();

        Parcel book(String id, Customer customer, ShippingType type, double kg) {
            if (parcels.containsKey(id))
                throw new IllegalArgumentException("Parcel ID already exists.");
            Parcel parcel = new Parcel(id, customer, type, kg);
            parcels.put(id, parcel);
            System.out.printf(Locale.US,
                    "Parcel %s booked (%s, %.1f kg). Charge: Rs.%.2f.%n",
                    id, type.name(), kg, parcel.charge);
            parcel.notifyChannels();
            return parcel;
        }
    }

    static void attempt(Runnable action) {
        try {
            action.run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Asha", List.of(new SMS(), new Email()));
        Parcel parcel = new ParcelService().book("P101", customer, new Express(), 2);
        parcel.advance(Status.PICKED_UP);
        attempt(parcel::cancel);
        parcel.advance(Status.IN_TRANSIT);
        attempt(() -> parcel.advance(Status.DELIVERED));
    }
}
