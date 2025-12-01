import java.io.*;
import java.util.*;

public class BOJ_1005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[N + 1];
            int[] inDegree = new int[N + 1];
            List<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                inDegree[b]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    q.add(i);
                    dp[i] = time[i];
                }
            }

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : graph[cur]) {
                    inDegree[next]--;
                    dp[next] = Math.max(dp[next], dp[cur] + time[next]);
                    if (inDegree[next] == 0) q.add(next);
                }
            }

            int W = Integer.parseInt(br.readLine());
            sb.append(dp[W]).append('\n');
        }

        System.out.print(sb.toString());
    }
}
