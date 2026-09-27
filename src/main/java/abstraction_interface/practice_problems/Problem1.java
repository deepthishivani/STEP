package abstraction_interface.practice_problems;

import java.util.*;

public class Problem1 {
    interface Question {
        boolean correct(String answer);
    }

    static class MCQ implements Question {
        private final String text, key;
        MCQ(String text, String key) {
            this.text = text;
            this.key = key;
        }
        public boolean correct(String answer) {
            return key.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalse implements Question {
        private final boolean key;
        TrueFalse(boolean key) { this.key = key; }
        public boolean correct(String answer) {
            return Boolean.toString(key).equalsIgnoreCase(answer);
        }
    }

    static class Student {
        private final String id, name;
        Student(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Examination {
        private final String title;
        private final List<Question> questions;
        private final Map<String, Attempt> attempts = new HashMap<>();

        Examination(String title, List<Question> questions) {
            if (questions.isEmpty())
                throw new IllegalArgumentException("Questions required.");
            this.title = title;
            this.questions = List.copyOf(questions);
        }

        Attempt start(Student student) {
            Attempt old = attempts.get(student.id);
            if (old != null) {
                if (old.submitted)
                    throw new IllegalStateException("Already submitted.");
                return old;
            }
            Attempt attempt = new Attempt(student, this);
            attempts.put(student.id, attempt);
            System.out.println("Examination '" + title
                    + "' started by " + student.name + ".");
            return attempt;
        }
    }

    static class Attempt {
        private final Student student;
        private final Examination exam;
        private final Map<Integer, String> answers = new HashMap<>();
        private boolean submitted;
        private int score;

        private Attempt(Student student, Examination exam) {
            this.student = student;
            this.exam = exam;
        }

        void answer(int number, String answer) {
            if (submitted)
                throw new IllegalStateException("Submitted answers are locked.");
            if (number < 1 || number > exam.questions.size())
                throw new IllegalArgumentException("Invalid question.");
            answers.put(number, Objects.requireNonNull(answer).trim());
            System.out.println("Question " + number
                    + " answered with '" + answer + "'.");
        }

        void submit() {
            if (submitted)
                throw new IllegalStateException("Already submitted.");
            submitted = true;
            for (int i = 0; i < exam.questions.size(); i++)
                if (exam.questions.get(i).correct(answers.get(i + 1)))
                    score++;
            System.out.println("Examination '" + exam.title
                    + "' submitted successfully.");
            System.out.println("Result: " + score + "/"
                    + exam.questions.size() + " correct.");
        }
    }

    public static void main(String[] args) {
        Examination exam = new Examination("Math Quiz", List.of(
                new MCQ("2 + 2? A:4 B:5 C:6", "A"),
                new MCQ("3 * 3? A:6 B:9 C:12", "B")));
        Student student = new Student("S1", "Student");
        Attempt attempt = exam.start(student);
        attempt.answer(1, "A");
        attempt.answer(2, "C");
        attempt.submit();

        try {
            attempt.answer(1, "B");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
