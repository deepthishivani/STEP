package abstraction_interface.assignment_problems;

import java.util.*;

public class Problem3 {
    interface Capability {
        String key();
        void apply(String device, int value);
    }

    static class Power implements Capability {
        private boolean on;
        public String key() { return "Power"; }
        public void apply(String device, int value) {
            if (value != 0 && value != 1)
                throw new IllegalArgumentException("Power must be 0 or 1.");
            on = value == 1;
            System.out.println(device + ": " + (on ? "ON" : "OFF") + ".");
        }
    }

    static class Brightness implements Capability {
        private int value;
        public String key() { return "Brightness"; }
        public void apply(String device, int value) {
            if (value < 0 || value > 100)
                throw new IllegalArgumentException("Rejected: " + device
                        + " brightness must be between 0% and 100%.");
            this.value = value;
            System.out.println(device + ": brightness set to " + value + "%.");
        }
    }

    static class Temperature implements Capability {
        private int value = 24;
        public String key() { return "Temperature"; }
        public void apply(String device, int value) {
            if (value < 16 || value > 30)
                throw new IllegalArgumentException("Rejected: " + device
                        + " temperature must be between 16 C and 30 C.");
            this.value = value;
            System.out.println(device + ": temperature set to " + value + " C.");
        }
    }

    static class Device {
        private final String name;
        private final Map<String, Capability> capabilities = new LinkedHashMap<>();

        Device(String name, Capability... initial) {
            this.name = name;
            for (Capability capability : initial)
                add(capability);
        }

        void add(Capability capability) {
            if (capabilities.putIfAbsent(capability.key(), capability) != null)
                throw new IllegalArgumentException("Capability already exists.");
        }

        boolean apply(String key, int value) {
            Capability capability = capabilities.get(key);
            if (capability == null)
                return false;
            capability.apply(name, value);
            return true;
        }
    }

    static class Step {
        private final String capability;
        private final int value;
        Step(String capability, int value) {
            this.capability = capability;
            this.value = value;
        }
    }

    static class Scene {
        private final String name;
        private final List<Step> steps;

        Scene(String name, List<Step> steps) {
            this.name = name;
            this.steps = List.copyOf(steps);
        }

        void run(List<Device> devices) {
            int count = 0;
            System.out.println("Scene '" + name + "' started.");

            for (Step step : steps)
                for (Device device : devices)
                    try {
                        if (device.apply(step.capability, step.value))
                            count++;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

            System.out.println("Scene '" + name + "' completed: "
                    + count + " actions applied.");
        }
    }

    public static void main(String[] args) {
        Device ac = new Device("Lab AC", new Power(), new Temperature());
        Device lights = new Device("Ceiling Lights", new Power(), new Brightness());
        Device projector = new Device("Projector", new Power());

        Scene lecture = new Scene("Lecture Mode", List.of(
                new Step("Power", 1),
                new Step("Brightness", 40),
                new Step("Temperature", 24)));

        lecture.run(List.of(ac, lights, projector));

        try {
            ac.apply("Temperature", 12);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        projector.add(new Brightness());
        System.out.println("Projector: Brightness capability added.");
        projector.apply("Brightness", 70);
    }
}
