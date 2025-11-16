import java.io.*;
import java.util.*;
public class BOJ_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long result = 0;

        long[] arrSum = new long[n+1];
        long[] leftNum = new long[m];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            arrSum[i] = arrSum[i-1] + Integer.parseInt(st.nextToken());

            if(arrSum[i] % m == 0) result++;
            leftNum[(int)(arrSum[i] % m)]++;
        }

        for(int i = 0; i < m; i++) {
            long cnt = leftNum[i];
            if(cnt >= 2) result += (cnt * (cnt-1) / 2);
        }

        System.out.println(result);
    }
}
