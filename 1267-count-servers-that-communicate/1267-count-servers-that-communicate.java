class Solution {
    public int countServers(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int []countRow = new int[n];
        int []countCol = new int[m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(grid[i][j] == 1) {
                    countRow[i]++;
                    countCol[j]++;
                }
            }
        }
        int res = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(grid[i][j] == 1 && (countRow[i] > 1 || countCol[j] > 1)) {
                    res++;
                }
            }
        }
        return res;
    }
}