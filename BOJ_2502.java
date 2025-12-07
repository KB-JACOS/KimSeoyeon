import java.io.*;
import java.util.*;

public class BOJ_2502 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[d+1][2];

        dp[1][0] = 1; dp[1][1] = 0;
        dp[2][0] = 0; dp[2][1] = 1;
        for(int i = 3; i <= d; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }
        System.out.println("dp = " + Arrays.deepToString(dp));

        int max = k / dp[d][1];
        for(int i = 1; i <= max; i++) {
            for(int j = 1; j <= i; j++) {
                if(dp[d][0] * j + dp[d][1] * i == k) {
                    System.out.println(j);
                    System.out.println(i);
                    System.exit(0);
                }
            }
        }
    }
}
