import java.util.*;

class programmers_1832 {

    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        // 첫 번째 행 값 넣기
        for(int j = 1; j < n; j++) {
            if(cityMap[0][j] == 1) break;
            dp[0][j][0] = 0; // 위에서 올 수 있는 게 없으니까
            dp[0][j][1] = 1; // 무조건 왼쪽에서만 오니까
        }

        // 첫 번째 열 값 넣기
        for(int i = 1; i < m; i++) {
            if(cityMap[i][0] == 1) break;
            dp[i][0][0] = 1; // 무조건 위에서만 오니까
            dp[i][0][1] = 0; // 왼쪽에서 올 수 있는 게 없으니까
        }
        // System.out.println(Arrays.deepToString(dp));

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(cityMap[i][j] == 1) continue;

                if(cityMap[i-1][j] == 2) { // 위쪽이 회전 불가
                    dp[i][j][0] = dp[i-1][j][0];
                } else {
                    dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1])%MOD;
                }

                if(cityMap[i][j-1] == 2) { // 왼쪽이 회전 불가
                    dp[i][j][1] = dp[i][j-1][1];
                } else {
                    dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1])%MOD;
                }

            }
        }
        return (dp[m-1][n-1][0]+dp[m-1][n-1][1])%MOD;
    }
}
