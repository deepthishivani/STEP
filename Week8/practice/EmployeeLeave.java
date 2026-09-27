import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmployeeLeave {
    enum Status { Pending, Approved, Rejected }

    static abstract class Employee {
        private final String name;
        Employee(String name) { this.name = name; }
        abstract int maxDaysPerRequest();
    }

    static class FullTime extends Employee {
        FullTime(String name) { super(name); }
        int maxDaysPerRequest() { return 30; }
    }
    static class PartTime extends Employee {
        PartTime(String name) { super(name); }
        int maxDaysPerRequest() { return 10; }
    }
    static class Contract extends Employee {
        Contract(String name) { super(name); }
        int maxDaysPerRequest() { return 5; }
    }

    static class LeaveRequest {
        private final Employee employee;
        private final LocalDate start, end;
        private Status status = Status.Pending;

        private LeaveRequest(Employee employee,
                             LocalDate start, LocalDate end) {
            this.employee = employee;
            this.start = start;
            this.end = end;
        }

        void resetToPending() {
            if (status != Status.Pending)
                throw new IllegalStateException("Cannot change status: "
                        + status + " request cannot revert to Pending.");
        }
    }

    static class LeaveManager {
        private final List<LeaveRequest> requests = new ArrayList<>();

        LeaveRequest submit(Employee employee,
                            LocalDate start, LocalDate end) {
            if (end.isBefore(start))
                throw new IllegalArgumentException("Invalid leave dates.");
            LeaveRequest request = new LeaveRequest(employee, start, end);
            requests.add(request);
            System.out.println("Leave request submitted by " + employee.name
                    + " for " + start + " to " + end + ". Status: Pending.");
            return request;
        }

        void review(LeaveRequest request, boolean approve) {
            if (!requests.contains(request) || request.status != Status.Pending)
                throw new IllegalStateException("Only pending requests can be reviewed.");
            long days = ChronoUnit.DAYS.between(request.start, request.end) + 1;
            request.status = approve
                    && days <= request.employee.maxDaysPerRequest()
                    ? Status.Approved : Status.Rejected;
            System.out.println("Leave request for " + request.employee.name
                    + ": " + request.status + ".");
        }
    }

    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        LeaveRequest john = manager.submit(new FullTime("John Doe"),
                LocalDate.parse("2024-10-10"),
                LocalDate.parse("2024-10-12"));
        manager.review(john, true);

        manager.submit(new PartTime("Jane Smith"),
                LocalDate.parse("2024-11-01"),
                LocalDate.parse("2024-11-05"));

        try {
            john.resetToPending();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
