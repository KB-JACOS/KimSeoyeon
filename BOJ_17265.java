import java.io.*;
import java.util.*;

public class BOJ_17265 {
    // 하 우
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    static int N;
    static String[][] map;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
            }
        }

        bfs(0, 0, Integer.parseInt(map[0][0]), 4, 1);
        System.out.println(max + " " + min);
    }

    static void bfs(int r, int c, int num, int operator, int dist) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c, num, operator, dist});

        while(!q.isEmpty()) {
//            System.out.println("r: " + r + ", c: " + c + ", num: " + num + ", operator: " + operator + ", dist: " + dist);
            int[] cur = q.poll();
            int cNum = cur[2], cOperator = cur[3], cDist = cur[4];

            if(cur[0] == N-1 && cur[1] == N-1) {
                max = Math.max(max, cNum);
                min = Math.min(min, cNum);
//                System.out.println("result -> " + max + " " + min);
            }

            for(int i = 0; i < 2; i++) {
                int nr = cur[0] + dr[i], nc = cur[1] + dc[i];
//                System.out.println("nr = " + nr + ", nc = " + nc);

                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if(cDist % 2 == 1) { // 연산자
                    if(map[nr][nc].equals("*")) {
                        q.add(new int[] {nr, nc, cNum, 0, cDist+1});
                    } else if (map[nr][nc].equals("+")) {
                        q.add(new int[] {nr, nc, cNum, 1, cDist+1});
                    } else {
                        q.add(new int[] {nr, nc, cNum, 2, cDist+1});
                    }
                } else { // 숫자(피연산자)
                    int nextNum;
                    if(cOperator == 0) nextNum = cNum * Integer.parseInt(map[nr][nc]);
                    else if(cOperator == 1) nextNum = cNum + Integer.parseInt(map[nr][nc]);
                    else nextNum = cNum - Integer.parseInt(map[nr][nc]);

                    q.add(new int[] {nr, nc, nextNum, 4, cDist+1});
                }
            }

        }
    }
}