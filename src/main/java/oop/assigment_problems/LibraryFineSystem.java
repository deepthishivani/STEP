package main.java.oop.assigment_problems;

public class LibraryFineSystem {

    static class BookIssue {
        String title, borrowerName;
        int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        double fineAmount() {
            return daysOverdue > 0 ? daysOverdue * 5.0 : 0;
        }

        boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        // Static because it works on the whole array of issues.
        // fineAmount is instance-based because it depends on one book issue.
        static double totalFineCollected(BookIssue[] issues) {
            double total = 0;
            for (BookIssue b : issues)
                total += b.fineAmount();
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "A", 18),
            new BookIssue("Effective Java", "B", 5),
            new BookIssue("Refactoring", "C", 0),
            new BookIssue("DSA Handbook", "D", 21),
            new BookIssue("Design Patterns", "E", 9)
        };

        for (BookIssue b : issues)
            System.out.println(b.title + " - " + b.daysOverdue +
                    " days - " +
                    (b.isSeverelyOverdue() ? "Severely overdue" : "OK"));

        System.out.println("Total fine collected: Rs " +
                BookIssue.totalFineCollected(issues));
    }
}
