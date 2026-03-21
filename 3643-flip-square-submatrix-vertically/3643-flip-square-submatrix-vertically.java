class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        
        // Traverse half rows of the k x k submatrix
        for (int i = 0; i < k / 2; i++) {
            
            // Traverse columns inside submatrix
            for (int j = y; j < y + k; j++) {
                
                // Swap rows vertically
                int temp = grid[x + i][j];
                grid[x + i][j] = grid[x + k - 1 - i][j];
                grid[x + k - 1 - i][j] = temp;
            }
        }
        
        return grid;
    }
}