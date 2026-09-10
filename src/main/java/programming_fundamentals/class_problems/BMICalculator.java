package main.java.programming_fundamentals.class_problems;

import java.util.*;

public class BMICalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }

    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println("\nPerson\tHeight(m)\tWeight(kg)\tBMI\tStatus");

        for (int i = 0; i < heights.length; i++) {

            double bmi =
                    weights[i] / (heights[i] * heights[i]);

            System.out.printf(
                    "%d\t%.2f\t\t%.2f\t\t%.2f\t%s%n",
                    i + 1,
                    heights[i],
                    weights[i],
                    bmi,
                    getBmiStatus(bmi)
            );
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of people: ");
        int n = sc.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Person " + (i + 1) + " height (m): "
            );

            heights[i] = sc.nextDouble();

            System.out.print(
                    "Person " + (i + 1) + " weight (kg): "
            );

            weights[i] = sc.nextDouble();
        }

        printWellnessReport(heights, weights);
    }
}
