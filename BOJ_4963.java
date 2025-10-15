import java.io.*;
import java.util.*;

public class BOJ_4963 {

    // 상 하 좌 우 좌상 우상 우하 좌하
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, 1, -1};

    static int w;
    static int h;

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken()); // col
            h = Integer.parseInt(st.nextToken()); // row

            if(w == 0 && h == 0) break;

            arr = new int[h][w];
            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            check(arr);
        }
    }

    static void check(int[][] arr) {
        boolean[][] visited = new boolean[h][w];

        int cnt = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, visited);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static void bfs(int i, int j, boolean[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[] {i, j});
        visited[i][j] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for(int k = 0; k < 8; k++) {
                int nr = r + dr[k], nc = c + dc[k];

                if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                if(visited[nr][nc]) continue;
                if(arr[nr][nc] == 0) continue;

                q.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
}
