import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for (int[] row : grid) {
            for (int v : row) total += v;
        }

        // -------- HORIZONTAL --------
        long topSum = 0;

        Map<Integer, Integer> bottom = new HashMap<>();
        for (int[] row : grid) {
            for (int v : row) {
                bottom.put(v, bottom.getOrDefault(v, 0) + 1);
            }
        }

        Map<Integer, Integer> top = new HashMap<>();

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                topSum += val;

                top.put(val, top.getOrDefault(val, 0) + 1);
                bottom.put(val, bottom.get(val) - 1);
                if (bottom.get(val) == 0) bottom.remove(val);
            }

            long bottomSum = total - topSum;

            if (topSum == bottomSum) return true;

            long diff = Math.abs(topSum - bottomSum);

            if (topSum > bottomSum) {
                if (valid(grid, top, diff, 0, i + 1, 0, n)) return true;
            } else {
                if (valid(grid, bottom, diff, i + 1, m, 0, n)) return true;
            }
        }

        // -------- VERTICAL --------
        long leftSum = 0;

        Map<Integer, Integer> right = new HashMap<>();
        for (int[] row : grid) {
            for (int v : row) {
                right.put(v, right.getOrDefault(v, 0) + 1);
            }
        }

        Map<Integer, Integer> left = new HashMap<>();

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int val = grid[i][j];
                leftSum += val;

                left.put(val, left.getOrDefault(val, 0) + 1);
                right.put(val, right.get(val) - 1);
                if (right.get(val) == 0) right.remove(val);
            }

            long rightSum = total - leftSum;

            if (leftSum == rightSum) return true;

            long diff = Math.abs(leftSum - rightSum);

            if (leftSum > rightSum) {
                if (valid(grid, left, diff, 0, m, 0, j + 1)) return true;
            } else {
                if (valid(grid, right, diff, 0, m, j + 1, n)) return true;
            }
        }

        return false;
    }

    private boolean valid(int[][] grid, Map<Integer, Integer> map,
                          long diff, int r1, int r2, int c1, int c2) {

        if (map.getOrDefault((int) diff, 0) == 0) return false;

        int rows = r2 - r1;
        int cols = c2 - c1;

        if (rows * cols == 1) return false;

        // 🔥 FIXED 2D case
        if (rows > 1 && cols > 1) {
            for (int i = r1; i < r2; i++) {
                for (int j = c1; j < c2; j++) {
                    if (grid[i][j] == diff) return true;
                }
            }
            return false;
        }

        if (rows == 1) {
            return grid[r1][c1] == diff || grid[r1][c2 - 1] == diff;
        }

        if (cols == 1) {
            return grid[r1][c1] == diff || grid[r2 - 1][c1] == diff;
        }

        return false;
    }
}