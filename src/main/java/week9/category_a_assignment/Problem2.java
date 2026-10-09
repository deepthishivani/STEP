package week9.category_a_assignment;

public class Problem2 {
    // Non-negative costs allow a sliding window.
    // O(n) time, O(1) extra space.
    // Checking all runs with running sums takes O(n²) time.
    public static int[] longestStreak(int[] costs, long budget) {
        int left = 0, bestLength = 0, bestStart = -1;
        long sum = 0;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (sum > budget && left <= right) {
                sum -= costs[left++];
            }

            int length = right - left + 1;
            // Strict > retains the earliest start in a tie.
            if (length > bestLength) {
                bestLength = length;
                bestStart = left;
            }
        }
        return new int[]{bestLength, bestStart};
    }

    private static void printResult(int[] result) {
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }

    public static void main(String[] args) {
        printResult(longestStreak(
                new int[]{4, 2, 1, 7, 3, 1, 2, 1, 5}, 8));
        printResult(longestStreak(new int[]{9, 10}, 8));
    }
}
