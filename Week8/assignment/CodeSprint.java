import java.util.*;

public class CodeSprint {
    interface Track {
        String name();
        double calculate(Score score);
    }

    static class Innovation implements Track {
        public String name() { return "Innovation"; }
        public double calculate(Score s) {
            return s.idea * .5 + s.execution * .3 + s.presentation * .2;
        }
    }

    static class Open implements Track {
        public String name() { return "Open"; }
        public double calculate(Score s) {
            return (s.idea + s.execution + s.presentation) / 3;
        }
    }

    static class Student {
        private final String id, name;
        Student(String id, String name) {
            this.id = Objects.requireNonNull(id);
            this.name = name;
        }
    }

    static class Judge {
        private final String name;
        Judge(String name) { this.name = name; }
    }

    static class Score {
        private final Judge judge;
        private final double idea, execution, presentation;
        Score(Judge judge, double idea, double execution, double presentation) {
            for (double value : new double[]{idea, execution, presentation})
                if (!Double.isFinite(value) || value < 0 || value > 10)
                    throw new IllegalArgumentException("Ratings must be from 0 to 10.");
            this.judge = judge;
            this.idea = idea;
            this.execution = execution;
            this.presentation = presentation;
        }
    }

    static class Project {
        private final String title;
        private Score score;
        private Project(String title) { this.title = title; }
    }

    static class Team {
        private final String name;
        private final List<Student> members;
        private final Track track;
        private Project project;
        private Team(String name, List<Student> members, Track track) {
            this.name = name;
            this.members = List.copyOf(members);
            this.track = track;
        }
    }

    enum State { OPEN, JUDGING, PUBLISHED }

    static class Hackathon {
        private State state = State.OPEN;
        private final Map<String, Team> teams = new LinkedHashMap<>();
        private final Set<String> registeredStudents = new HashSet<>();

        Team register(String name, List<Student> members, Track track) {
            if (state != State.OPEN)
                throw new IllegalStateException("Registration closed.");
            if (members.size() < 2 || members.size() > 4)
                throw new IllegalArgumentException(
                        "Registration failed: A team must have 2 to 4 members.");
            if (teams.containsKey(name))
                throw new IllegalArgumentException("Team name already registered.");

            Set<String> ids = new HashSet<>();
            for (Student student : members)
                if (!ids.add(student.id) || registeredStudents.contains(student.id))
                    throw new IllegalArgumentException(
                            "Student already belongs to a team or is repeated.");

            Team team = new Team(name, members, Objects.requireNonNull(track));
            teams.put(name, team);
            registeredStudents.addAll(ids);
            System.out.println("Team " + name + " registered ("
                    + members.size() + " members, " + track.name() + " track).");
            return team;
        }

        private void requireTeam(Team team) {
            if (team == null || teams.get(team.name) != team)
                throw new IllegalArgumentException("Unregistered team.");
        }

        void submit(Team team, String title) {
            requireTeam(team);
            if (state != State.OPEN || team.project != null)
                throw new IllegalStateException(
                        "Submission closed or project already submitted.");
            team.project = new Project(title);
            System.out.println("Project '" + title + "' submitted by " + team.name + ".");
        }

        void startJudging() {
            if (state != State.OPEN)
                throw new IllegalStateException("Cannot start judging.");
            state = State.JUDGING;
        }

        void score(Team team, Judge judge, double idea,
                   double execution, double presentation) {
            if (state == State.PUBLISHED)
                throw new IllegalStateException(
                        "Rescore rejected: Results have already been published.");
            requireTeam(team);
            if (state != State.JUDGING || team.project == null)
                throw new IllegalStateException("Judging requires a submitted project.");

            Score score = new Score(judge, idea, execution, presentation);
            team.project.score = score;
            System.out.printf(Locale.US,
                    "Score recorded for '%s'. Final score: %.2f.%n",
                    team.project.title, team.track.calculate(score));
        }

        void publish() {
            if (state != State.JUDGING)
                throw new IllegalStateException("Judging must start first.");
            for (Team team : teams.values())
                if (team.project != null && team.project.score == null)
                    throw new IllegalStateException("A submitted project is unscored.");
            state = State.PUBLISHED;
            System.out.println("Results published.");
        }
    }

    static void attempt(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();
        Team team = hackathon.register("ByteBusters", List.of(
                new Student("S1", "Asha"),
                new Student("S2", "Ravi"),
                new Student("S3", "Neha")), new Innovation());

        attempt(() -> hackathon.register("SoloCoder",
                List.of(new Student("S4", "Kiran")), new Open()));

        hackathon.submit(team, "SmartAttend");
        hackathon.startJudging();
        Judge judge = new Judge("Judge 1");
        hackathon.score(team, judge, 8, 7, 9);
        hackathon.publish();
        attempt(() -> hackathon.score(team, judge, 10, 7, 9));
    }
}
