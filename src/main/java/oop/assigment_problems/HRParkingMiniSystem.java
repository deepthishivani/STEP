package main.java.oop.assigment_problems;

public class HRParkingMiniSystem {

    static class Employee {
        private String empId, empName;
        private double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }

        double effectiveSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(String id, String name,
                        double salary, double teamBonus) {
            super(id, name, salary);
            this.teamBonus = teamBonus;
        }

        @Override
        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {
        private double stipendCap;

        InternEmployee(String id, String name,
                       double salary, double stipendCap) {
            super(id, name, salary);
            this.stipendCap = stipendCap;
        }

        @Override
        double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    static class ParkingSlot {
        String slotNo;
        int capacity, occupiedCount;

        ParkingSlot(String slotNo, int capacity) {
            this.slotNo = slotNo;
            this.capacity = capacity;
        }

        void allot() {
            if (occupiedCount < capacity)
                occupiedCount++;
        }
    }

    static class CompanyEmployeeRecord {
        String name, empId;
        Employee employee;
        ParkingSlot slot;

        static int totalRecords = 0;

        CompanyEmployeeRecord(String name, String empId,
                              Employee employee) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            totalRecords++;
        }

        String fullProfile() {
            return name +
                    " | Pay: Rs " +
                    employee.effectiveSalary() +
                    " | Slot: " +
                    (slot == null ?
                            "no parking assigned" : slot.slotNo);
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot s : slots)
            if (s != null && s.occupiedCount < s.capacity)
                return s;

        return null;
    }

    static void safeAllot(CompanyEmployeeRecord e,
                          ParkingSlot[] slots) {

        ParkingSlot s = findAvailableSlot(slots);

        if (s != null) {
            s.allot();
            e.slot = s;
        }
    }

    public static void main(String[] args) {

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1),
            new ParkingSlot("A2", 1)
        };

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E1",
                        new ManagerEmployee(
                                "E1", "Divya", 70000, 8000)
                );

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E2",
                        new Employee(
                                "E2", "Karan", 40000)
                );

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E3",
                        new InternEmployee(
                                "E3", "Meera", 12000, 10000)
                );

        safeAllot(divya, slots);
        safeAllot(karan, slots);

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println("Total records: " +
                CompanyEmployeeRecord.totalRecords);
    }
}
