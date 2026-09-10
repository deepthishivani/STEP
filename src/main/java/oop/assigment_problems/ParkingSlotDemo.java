package main.java.oop.assigment_problems;

public class ParkingSlotDemo {

    static class ParkingSlot {
        String slotNo;
        int capacity, occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot(String vehicleNo) {
            if (occupiedCount < capacity) {
                occupiedCount++;
                System.out.println(vehicleNo +
                        " allotted to slot " + slotNo);
            }
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot s : slots)
            if (s != null && s.occupiedCount < s.capacity)
                return s;

        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot s = findAvailableSlot(slots);

        if (s != null)
            s.allot(vehicleNo);
        else
            System.out.println("No slots available for " + vehicleNo);
    }

    public static void main(String[] args) {

        // The array reference is passed, not copies of ParkingSlot objects.
        // Therefore changes affect the same objects stored in the array.

        ParkingSlot[] available = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(available, "TN09AB1234");

        ParkingSlot[] full = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(full, "TN09AB1234");
    }
}
