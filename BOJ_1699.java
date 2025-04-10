import java.io.*;
import java.util.*;

public class BOJ_1699 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        dp[0] = 0;
        dp[1] = 1;

        System.out.println(cal(N));
    }

    private static int cal(int N) {
        for(int i = 2; i <= N; i++) {
            dp[i] = i;
            for(int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[N];
    }
}
