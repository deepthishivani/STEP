package main.java.string_handling.class_problems;

import java.util.Scanner;

public class BankTransactionReference {
    static String normalizeReference(String raw) {
        String ref = raw.trim();

        if (ref.length() < 3)
            return ref;

        return ref.substring(0, 3).toUpperCase() + ref.substring(3);
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(reference.charAt(i)))
                return "Invalid: bank code must be 3 letters";

        for (int i = 3; i < 14; i++)
            if (!Character.isDigit(reference.charAt(i)))
                return "Invalid: body must contain only digits";

        String bank = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String seq = reference.substring(9);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(bank)
              .append("] DATE: ")
              .append(date.substring(0, 2))
              .append("/")
              .append(date.substring(2, 4))
              .append("/")
              .append(date.substring(4, 6))
              .append(" | SEQ: ")
              .append(seq);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String reference = normalizeReference(s.nextLine());

        System.out.println(validateAndFormat(reference));
    }
}
