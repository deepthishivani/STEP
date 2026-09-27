package abstraction_interface.practice_problems;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Problem3 {
    interface Pricing {
        long perNight();
    }
    static class Standard implements Pricing {
        public long perNight() { return 150; }
    }
    static class Deluxe implements Pricing {
        public long perNight() { return 200; }
    }
    static class Suite implements Pricing {
        public long perNight() { return 300; }
    }

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
    }

    static class Room {
        private final String name;
        private final Pricing pricing;
        private final List<Reservation> bookings = new ArrayList<>();

        Room(String name, Pricing pricing) {
            this.name = name;
            this.pricing = pricing;
        }

        boolean available(LocalDate start, LocalDate end) {
            for (Reservation r : bookings)
                if (!r.cancelled && start.isBefore(r.end)
                        && r.start.isBefore(end))
                    return false;
            return true;
        }
    }

    static class Reservation {
        private final Customer customer;
        private final Room room;
        private final LocalDate start, end;
        private final LocalDateTime deadline;
        private final long amount;
        private boolean cancelled;

        private Reservation(Customer customer, Room room,
                            LocalDate start, LocalDate end) {
            this.customer = customer;
            this.room = room;
            this.start = start;
            this.end = end;
            deadline = start.atStartOfDay().minusHours(24);
            amount = Math.multiplyExact(
                    ChronoUnit.DAYS.between(start, end),
                    room.pricing.perNight());
        }
    }

    static class BookingManager {
        private final Clock clock;
        private final Set<Reservation> reservations = new HashSet<>();

        BookingManager(Clock clock) { this.clock = clock; }

        Reservation book(Customer customer, Room room,
                         LocalDate start, LocalDate end) {
            if (!start.isBefore(end))
                throw new IllegalArgumentException("Invalid booking dates.");

            if (!room.available(start, end)) {
                System.out.println("Booking failed: " + room.name
                        + " is not available for " + start + " to " + end + ".");
                return null;
            }

            Reservation r = new Reservation(customer, room, start, end);
            room.bookings.add(r);
            reservations.add(r);
            System.out.printf(Locale.US,
                    "%s booked from %s to %s. Total price: $%.2f%n",
                    room.name, start, end, (double) r.amount);
            return r;
        }

        void cancel(Reservation r) {
            if (r == null || !reservations.contains(r) || r.cancelled)
                throw new IllegalStateException("No active reservation.");
            if (!LocalDateTime.now(clock).isBefore(r.deadline))
                throw new IllegalStateException("Cancellation deadline passed.");
            r.cancelled = true;
            System.out.println("Reservation for " + r.room.name
                    + " cancelled successfully.");
        }
    }

    public static void main(String[] args) {
        Clock clock = Clock.fixed(
                Instant.parse("2024-11-29T10:00:00Z"), ZoneOffset.UTC);
        BookingManager manager = new BookingManager(clock);
        Customer customer = new Customer("Asha");
        Room deluxe = new Room("Deluxe Room 101", new Deluxe());

        Reservation first = manager.book(customer, deluxe,
                LocalDate.parse("2024-12-01"),
                LocalDate.parse("2024-12-05"));

        manager.book(customer, new Room("Standard Room 205", new Standard()),
                LocalDate.parse("2024-12-03"),
                LocalDate.parse("2024-12-07"));

        manager.book(customer, deluxe,
                LocalDate.parse("2024-12-03"),
                LocalDate.parse("2024-12-07"));

        manager.cancel(first);
    }
}
