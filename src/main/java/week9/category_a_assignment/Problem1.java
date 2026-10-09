package week9.category_a_assignment;

import java.util.Arrays;

public class Problem1 {
    // Prefix sums: O(n + q) time.
    // O(n) extra space for prefixes, plus O(q) for the answer.
    // Summing each range separately can take O(nq) time.
    public static long[] footfallReport(int[] visitors, int[][] queries) {
        long[] prefix = new long[visitors.length + 1];
        for (int i = 0; i < visitors.length; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        long[] answers = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            answers[i] = prefix[end + 1] - prefix[start];
        }
        return answers;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};
        System.out.println(Arrays.toString(footfallReport(visitors, queries)));
    }
}
