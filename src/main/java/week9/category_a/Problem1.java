package week9.category_a;

import java.util.Arrays;

public class Problem1 {
    // Two pointers: O(n) time, O(1) extra space.
    // Checking every pair would take O(n²) time.
    public static int[] pairSumSorted(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            long sum = (long) nums[left] + nums[right];
            if (sum == target) return new int[]{nums[left], nums[right]};
            if (sum < target) left++;
            else right--;
        }
        return null;
    }

    private static void printPair(int[] pair) {
        System.out.println(pair == null
                ? "Not Found"
                : "(" + pair[0] + ", " + pair[1] + ")");
    }

    public static void main(String[] args) {
        printPair(pairSumSorted(new int[]{-4, -1, 0, 3, 5, 9}, 4));
        printPair(pairSumSorted(new int[]{1, 2, 3}, 100));
    }
}
