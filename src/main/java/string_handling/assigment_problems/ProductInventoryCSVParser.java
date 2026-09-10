package main.java.string_handling.assigment_problems;

import java.util.Scanner;

public class ProductInventoryCSVParser {
    static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0].trim() +
                " | SKU: " + fields[1].trim() +
                " | Qty: " + fields[2].trim());
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        parseInventoryRecord(s.nextLine());
    }
}
