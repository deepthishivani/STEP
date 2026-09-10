package main.java.oop.assigment_problems;

public class LibraryMemberDemo {

    static class BrokenLibraryMember {

        // Wrong: each member must have an independent name.
        static String name;

        // Wrong: every member needs a unique memberId.
        static String memberId;

        // Wrong: booksIssued varies from member to member.
        static int booksIssued;

        BrokenLibraryMember(String n, String id, int books) {
            name = n;
            memberId = id;
            booksIssued = books;
        }
    }

    static class LibraryMember {
        String name, memberId;
        int booksIssued;

        static String libraryName = "SRM Library";
        static int memberCount = 1000;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
            this.memberId = "LM-" + (++memberCount);
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " +
                    (memberCount - 1000));
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");

        BrokenLibraryMember a =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember b =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(a.name);
        System.out.println(b.name);

        System.out.println("\nFixed version:");

        LibraryMember m1 =
                new LibraryMember("Aditi", 2);

        LibraryMember m2 =
                new LibraryMember("Rohan", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}
