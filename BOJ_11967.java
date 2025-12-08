import java.io.*;
import java.util.*;

public class BOJ_11967 {
    static List<int[]>[][] list;
    static boolean[][] visited;
    static boolean[][] lights;
    static Deque<int[]> q;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                list[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            list[x][y].add(new int[] {a, b});
        }

        // 초기화
        q = new ArrayDeque<>();
        visited = new boolean[n+2][n+2];
        lights = new boolean[n+2][n+2];

        // 시작
        q.add(new int[] {1, 1});
        visited[1][1] = true;
        lights[1][1] = true;
        result = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0], col = cur[1];

            for(int[] node : list[row][col]) {
                int cr = node[0], cc = node[1];

                if(!lights[cr][cc]) {
                    lights[cr][cc] = true;
                    result++;

                    // 만약 새로 켠 방이 이미 방문한 방과 인접해 있으면 접근 가능하므로 큐에 넣는다
                    for (int i = 0; i < 4; i++) {
                        int nr = cr + dr[i], nc = cc + dc[i];
                        if (visited[nr][nc]) {
                            // 인접한 어떤 방이 이미 방문되어 있으면 새로 켠 방으로 이동 가능
                            if (!visited[cr][cc]) {
                                visited[cr][cc] = true;
                                q.add(new int[]{cr, cc});
                            }
                            break;
                        }
                    }
                }
            }

            // 현재 방에서 인접한 방들로 이동 (불이 켜져 있고 아직 방문하지 않은 경우)
            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i], nc = col + dc[i];
                if (lights[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }

        System.out.println(result);
    }
}
