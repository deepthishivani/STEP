package main.java.oop.class_problems;

public class HostelRoomDemo {

    static class HostelRoom {
        String roomNo;
        int beds, occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {
            if (occupied < beds) {
                occupied++;
                System.out.println(name + " allotted to room " + roomNo);
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom r : rooms)
            if (r != null && r.occupied < r.beds)
                return r;
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom r = findAvailableRoom(rooms);

        if (r != null)
            r.allot(studentName);
        else
            System.out.println("No rooms available for " + studentName);
    }

    public static void main(String[] args) {

        // Passing an array copies only its reference value, not the HostelRoom
        // objects themselves, so changes to a room affect the same object.

        HostelRoom[] available = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        safeAllot(available, "Divya");

        HostelRoom[] full = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };

        safeAllot(full, "Divya");
    }
}
