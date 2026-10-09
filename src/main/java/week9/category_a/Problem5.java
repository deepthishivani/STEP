package week9.category_a;

public class Problem5 {
    // Recomputing every window: O((n-k+1) × k) time.
    // Sliding window: O(n) time, O(1) extra space.
    public static long maxSumSubarray(int[] sales, int k) {
        if (sales == null || k < 1 || k > sales.length) {
            throw new IllegalArgumentException("Require 1 <= k <= sales.length");
        }

        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        long maximum = windowSum;
        for (int i = k; i < sales.length; i++) {
            windowSum += (long) sales[i] - sales[i - k];
            maximum = Math.max(maximum, windowSum);
        }
        return maximum;
    }

    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};
        System.out.println(maxSumSubarray(sales, 3));
    }
}
