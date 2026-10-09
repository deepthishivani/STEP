package abstraction_interface.category_a_practice;

abstract class StaffMember {
    private double baseSalary;
    protected final double bonusRate;

    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    public StaffMember(double baseSalary, double bonusRate) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Negative salary");
        }
        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {
        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        }
    }

    public abstract double calculateBonus();
}

interface Auditable {
    String auditRecord();
}

class TeamLead extends StaffMember implements Auditable {
    private final int teamSize;

    public TeamLead(double baseSalary, int teamSize) {
        super(baseSalary);
        this.teamSize = teamSize;
    }

    public TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize
                + " team members, salary $" + getSalary();
    }
}

public class Problem3 {
    static String getAuditIfApplicable(StaffMember member) {
        if (member instanceof Auditable) {
            return ((Auditable) member).auditRecord();
        }
        return "No audit required";
    }

    public static void main(String[] args) {
        TeamLead lead = new TeamLead(60000, 5);
        TeamLead lead2 = new TeamLead(60000, 0.20, 5);

        System.out.println(lead.calculateBonus());
        System.out.println(lead2.calculateBonus());

        lead.setSalary(-5000);
        System.out.println(lead.getSalary() == 60000
                ? "rejected, salary unchanged" : lead.getSalary());

        StaffMember ref = lead;
        System.out.println(getAuditIfApplicable(ref));
    }
}
