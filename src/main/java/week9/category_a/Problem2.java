package week9.category_a;

public class Problem2 {
    static class Summary {
        final long total;
        final int row, col;

        Summary(long total, int row, int col) {
            this.total = total;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "total = " + total
                    + ", maxCoordinate = (" + row + ", " + col + ")";
        }
    }

    // O(m × n) time, O(1) extra space.
    // For an empty grid, coordinates are (-1, -1).
    public static Summary warehouseSummary(int[][] grid) {
        long total = 0;
        int maximum = -1, maxRow = -1, maxCol = -1;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int count = grid[row][col];
                total += count;

                // Strict > keeps the first bin when maximum values tie.
                if (count > maximum) {
                    maximum = count;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        return new Summary(total, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        System.out.println(warehouseSummary(grid));
    }
}
