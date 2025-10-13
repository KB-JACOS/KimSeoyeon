import java.io.*;
import java.util.*;

public class BOJ_1783 {
    static int[] dr = {-2, -1, 1, 2};
    static int[] dc = {1, 2, 2, 1};

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int result;
        if(N == 1) {
            result = 1;
            System.exit(0);
        }
        else if(N == 2) {
            
        }
        result = bfs(N-1, 0, 1);

        System.out.println(result);
    }

    static int bfs(int r, int c, int cnt) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new int[] {r, c, cnt});
        visited[r][c] = true;

        int max = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1];
            System.out.println(cur[2]);

            max = Math.max(max, cur[2]);

            for(int i = 0; i < 4; i++) {
                int nr = cr + dr[i], nc = cc + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(visited[nr][nc]) continue;

                q.add(new int[] {nr, nc, cur[2] + 1});
                visited[nr][nc] = true;
            }
        }

        return max;

    }
}
