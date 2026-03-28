class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Check diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        // DSU setup
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Find
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] != x)
                    parent[x] = applyAsInt(parent[x]);
                return parent[x];
            }
        };

        // Union
        java.util.function.BiConsumer<Integer, Integer> union = (a, b) -> {
            int pa = find.applyAsInt(a);
            int pb = find.applyAsInt(b);
            if (pa != pb) parent[pa] = pb;
        };

        // Step 2: union where lcp > 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union.accept(i, j);
                }
            }
        }

        // Step 3: assign characters
        char[] res = new char[n];
        int[] map = new int[n];
        java.util.Arrays.fill(map, -1);

        int charId = 0;

        for (int i = 0; i < n; i++) {
            int root = find.applyAsInt(i);

            if (map[root] == -1) {
                if (charId == 26) return "";
                map[root] = charId++;
            }

            res[i] = (char) ('a' + map[root]);
        }

        // Step 4: Validate LCP
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (res[i] == res[j]) {
                    dp[i][j] = 1;
                    if (i + 1 < n && j + 1 < n)
                        dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }

        // Compare
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != lcp[i][j]) return "";
            }
        }

        return new String(res);
    }
}