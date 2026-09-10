package main.java.access_modifiers_encapsulation_object_modeling.class_problems;

import java.util.Arrays;

public class PatientVitals {
    private double[] readings = new double[500];
    private int count;

    PatientVitals(double[] initialReadings) {
        if (initialReadings != null)
            for (double x : initialReadings)
                recordReading(x);
    }

    void recordReading(double reading) {
        if (reading > 0 && reading <= 45 && count < readings.length)
            readings[count++] = reading;
    }

    double getAverage() {
        if (count == 0) return 0;

        double sum = 0;
        for (int i = 0; i < count; i++)
            sum += readings[i];

        return sum / count;
    }

    double[] getAllReadings() {
        return Arrays.copyOf(readings, count);
    }

    public static void main(String[] args) {
        PatientVitals v =
            new PatientVitals(new double[]{36.5,-2,37.1});

        System.out.println(Arrays.toString(v.getAllReadings()));

        double[] copy = v.getAllReadings();
        copy[0] = 999;

        System.out.println(v.getAllReadings()[0]);
    }
}
