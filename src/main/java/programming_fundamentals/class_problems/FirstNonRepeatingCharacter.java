package main.java.programming_fundamentals.class_problems;

import java.util.*;

public class FirstNonRepeatingCharacter {

    static Character findFirstNonRepeatingChar(String text) {

        HashMap<Character, Integer> frequency =
                new HashMap<>();

        for (char c : text.toCharArray()) {
            frequency.put(
                    c,
                    frequency.getOrDefault(c, 0) + 1
            );
        }

        for (char c : text.toCharArray()) {
            if (frequency.get(c) == 1)
                return c;
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        Character result =
                findFirstNonRepeatingChar(text);

        if (result != null)
            System.out.println(
                    "First Non-Repeating Character: '" +
                    result + "'"
            );
        else
            System.out.println(
                    "No Non-Repeating Character Found"
            );
    }
}
