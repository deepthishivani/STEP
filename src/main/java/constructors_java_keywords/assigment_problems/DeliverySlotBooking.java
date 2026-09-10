package main.java.constructors_java_keywords.assigment_problems;

public class DeliverySlotBooking {

    static class DeliverySlot {
        private String orderId, timeSlot;

        public DeliverySlot(String orderId, String timeSlot) {
            this.orderId = orderId;
            this.timeSlot = timeSlot;
        }

        public DeliverySlot(String orderId) {
            this(orderId, "ASAP");
        }

        boolean isPeakHour() {
            return timeSlot.equals("12:00-13:00") ||
                   timeSlot.equals("13:00-14:00") ||
                   timeSlot.equals("19:00-20:00") ||
                   timeSlot.equals("20:00-21:00");
        }
    }

    public static void main(String[] args) {
        DeliverySlot a =
            new DeliverySlot("ORD101", "13:00-14:00");

        DeliverySlot b =
            new DeliverySlot("ORD102");

        System.out.println(a.isPeakHour());
        System.out.println(b.isPeakHour());
    }
}
