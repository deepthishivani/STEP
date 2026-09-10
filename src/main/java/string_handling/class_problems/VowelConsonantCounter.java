package main.java.string_handling.class_problems;

import java.util.Scanner;

public class VowelConsonantCounter {
    static void countVowelsAndConsonants(String text) {
        int vowels = 0, consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));

            if (c == ' ')
                continue;

            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                vowels++;
            else
                consonants++;
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        countVowelsAndConsonants(s.nextLine());
    }
}
