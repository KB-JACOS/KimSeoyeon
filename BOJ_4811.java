import java.io.*;

public class BOJ_4811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[31][31];

        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 2; dp[3][3] = 2;

        while(true) {
            long result = 0;

            int t = Integer.parseInt(br.readLine());
            if(t == 0) break;

            // 이미 계산된 경우
            if (dp[t][1] != 0) {
                for(int i = 1; i <= t; i++) {
                    result += dp[t][i];
                }
                System.out.println(result);
                continue;
            }

            for (int i = 4; i <= t; i++) {
                if (dp[i][1] != 0) continue;

                dp[i][1] = 1;
                dp[i][2] = i-1;

                long sum = 0;
                for(int j = 1; j < i; j++) {
                    sum += dp[i-1][j];
                }
                dp[i][i] = sum;
                dp[i][i-1] = sum;

                for (int j = 3; j < i-1; j++) {

                    for (int r = j-1; r <= i-1; r++) {
                        for(int c = 1; c <= j-1; c++) {
//                            System.out.println("r = " + r + " c = " + c);
                            dp[i][j] += dp[r][c];
                        }
                    }
                }
            }

            for(int i = 1; i <= t; i++) {
                result += dp[t][i];
            }
            System.out.println(result);

        }

    }
}
