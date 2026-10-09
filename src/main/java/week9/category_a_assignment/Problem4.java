package week9.category_a_assignment;

public class Problem4 {
    // Two binary searches: O(log n) time, O(1) space per query.
    // A linear scan takes O(n) time.
    public static int countInBand(int[] scores, int low, int high) {
        return firstGreater(scores, high) - firstAtLeast(scores, low);
    }

    private static int firstAtLeast(int[] scores, int value) {
        int left = 0, right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] < value) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private static int firstGreater(int[] scores, int value) {
        int left = 0, right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] <= value) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}
