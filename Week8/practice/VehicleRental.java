import java.util.*;

public class VehicleRental {
    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
    }

    static abstract class Vehicle {
        private final String name;
        private Rental active;
        Vehicle(String name) { this.name = name; }
        abstract long dailyRate();
        long charge(int days) {
            return Math.multiplyExact(dailyRate(), days);
        }
    }

    static class StandardCar extends Vehicle {
        StandardCar(String name) { super(name); }
        long dailyRate() { return 50; }
    }

    static class LuxuryCar extends Vehicle {
        LuxuryCar(String name) { super(name); }
        long dailyRate() { return 100; }
    }

    static class SUV extends Vehicle {
        SUV(String name) { super(name); }
        long dailyRate() { return 80; }
    }

    static class Rental {
        private final Customer customer;
        private final Vehicle vehicle;
        private final int days;
        private final long amount;
        private boolean returned;

        private Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.amount = vehicle.charge(days);
        }
    }

    static class RentalService {
        private final List<Rental> rentals = new ArrayList<>();

        Rental rent(Customer customer, Vehicle vehicle, int days) {
            if (days <= 0)
                throw new IllegalArgumentException("Duration must be positive.");
            if (vehicle.active != null)
                throw new IllegalStateException("Vehicle already rented.");

            Rental rental = new Rental(customer, vehicle, days);
            rentals.add(rental);
            vehicle.active = rental;
            System.out.printf(Locale.US,
                    "%s rented for %d days. Total charge: $%.2f%n",
                    vehicle.name, days, (double) rental.amount);
            return rental;
        }

        void returnVehicle(Rental rental) {
            if (!rentals.contains(rental) || rental.returned
                    || rental.vehicle.active != rental)
                throw new IllegalStateException("No active rental to return.");

            rental.returned = true;
            rental.vehicle.active = null;
            System.out.println(rental.vehicle.name
                    + " returned. Now available.");
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();
        Customer customer = new Customer("Asha");

        Rental luxury = service.rent(customer,
                new LuxuryCar("Luxury Car A"), 3);
        service.rent(customer, new StandardCar("Standard Car B"), 5);
        service.returnVehicle(luxury);
    }
}
