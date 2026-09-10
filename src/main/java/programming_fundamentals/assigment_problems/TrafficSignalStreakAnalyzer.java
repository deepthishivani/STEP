package main.java.programming_fundamentals.assigment_problems;

import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {
    static void findLongestStreak(String signalLog) {
        char longest = signalLog.charAt(0);
        int max = 1, count = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1))
                count++;
            else
                count = 1;

            if (count > max) {
                max = count;
                longest = signalLog.charAt(i);
            }
        }

        System.out.println("Longest Streak: '" + longest + "' repeated " + max + " times");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String signalLog = s.nextLine();
        findLongestStreak(signalLog);
    }
}
