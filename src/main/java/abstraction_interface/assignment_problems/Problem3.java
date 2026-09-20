package abstraction_interface.assignment_problems;

abstract class ServiceableVehicle {
    private double mileage;

    public abstract String performMaintenance();

    double getMileage() {
        return mileage;
    }

    void addMileage(double km) {
        if (km >= 0)
            mileage += km;
    }
}

interface Insurable {
    String getInsuranceInfo();
}

class Forklift extends ServiceableVehicle implements Insurable {
    protected String assetTag;

    public Forklift(String assetTag) {
        this.assetTag = assetTag;
    }

    public String performMaintenance() {
        return "Forklift " + assetTag +
                ": hydraulic and fork inspection complete";
    }

    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}

class HeavyDutyForklift extends Forklift {
    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    public String performMaintenance() {
        return super.performMaintenance() +
                " | high-pressure hydraulic check complete";
    }
}

public class Problem3 {
    static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable)
            return ((Insurable) v).getInsuranceInfo();

        return "No insurance record exists";
    }

    public static void main(String[] args) {
        Forklift f = new Forklift("FL-22");
        f.addMileage(120);

        System.out.println(f.getMileage());
        System.out.println(f.performMaintenance());

        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");
        System.out.println(hd.performMaintenance());

        System.out.println(getInsuranceIfApplicable(f));
    }
}
