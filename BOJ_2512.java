import java.io.*;
import java.util.*;

public class BOJ_2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] budget = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        // 예산 정렬
        Arrays.sort(budget);

        int left = 0;
        int right = budget[N-1];
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int total = 0;
            for (int b : budget) {
                total += Math.min(b, mid);
            }

            if (total <= M) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
