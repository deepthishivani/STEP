package abstraction_interface.category_a_practice;

interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    protected final String zoneName;

    public SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {
    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + zoneName + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private final String secondZoneName;

    public DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message) + " [also covering " + secondZoneName + "]";
    }
}

class SmokeDetector implements Alertable {
    private final String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

public class Problem2 {
    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable device) {
        if (device instanceof MotionSensor) {
            return ((MotionSensor) device).getZoneName();
        }
        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        Alertable motion = new MotionSensor("Living Room");
        Alertable dual = new DualZoneMotionSensor("Hallway", "Stairwell");
        Alertable smoke = new SmokeDetector("SD-01");

        broadcastAll(new Alertable[]{motion, dual, smoke}, "Motion detected");
        System.out.println(getZoneIfMotionSensor(motion));
        System.out.println(getZoneIfMotionSensor(smoke));
    }
}
