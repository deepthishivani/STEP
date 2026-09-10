package main.java.access_modifiers_encapsulation_object_modeling.class_problems;

import java.util.Arrays;

public class DischargeLedger {

    static class DischargeSummary {
        private final String patientId;
        private final String[] medicationCodes;
        static final String FACILITY;

        static {
            FACILITY = "MediTrack";
        }

        public DischargeSummary(String patientId,
                                String[] medicationCodes) {
            if (patientId == null || medicationCodes == null)
                throw new IllegalArgumentException();

            for (String x : medicationCodes)
                if (x == null || !x.matches("MED-[A-Z]"))
                    throw new IllegalArgumentException();

            this.patientId = patientId;
            this.medicationCodes = medicationCodes.clone();
        }

        String[] getMedicationCodes() {
            return medicationCodes.clone();
        }

        DischargeSummary withCorrectedMedication(int index,
                                                  String newCode) {
            if (index < 0 || index >= medicationCodes.length ||
                newCode == null || !newCode.matches("MED-[A-Z]"))
                throw new IllegalArgumentException();

            String[] copy = medicationCodes.clone();
            copy[index] = newCode;

            return new DischargeSummary(patientId, copy);
        }
    }

    static class CriticalCareDischargeSummary
            extends DischargeSummary {

        private final int icuDays;

        public CriticalCareDischargeSummary(
                String patientId,
                String[] medicationCodes,
                int icuDays) {

            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0, skipped = 0;
        int critical = 0, routine = 0;

        for (DischargeSummary d : summaries) {
            if (d == null) {
                skipped++;
                continue;
            }

            processed++;

            if (d instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               critical + " critical-care | " +
               routine + " routine";
    }

    public static void main(String[] args) {
        try {
            new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A","bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d =
            new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A","MED-B"});

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] a = {
            new CriticalCareDischargeSummary(
                "MT001",new String[]{"MED-X"},4),
            null,
            new DischargeSummary(
                "MT002",new String[]{"MED-Y"})
        };

        System.out.println(processNightlyBatch(a));
    }
}
