package main.java.oop.assigment_problems;

public class EmployeeDemo {

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
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(String id, String name,
                        double salary, double teamBonus) {
            super(id, name, salary);
            this.teamBonus = teamBonus;
        }

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

        double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    public static void main(String[] args) {
        Employee plain =
                new Employee("E1", "Karan", 40000);

        Employee manager =
                new ManagerEmployee("E2", "Divya", 70000, 8000);

        Employee intern =
                new InternEmployee("E3", "Meera", 12000, 10000);

        Employee[] employees = {plain, manager, intern};

        for (Employee e : employees) {
            if (e instanceof ManagerEmployee m)
                System.out.println("Manager effective pay: Rs " +
                        m.effectiveSalary());

            else if (e instanceof InternEmployee i)
                System.out.println("Intern effective pay: Rs " +
                        i.effectiveSalary());

            else
                System.out.println("Plain employee pay: Rs " +
                        e.getSalary());
        }
    }
}
