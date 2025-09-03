import java.io.*;
import java.util.*;

public class BOJ_7576 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // col
        int n = Integer.parseInt(st.nextToken()); // row

        Deque<int[]> q = new ArrayDeque<>();

        // 1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토 없음
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    q.add(new int[]{i, j, 0});
                }
                arr[i][j] = num;
            }
        }

        int max = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], depth = cur[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 나가면 무시
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                // 이미 익었다면 건너 뜀
                if (arr[nr][nc] == 1) {
                    continue;
                }

                if (arr[nr][nc] == 0) {
                    q.add(new int[]{nr, nc, depth + 1});
                    arr[nr][nc] = 1; // 방문 처리
                    max = depth + 1;
                }
            }
        }

        for (int[] row : arr) {
            for (int col : row) {
                if (col == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }

        // 다 처리하고 보니 처음부터 익은 토마토와 벽 밖에 없었을 떄
        if (max == Integer.MIN_VALUE) {
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(max);
    }
}
