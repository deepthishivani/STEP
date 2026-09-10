package main.java.string_handling.class_problems;

import java.util.Scanner;

public class CSVStudentRecordParser {
    static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Name: " + fields[0].trim() +
                " | Roll No: " + fields[1].trim() +
                " | Dept: " + fields[2].trim());
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        parseStudentRecord(s.nextLine());
    }
}
