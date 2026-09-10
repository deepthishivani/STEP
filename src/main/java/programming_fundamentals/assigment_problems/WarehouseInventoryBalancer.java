package main.java.programming_fundamentals.assigment_problems;

import java.util.Scanner;

public class WarehouseInventoryBalancer {
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int sumA = 0, sumB = 0;
        int max = sectionA[0], section = 1, index = 0;

        for (int i = 0; i < sectionA.length; i++) {
            sumA += sectionA[i];
            sumB += sectionB[i];

            if (sectionA[i] > max) {
                max = sectionA[i];
                section = 1;
                index = i;
            }

            if (sectionB[i] > max) {
                max = sectionB[i];
                section = 2;
                index = i;
            }
        }

        System.out.println("Section A Total: " + sumA);
        System.out.println("Section B Total: " + sumB);
        System.out.println("Status: " + (sumA == sumB ? "Balanced" : "Not Balanced"));
        System.out.println("Highest Quantity: " + max +
                " (Section " + (section == 1 ? "A" : "B") +
                ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++)
            a[i] = s.nextInt();

        for (int i = 0; i < n; i++)
            b[i] = s.nextInt();

        analyzeInventory(a, b);
    }
}
