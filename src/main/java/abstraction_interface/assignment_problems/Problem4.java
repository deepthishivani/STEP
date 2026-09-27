package abstraction_interface.assignment_problems;

import java.util.*;

public class Problem4 {
    interface CreditPolicy {
        int limit();
        String name();
    }

    static class Regular implements CreditPolicy {
        public int limit() { return 24; }
        public String name() { return "Regular"; }
    }
    static class Honors implements CreditPolicy {
        public int limit() { return 28; }
        public String name() { return "Honors"; }
    }
    static class Exchange implements CreditPolicy {
        public int limit() { return 20; }
        public String name() { return "Exchange"; }
    }

    static class Student {
        private final String id, name;
        private final CreditPolicy policy;
        private int credits;

        private Student(String id, String name, CreditPolicy policy, int credits) {
            if (credits < 0 || credits > policy.limit())
                throw new IllegalArgumentException("Invalid initial credits.");
            this.id = id;
            this.name = name;
            this.policy = policy;
            this.credits = credits;
        }

        boolean eligible(Elective elective) {
            return (long) credits + elective.credits <= policy.limit();
        }

        String creditText() {
            return " (credits: " + credits + "/" + policy.limit() + ").";
        }
    }

    static class Elective {
        private final String name;
        private final int credits, capacity;
        private final Set<Student> enrolled = new LinkedHashSet<>();
        private final Deque<Student> waiting = new ArrayDeque<>();

        private Elective(String name, int credits, int capacity) {
            if (credits <= 0 || capacity <= 0)
                throw new IllegalArgumentException("Credits and capacity must be positive.");
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }
    }

    static class EnrollmentService {
        private final Map<String, Student> students = new HashMap<>();
        private final Set<Elective> electives = new HashSet<>();

        synchronized Student student(String id, String name,
                                     CreditPolicy policy, int credits) {
            if (students.containsKey(id))
                throw new IllegalArgumentException("Student ID already registered.");
            Student student = new Student(id, name, policy, credits);
            students.put(id, student);
            return student;
        }

        synchronized Elective elective(String name, int credits, int capacity) {
            Elective elective = new Elective(name, credits, capacity);
            electives.add(elective);
            return elective;
        }

        private void validate(Student student, Elective elective) {
            if (students.get(student.id) != student || !electives.contains(elective))
                throw new IllegalArgumentException("Unknown student or elective.");
        }

        synchronized void enroll(Student student, Elective elective) {
            validate(student, elective);

            if (elective.enrolled.contains(student) || elective.waiting.contains(student))
                throw new IllegalStateException("Duplicate enrollment or waitlist entry.");

            if (!student.eligible(elective)) {
                System.out.println("Enrollment failed: " + student.name
                        + " would exceed the " + student.policy.name()
                        + " credit limit (" + ((long) student.credits + elective.credits)
                        + "/" + student.policy.limit() + ").");
                return;
            }

            if (elective.enrolled.size() < elective.capacity) {
                elective.enrolled.add(student);
                student.credits += elective.credits;
                System.out.println(student.name + " enrolled in "
                        + elective.name + student.creditText());
            } else {
                elective.waiting.addLast(student);
                System.out.println(elective.name + " is full. " + student.name
                        + " added to waitlist (position " + elective.waiting.size() + ").");
            }
        }

        synchronized void drop(Student student, Elective elective) {
            validate(student, elective);

            if (!elective.enrolled.remove(student)) {
                if (elective.waiting.remove(student)) {
                    System.out.println(student.name + " removed from waitlist.");
                    return;
                }
                throw new IllegalStateException("Student is neither enrolled nor waiting.");
            }

            student.credits -= elective.credits;
            System.out.println(student.name + " dropped "
                    + elective.name + student.creditText());

            Iterator<Student> iterator = elective.waiting.iterator();
            while (iterator.hasNext() && elective.enrolled.size() < elective.capacity) {
                Student next = iterator.next();
                if (!next.eligible(elective))
                    continue;
                iterator.remove();
                elective.enrolled.add(next);
                next.credits += elective.credits;
                System.out.println(next.name
                        + " promoted from waitlist and enrolled in "
                        + elective.name + next.creditText());
            }
        }
    }

    public static void main(String[] args) {
        EnrollmentService service = new EnrollmentService();
        Elective cloud = service.elective("Cloud Computing", 4, 2);

        Student asha = service.student("S1", "Asha", new Regular(), 20);
        Student ravi = service.student("S2", "Ravi", new Honors(), 22);
        Student neha = service.student("S3", "Neha", new Exchange(), 12);
        Student kiran = service.student("S4", "Kiran", new Regular(), 22);

        service.enroll(asha, cloud);
        service.enroll(ravi, cloud);
        service.enroll(neha, cloud);
        service.enroll(kiran, cloud);
        service.drop(asha, cloud);
    }
}
