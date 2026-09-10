package main.java.oop.class_problems;

public class FeeHostelMiniSystem {

    static class FeeAccount {
        private double totalFee, amountPaid;

        FeeAccount(double totalFee) {
            this.totalFee = totalFee;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Payment rejected");
                return;
            }
            amountPaid += amount;
        }

        double getDue() {
            return Math.max(0, totalFee - amountPaid);
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(double totalFee) {
            super(totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class HostelRoom {
        String roomNo;
        int beds, occupied;

        HostelRoom(String roomNo, int beds) {
            this.roomNo = roomNo;
            this.beds = beds;
        }

        void allot() {
            if (occupied < beds)
                occupied++;
        }
    }

    static class SrmStudent {
        String name, regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        SrmStudent(String name, String regNo,
                   HostelFeeAccount feeAccount) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            totalStudents++;
        }

        String fullStatus() {
            return name + " | Due: Rs " + feeAccount.getDue() +
                    " | Room: " +
                    (room == null ? "unallotted" : room.roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom r : rooms)
            if (r != null && r.occupied < r.beds)
                return r;
        return null;
    }

    static void safeAllot(SrmStudent s, HostelRoom[] rooms) {
        HostelRoom r = findAvailableRoom(rooms);

        if (r != null) {
            r.allot();
            s.room = r;
        }
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1),
            new HostelRoom("C-507", 1)
        };

        SrmStudent ravi = new SrmStudent(
                "Ravi", "RA1", new HostelFeeAccount(200000));

        SrmStudent anitha = new SrmStudent(
                "Anitha", "RA2", new HostelFeeAccount(200000));

        SrmStudent karthik = new SrmStudent(
                "Karthik", "RA3", new HostelFeeAccount(200000));

        safeAllot(ravi, rooms);
        safeAllot(anitha, rooms);

        ravi.feeAccount.payInTwoInstallments(60000);
        anitha.feeAccount.pay(20000);
        karthik.feeAccount.pay(-1000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println("Total students: " +
                SrmStudent.totalStudents);
    }
}
