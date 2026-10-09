package week9.category_a;

public class Problem4 {
    // Problem 4 repeats Problem 3 in the supplied questions.
    // It uses the same O(log n) time, O(1) space binary search.
    public static String findBook(String[][] catalog, String targetIsbn) {
        return Problem3.findBook(catalog, targetIsbn);
    }

    public static void main(String[] args) {
        String[][] catalog = {
            {"0001112223", "Introduction to Algebra"},
            {"0002223334", "Beginning Python"},
            {"0003334445", "Classic Mythology"},
            {"0004445556", "Data and Society"},
            {"0005556667", "European History"}
        };

        System.out.println(findBook(catalog, "0003334445"));
        System.out.println(findBook(catalog, "0009998887"));
    }
}
