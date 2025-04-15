import java.io.*;
import java.util.*;

public class BOJ_1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());  // 오영식이 가진 랜선 수
        int N = Integer.parseInt(st.nextToken());  // 필요한 랜선 수

        int[] cables = new int[K];
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(cables);

        long start = 1;
        long end = cables[K-1];
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            long sum = 0;
            for (int cable : cables) {
                sum += (cable / mid);
            }

            if (sum >= N) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}
