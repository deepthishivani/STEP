package main.java.programming_fundamentals.assigment_problems;

import java.util.Scanner;

public class TypingSpeedAccuracyChecker {
    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0, firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i))
                matched++;
            else if (firstMismatch == -1)
                firstMismatch = i;
        }

        double accuracy = (matched * 100.0) / original.length();

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, original.length(), accuracy);

        if (firstMismatch == -1)
            System.out.println(" | No Mismatches");
        else
            System.out.println(" | First Mismatch at position " + (firstMismatch + 1) +
                    " ('" + original.charAt(firstMismatch) +
                    "' vs '" + typed.charAt(firstMismatch) + "')");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String original = s.nextLine();
        String typed = s.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Strings must be of equal length");
            return;
        }

        checkTypingAccuracy(original, typed);
    }
}
