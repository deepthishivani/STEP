package main.java.oop.class_problems;

public class StaticBoundaryDemo {

    static class BrokenStudent {
        // Wrong: name belongs to each individual student.
        static String name;

        // Wrong: regNo must uniquely identify each student.
        static String regNo;

        // Wrong: attendance differs for every student.
        static int attendance;

        BrokenStudent(String n, String r, int a) {
            name = n;
            regNo = r;
            attendance = a;
        }
    }

    static class SrmStudent {
        String name, regNo;
        int attendance;

        static String university = "SRMIST";
        static int admissionCount = 10;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            this.regNo = "RA2311003010" + (++admissionCount);
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " +
                    (admissionCount - 10));
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");

        BrokenStudent a =
                new BrokenStudent("Ravi", "RA231100301011", 82);
        BrokenStudent b =
                new BrokenStudent("Meera", "RA231100301012", 90);

        System.out.println(a.name);
        System.out.println(b.name);

        System.out.println("\nFixed version:");

        SrmStudent s1 = new SrmStudent("Ravi", 82);
        SrmStudent s2 = new SrmStudent("Meera", 90);

        s1.printIdCard();
        s2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}
