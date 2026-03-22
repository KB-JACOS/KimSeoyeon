import java.io.*;
import java.util.*;

public class BOJ_1103 {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] result;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];
        result = new int[n][m];

        for(int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();

            for(int j = 0; j < m; j++) {
                if(input[j] - '0' == 24) arr[i][j] = -1;
                else arr[i][j] = input[j] - '0';
            }
        }

        int answer = dfs(0, 0);
        System.out.println(answer);
    }

    static int dfs(int r, int c) {
        if(r < 0 || r >= n || c < 0 || c >= m) return 0;
        if(arr[r][c] == -1) return 0;

        if(visited[r][c]) {
            System.out.println(-1);
            System.exit(0);
        }

        if(result[r][c] != 0) return result[r][c];

        visited[r][c] = true;

        int max = 0;
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i] * arr[r][c];
            int nc = c + dc[i] * arr[r][c];

            max = Math.max(max, dfs(nr, nc) + 1);
        }

        visited[r][c] = false;
        result[r][c] = max;

        return max;
    }
}
