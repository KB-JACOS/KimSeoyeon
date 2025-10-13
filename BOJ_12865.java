import java.io.*;
import java.util.*;

public class BOJ_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K+1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            for(int j = K; j >= W; j--) {
                dp[j] = Math.max(dp[j], dp[j-W]+V);
            }
//            System.out.println("dp = " + Arrays.toString(dp));
        }
        /*
        int result = 0;
        for(int i = 0; i <= K; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
         */
        System.out.println(dp[K]);
    }
}
