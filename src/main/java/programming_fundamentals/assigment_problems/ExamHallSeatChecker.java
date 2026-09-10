package main.java.programming_fundamentals.assigment_problems;

import java.util.Scanner;

public class ExamHallSeatChecker {
    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean found = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            boolean alreadyPrinted = false;

            for (int k = 0; k < i; k++)
                if (seatNumbers[k] == seatNumbers[i])
                    alreadyPrinted = true;

            if (alreadyPrinted) continue;

            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    found = true;
                    break;
                }
            }
        }

        if (!found)
            System.out.println("No Duplicate Seats Found");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int[] seats = new int[n];

        for (int i = 0; i < n; i++)
            seats[i] = s.nextInt();

        checkDuplicateSeats(seats);
    }
}
