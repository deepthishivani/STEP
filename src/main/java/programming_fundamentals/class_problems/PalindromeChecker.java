package main.java.programming_fundamentals.class_problems;

import java.util.*;

public class PalindromeChecker {

    static boolean isPalindromeIterative(String text) {
        int i = 0;
        int j = text.length() - 1;

        while (i < j) {
            if (text.charAt(i) != text.charAt(j))
                return false;

            i++;
            j--;
        }

        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        return recursiveCheck(text, 0, text.length() - 1);
    }

    static boolean recursiveCheck(String text, int i, int j) {
        if (i >= j)
            return true;

        if (text.charAt(i) != text.charAt(j))
            return false;

        return recursiveCheck(text, i + 1, j - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] array = text.toCharArray();

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return text.equals(new String(array));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine()
                .replaceAll("[^A-Za-z0-9]", "")
                .toLowerCase();

        System.out.println(
                "Iterative: " +
                (isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome")
        );

        System.out.println(
                "Recursive: " +
                (isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome")
        );

        System.out.println(
                "Array Reversal: " +
                (isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome")
        );
    }
}
