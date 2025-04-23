import java.io.*;
import java.util.*;

public class BOJ_2178 {
    static int result;
    static int[][] arr;
    static int N;
    static int M;
    static Deque<Point> q = new ArrayDeque<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        br.close(); // 입력 끝

        int[][] visited = new int[N][M];

        result = bfs(0, 0, visited);

        System.out.println(result);
    }

    static int bfs(int x, int y, int[][] visited) {

        q.add(new Point(x, y, 1));
        visited[x][y] = 1;

        int result = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Point p = q.poll();
            int cnt = p.cnt;
//            System.out.println(p.x + " " + p.y + " " + p.cnt);

            if (p.x == N - 1 && p.y == M - 1) {
                result = Math.min(result, p.cnt);
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위를 벗어나지 않는다면
                if(0 <= nx  && nx < N && 0 <= ny && ny < M) {
                    // 방문 전이고 갈 수 있는 길이면
                    if (visited[nx][ny] == 0 && arr[nx][ny] == 1) {
                        visited[nx][ny] = 1;
                        q.add(new Point(nx, ny, cnt + 1));
                    }
                }
            }
        }
        return result;
    }
}

class Point {
    int x, y;
    int cnt;

    Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

