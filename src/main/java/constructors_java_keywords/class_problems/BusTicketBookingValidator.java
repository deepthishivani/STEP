package main.java.constructors_java_keywords.class_problems;

import java.util.HashSet;

public class BusTicketBookingValidator {
    static class BusTicket {
        private String passengerName, destination;
        private boolean checkedIn;

        public BusTicket(String passengerName, String destination) {
            if (passengerName == null || destination == null ||
                passengerName.trim().isEmpty() || destination.trim().isEmpty() ||
                !passengerName.trim().matches("[A-Za-z ]+") ||
                !destination.trim().matches("[A-Za-z ]+"))
                throw new IllegalArgumentException();

            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
        }

        void markCheckedIn() {
            if (!checkedIn)
                checkedIn = true;
        }

        static void processBatch(String[][] rawBookings) {
            int valid = 0, rejected = 0, duplicates = 0;
            HashSet<String> seen = new HashSet<>();

            for (String[] r : rawBookings) {
                try {
                    if (r == null || r.length < 2)
                        throw new IllegalArgumentException();

                    BusTicket t = new BusTicket(r[0], r[1]);
                    String key = t.passengerName.toLowerCase() + "|" +
                                 t.destination.toLowerCase();

                    if (!seen.add(key))
                        duplicates++;
                    else
                        valid++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
        }
    }

    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya","Chennai"},
            {"","Bangalore"},
            {"Ravi123","Pune"},
            {"Divya","Chennai"},
            {" "," "}
        };

        BusTicket.processBatch(bookings);
    }
}
