package main.java.string_handling.assigment_problems;

import java.util.*;

public class StopWordFrequencyReport {
    static void printFilteredWordFrequency(String feedback) {
        String text = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "");

        String[] words = text.split("\\s+");

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            boolean stop = false;

            for (String s : stopWords)
                if (word.equals(s))
                    stop = true;

            if (!stop && !word.isEmpty())
                map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> e : list)
            System.out.println(e.getKey() + ": " + e.getValue());
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StringBuilder input = new StringBuilder();

        while (s.hasNextLine()) {
            if (input.length() > 0)
                input.append(" ");
            input.append(s.nextLine());
        }

        printFilteredWordFrequency(input.toString());
    }
}
