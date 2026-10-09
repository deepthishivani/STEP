package week9.category_a;

public class Problem3 {
    // Each record is {ISBN, title}; records are sorted by ISBN.
    // Binary search: O(log n) time, O(1) extra space.
    // A linear search would take O(n) time per query.
    public static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0, right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = catalog[mid][0].compareTo(targetIsbn);

            if (comparison == 0) return catalog[mid][1];
            if (comparison < 0) left = mid + 1;
            else right = mid - 1;
        }
        return "Not Found";
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
