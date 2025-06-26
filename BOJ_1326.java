import java.io.*;
import java.util.*;

public class BOJ_1326 {
    static int N;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int result = bfs(a-1, b-1);
        System.out.println(result);
    }

    static int bfs(int start, int target) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {start, 0});

        while(!q.isEmpty()) {
            int[] v = q.poll();
            int idx = v[0];
            int step = arr[idx];

            if (idx == target) return v[1];

            for (int i = idx; i < N; i+=step) {
//                if (i == target) return v[1]+1;
                if (!visited[i]) {
                    q.add(new int[] {i, v[1] + 1});
                    visited[i] = true;
                }
            }

            for (int i = idx; i >= 0; i-=step) {
//                if (i == target) return v[1]+1;
                if (!visited[i]) {
                    q.add(new int[] {i, v[1] + 1});
                    visited[i] = true;
                }
            }

        }
        return -1;
    }
}
