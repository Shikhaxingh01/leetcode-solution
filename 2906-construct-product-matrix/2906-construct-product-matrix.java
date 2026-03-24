class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int mod = 12345;
        int n = grid.length, m = grid[0].length;
        int size = n * m;
        
        long[] arr = new long[size];
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[idx++] = grid[i][j] % mod;
            }
        }
        
        long[] prefix = new long[size];
        long[] suffix = new long[size];
        
        prefix[0] = arr[0];
        for (int i = 1; i < size; i++) {
            prefix[i] = (prefix[i - 1] * arr[i]) % mod;
        }
        
        suffix[size - 1] = arr[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * arr[i]) % mod;
        }
        
        int[][] result = new int[n][m];
        idx = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long left = (idx > 0) ? prefix[idx - 1] : 1;
                long right = (idx < size - 1) ? suffix[idx + 1] : 1;
                result[i][j] = (int)((left * right) % mod);
                idx++;
            }
        }
        
        return result;
    }
}