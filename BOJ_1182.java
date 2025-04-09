import java.io.*;
import java.util.*;

public class BOJ_1182 {
    static int N;
    static int S;
    static int[] nums;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        countCombi(0,-1,0);

        dfs(0, 0);
        if (S == 0) result--; // 공집합 제거

        System.out.println(result);
    }

    public static void countCombi(int sum, int idx, int pickCount){
        // 합이 S이고 원소가 한 개 이상인 경우
        if (sum == S && pickCount > 0){
            result++;
        }

        // 인덱스가 범위를 넘어가면 종료
        if (idx > N - 1) return;

        for (int i = idx + 1; i < N; i++){
            countCombi(sum + nums[i], i,pickCount + 1);
        }
    }

    private static void dfs(int depth, int sum) {
        if (depth == N) { // 모든 원소를 탐색해서 하나의 부분 수열을 만들었음
            if (sum == S) result++;
            return;
        }
        dfs(depth + 1, sum + nums[depth]);
        dfs(depth + 1, sum);
    }
}
