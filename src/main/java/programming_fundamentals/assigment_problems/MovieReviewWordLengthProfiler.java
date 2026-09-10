package main.java.programming_fundamentals.assigment_problems;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {
    static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");
        int shortCount = 0, mediumCount = 0, longCount = 0;

        for (String word : words) {
            int len = word.length();

            if (len <= 4)
                shortCount++;
            else if (len <= 8)
                mediumCount++;
            else
                longCount++;
        }

        System.out.println("Short: " + shortCount +
                " | Medium: " + mediumCount +
                " | Long: " + longCount);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String review = s.nextLine();
        classifyWordLengths(review);
    }
}
