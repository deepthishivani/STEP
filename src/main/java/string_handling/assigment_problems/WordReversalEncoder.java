package main.java.string_handling.assigment_problems;

import java.util.Scanner;

public class WordReversalEncoder {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            result.append(new StringBuilder(words[i]).reverse());

            if (i < words.length - 1)
                result.append(" ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(reverseEachWord(s.nextLine()));
    }
}
