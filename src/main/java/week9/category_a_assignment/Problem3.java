package week9.category_a_assignment;

import java.util.HashMap;
import java.util.Map;

public class Problem3 {
    // Prefix sum frequencies: O(n) expected time, O(n) space.
    // Re-summing every subarray from scratch takes O(n³) time.
    public static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> frequencies = new HashMap<>();
        frequencies.put(0L, 1);

        long prefix = 0;
        long count = 0;

        for (int transaction : transactions) {
            prefix += transaction;
            count += frequencies.getOrDefault(prefix - k, 0);
            frequencies.put(prefix, frequencies.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPeriods(
                new int[]{3, 4, -7, 1, 3, 3, 1, -4}, 7));
        System.out.println(countPeriods(new int[]{1, 2, 3}, 10));
    }
}
