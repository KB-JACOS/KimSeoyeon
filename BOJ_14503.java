import java.io.*;
import java.util.*;

public class BOJ_14503 {

    static int n, m, r, c;
    static int[][] map;
    static int dir;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static boolean[][] cleaned;

    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cleaned = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol(r, c, dir);

        System.out.println(answer);
    }

    public static void sol(int r, int c, int dir) {

        // 현재 위치 청소
        if (!cleaned[r][c]) {
            cleaned[r][c] = true;
            answer++;
        }

        // 4방향 탐색 -> 이동 가능
        for(int i = 0; i < 4; i++) {
            dir = (dir+3) % 4; // 반시계 방향
            int nr = r + dr[dir], nc = c + dc[dir]; // 다음 이동 위치

            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue; // 범위 벗어나면 무시

            if(map[nr][nc] == 0 && !cleaned[nr][nc]) {
                sol(nr, nc, dir);
                return;
            }
        }

        // 이동 불가 -> 후진해야 함
        int br = r - dr[dir];
        int bc = c - dc[dir];
        if(br >= 0 && br < n && bc >= 0 && bc < m) {
            if(map[br][bc] != 1) { // 벽이 아니면
                sol(br, bc, dir);
            }
        }
    }
}
