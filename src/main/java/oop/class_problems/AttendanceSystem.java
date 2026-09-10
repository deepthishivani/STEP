package main.java.oop.class_problems;

public class AttendanceSystem {

    static class SrmStudent {
        String name, regNo;
        int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        void addAttendanceUpdate(int newAttendance) {
            attendance = newAttendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        // classAverage is static because it works on many students;
        // isEligible is not static because it depends on one student's attendance.
        static double classAverage(SrmStudent[] students) {
            double sum = 0;
            for (SrmStudent s : students)
                sum += s.attendance;
            return sum / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] s = {
            new SrmStudent("Ravi", "RA1", 82),
            new SrmStudent("Anitha", "RA2", 68),
            new SrmStudent("Karthik", "RA3", 91),
            new SrmStudent("Meera", "RA4", 74),
            new SrmStudent("Suresh", "RA5", 60)
        };

        for (SrmStudent x : s)
            System.out.println(x.name + " - " + x.attendance + "% - " +
                    (x.isEligible() ? "Eligible" : "Detained"));

        System.out.printf("Class average: %.1f%%%n",
                SrmStudent.classAverage(s));
    }
}
