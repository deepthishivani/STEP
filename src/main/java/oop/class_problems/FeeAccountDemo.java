package main.java.oop.class_problems;

public class FeeAccountDemo {

    static class FeeAccount {
        private String regNo;
        private double totalFee, amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
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
        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee,
                              double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() {
            return getDue() * (1 - scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA1", 150000, 0);
        HostelFeeAccount hostel =
                new HostelFeeAccount("RA2", 200000, 0);
        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA3", 180000, 0, 20);

        plain.pay(150000);
        hostel.payInTwoInstallments(60000);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount a : accounts) {
            if (a instanceof ScholarshipFeeAccount s)
                System.out.println("Scholarship account effective due: Rs " +
                        s.effectiveDue());
            else if (a instanceof HostelFeeAccount)
                System.out.println("Hostel account due: Rs " + a.getDue());
            else
                System.out.println("Plain account due: Rs " + a.getDue());
        }
    }
}
