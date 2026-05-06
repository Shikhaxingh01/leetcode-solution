
class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;        // original rows
        int n = boxGrid[0].length;     // original cols

        // After rotating 90° clockwise:
        // new grid has dimensions n x m
        char[][] result = new char[n][m];

        // ----------------------------
        // 1) Rotate the original grid
        // Mapping for clockwise rotation:
        // (i, j) in original -> (j, m - 1 - i) in rotated
        // ----------------------------
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][m - 1 - i] = boxGrid[i][j];
            }
        }

        // ----------------------------
        // 2) Apply gravity on the rotated grid
        //
        // In rotated grid, gravity pulls stones '#' "down",
        // i.e., towards increasing row index (towards row n-1).
        //
        // emptyIndex[col] = the next lowest row in this column where
        // a stone can fall and settle (within the current segment).
        //
        // Why segments?
        // Obstacles '*' break a column into independent segments.
        // Stones above an obstacle cannot pass through it.
        // ----------------------------
        int[] emptyIndex = new int[m];           // one pointer per column (since result has m columns)
        Arrays.fill(emptyIndex, n - 1);          // initially, stones can fall to the bottom row in each column

        // Traverse rows bottom-up.
        // Bottom-up ensures when we "drop" a stone to emptyIndex[col],
        // we won't later re-process/mess up already settled stones below.
        for (int i = n - 1; i >= 0; i--) {       // i = row in rotated grid
            for (int j = 0; j < m; j++) {        // j = col in rotated grid

                if (result[i][j] == '#') {
                    // Found a stone. It should fall to the current emptyIndex[j]
                    // unless it's already there.
                    if (emptyIndex[j] != i) {
                        result[emptyIndex[j]][j] = '#'; // move stone down
                        result[i][j] = '.';             // leave empty behind
                    }
                    // Next stone in the same segment must stack above this one.
                    emptyIndex[j]--;

                } else if (result[i][j] == '*') {
                    // Obstacle blocks falling. Reset the landing pointer to just above it.
                    // Any stones above can only fall until (i - 1).
                    emptyIndex[j] = i - 1;
                }

                // If result[i][j] == '.', do nothing.
                // It's just empty space; pointers/states remain unchanged.
            }
        }

        return result;
    }
}