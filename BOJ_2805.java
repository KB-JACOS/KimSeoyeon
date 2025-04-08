import java.io.*;
import java.util.*;

public class BOJ_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 나무 수
        int M = Integer.parseInt(st.nextToken()); // 필요한 나무 길이

        int[] trees = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        int start = 0;
        int end = trees[N - 1]; // 최대 나무 높이

        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            long sum = 0; // long으로 해야 오버플로우 방지
            for (int i = 0; i < N; i++) {
                if (trees[i] > mid) {
                    sum += (trees[i] - mid);
                }
            }

            if (sum >= M) {
                result = mid; // 조건을 만족하면 결과 저장하고 더 크게 시도
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}
