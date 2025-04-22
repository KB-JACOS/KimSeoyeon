import java.io.*;
import java.util.*;

public class BOJ_14500 {
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//         System.out.println(Arrays.deepToString(arr));
        br.close();

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;

                dfs(i, j, 1, arr[i][j], visited);

                // T 모양 체크
                checkT(i, j);

                // 하나씩 기준점이 되어 탐색해야 해서
                visited[i][j] = false;
            }
        }
        System.out.println(max);

    }

    // 재귀 -> 종료 필수
    static void dfs(int x, int y, int depth, int nowSum, boolean[][] visited) {
        if (depth == 4) {
            max = Math.max(max, nowSum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나면
            if (ny < 0 || nx < 0 || nx >= N || ny >= M) continue;

            // 이미 방문한 곳이면 다시 볼 필요가 없으니까
            if (!visited[nx][ny]) {
                visited[nx][ny] = true;

                dfs(nx, ny, depth + 1, nowSum + arr[nx][ny], visited);

                visited[nx][ny] = false;
            }
        }

    }

    // 기준: 가운데
    static void checkT(int row, int col) {
        // 아
        if (col >= 0 && row >= 1 && col <= M - 2 && row <= N - 2) {
            max = Math.max(max, arr[row][col] + arr[row+1][col] + arr[row-1][col] + arr[row][col+1]);
        }

        // 어
        if (col >= 1 && row >= 1 && col <= M - 1 && row <= N - 2) {
            max = Math.max(max, arr[row][col] + arr[row+1][col] + arr[row-1][col] + arr[row][col-1]);
        }

        // 오
        if (col >= 1 && row >= 1 && col <= M - 2 && row <= N - 1) {
            max = Math.max(max, arr[row][col] + arr[row][col+1] + arr[row][col-1] + arr[row-1][col]);
        }

        // 우
        if (col >= 1 && row >= 0 && col <= M - 2 && row <= N - 2) {
            max = Math.max(max, arr[row][col] + arr[row][col+1] + arr[row][col-1] + arr[row+1][col]);
        }
    }
}
