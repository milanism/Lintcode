public int[][] matrixRestoration(int n, int m, int[][] after) {
        // write your code here
        if (n == 0 || m == 0 || after == null) {
            return new int[0][0];
        }
        
        int[][] res = new int[n][m];
        res[0][0] = after[0][0];
        for (int i = 1; i < m; i++) {
            res[0][i] = after[0][i] - after[0][i - 1];
        }
        
        for (int j = 1; j < n; j++) {
            res[j][0] = after[j][0] - after[j - 1][0];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                res[i][j] = after[i][j] + after[i - 1][j - 1] - after[i - 1][j] - after[i][j - 1];
            }
        }
        
        return res;
    }