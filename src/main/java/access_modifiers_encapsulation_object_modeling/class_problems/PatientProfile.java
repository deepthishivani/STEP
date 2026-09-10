package main.java.access_modifiers_encapsulation_object_modeling.class_problems;

public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private Integer lockerPinHash;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.name = name;
        setPatientId(patientId);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (patientId == null && id != null && !id.trim().isEmpty())
            patientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}"))
            lockerPinHash = pin.hashCode();
    }

    public static void main(String[] args) {
        System.out.println(
            new PatientProfile("Arjun Iyer").getPatientId());

        System.out.println(
            new PatientProfile("MT2026-0142","Arjun Iyer")
                .getPatientId());

        PatientProfile p = new PatientProfile();
        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");

        System.out.println(p.getPatientId());
    }
}
