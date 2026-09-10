package main.java.string_handling.class_problems;

import java.util.Scanner;

public class FileExtensionValidator {
    static String validateFileExtension(String filename) {
        int pos = filename.lastIndexOf('.');

        if (pos == -1 || pos == filename.length() - 1)
            return "Rejected — invalid file type";

        String ext = filename.substring(pos + 1);

        if (ext.equalsIgnoreCase("pdf") ||
            ext.equalsIgnoreCase("docx") ||
            ext.equalsIgnoreCase("zip"))
            return "Accepted";

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(validateFileExtension(s.nextLine()));
    }
}
