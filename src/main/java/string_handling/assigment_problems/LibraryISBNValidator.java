package main.java.string_handling.assigment_problems;

import java.util.Scanner;

public class LibraryISBNValidator {
    static String normalizeCode(String raw) {
        String code = raw.trim();

        if (code.length() < 3)
            return code;

        return code.substring(0, 3).toUpperCase() + code.substring(3);
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(code.charAt(i)))
                return "Invalid: publisher code must be 3 letters";

        for (int i = 3; i < 13; i++)
            if (!Character.isDigit(code.charAt(i)))
                return "Invalid: body must contain only digits";

        String pub = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(pub)
              .append("] YEAR: ")
              .append(year)
              .append(" | CATALOG: ")
              .append(catalog);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String code = normalizeCode(s.nextLine());
        System.out.println(validateAndFormat(code));
    }
}
