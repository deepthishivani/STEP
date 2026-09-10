package main.java.programming_fundamentals.class_problems;

import java.util.*;

public class ReverseCustomerName {

    static String reverseCustomerName(String customerName) {

        char[] array =
                customerName.toCharArray();

        for (int i = 0, j = array.length - 1;
             i < j;
             i++, j--) {

            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return new String(array);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println(
                "Original Name: " + name
        );

        System.out.println(
                "Reversed Name: " +
                reverseCustomerName(name)
        );
    }
}
