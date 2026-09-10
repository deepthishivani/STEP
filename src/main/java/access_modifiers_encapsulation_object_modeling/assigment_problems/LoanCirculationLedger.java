package main.java.access_modifiers_encapsulation_object_modeling.assigment_problems;

public class LoanCirculationLedger {

    static class LoanReceipt {
        private final String memberId;
        private final String[] bookIds;
        static final String LIBRARY;

        static {
            LIBRARY = "PageTurner Library";
        }

        public LoanReceipt(String memberId, String[] bookIds) {
            if (memberId == null || bookIds == null)
                throw new IllegalArgumentException();

            for (String id : bookIds)
                if (id == null || !id.matches("BK-\\d{3}"))
                    throw new IllegalArgumentException();

            this.memberId = memberId;
            this.bookIds = bookIds.clone();
        }

        String[] getBookIds() {
            return bookIds.clone();
        }

        LoanReceipt withCorrectedBookId(int index,
                                        String newId) {
            if (index < 0 || index >= bookIds.length ||
                newId == null ||
                !newId.matches("BK-\\d{3}"))
                throw new IllegalArgumentException();

            String[] copy = bookIds.clone();
            copy[index] = newId;

            return new LoanReceipt(memberId, copy);
        }
    }

    static class ReferenceOnlyLoanReceipt
            extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0, skipped = 0;
        int reference = 0, regular = 0;

        for (LoanReceipt r : receipts) {
            if (r == null) {
                skipped++;
                continue;
            }

            processed++;

            if (r instanceof ReferenceOnlyLoanReceipt)
                reference++;
            else
                regular++;
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               reference + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100","bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r =
            new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100","BK-101"});

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"),
            null,
            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"})
        };

        System.out.println(
            processNightlyCirculation(receipts));
    }
}
