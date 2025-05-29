import java.io.*;
import java.util.*;

public class BOJ_13459 {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N;
    static int M;

    static char[][] arr;

    static int redRow = 0;
    static int redCol = 0;
    static int blueRow = 0;
    static int blueCol = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            // .(빈칸) #(이동 불가) O(구멍) R(빨) B(파)
            String input = br.readLine();
            arr[i] = input.toCharArray();
        }
        // System.out.println(Arrays.deepToString(arr));

        // 빨, 파 구슬 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'R') {
                    redRow = i;
                    redCol = j;
                } else if (arr[i][j] == 'B') {
                    blueRow = i;
                    blueCol = j;
                }
            }
        }
        // System.out.println(redRow + " " + redCol + " " +  blueRow + " " + blueCol);

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Deque<State> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        State start = new State(redRow, redCol, blueRow, blueCol, 0);
        q.add(start);
        visited.add(start.toKey());

        while(!q.isEmpty()) {
            State current = q.poll();

            if (current.cnt > 10) return 0; // 10번 안에 해결하지 못했다면
            if (arr[current.rR][current.rC] == 'O') return 1; // 빨간 공이 구멍에 들어갔다면

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                // move 함수를 통해서 다음 위치 구하기
                int[] nextRed = move(current.rR, current.rC, i);
                int[] nextBlue = move(current.bR, current.bC, i);

                // blue가 구멍에 빠지면
                if (arr[nextBlue[0]][nextBlue[1]] == 'O') continue;

                // red 위치 = blue 위치
                // 먼저 도착한 게 그 자리에 있고, 그 다음에 도착 한 건 뒤에 있도록 조정
                if (nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]) {
                    // 이동 거리 계산
                    int redDist = Math.abs(nextRed[0] - current.rR) + Math.abs(nextRed[1] - current.rC);
                    int blueDist = Math.abs(nextBlue[0] - current.bR) + Math.abs(nextBlue[1] - current.bC);

                    if (redDist > blueDist) { // 파란 공이 먼저 도착 -> 빨간 공이 뒤로 물러남
                        nextRed[0] -= dr[i];
                        nextRed[1] -= dc[i];
                    } else { // 빨간 공이 먼저 도착 -> 파란 공이 뒤로 물러남
                        nextBlue[0] -= dr[i];
                        nextBlue[1] -= dc[i];
                    }
                }

                // 방문 여부 확인 후 예약과 방문 표시
                State nextState = new State(nextRed[0], nextRed[1], nextBlue[0], nextBlue[1], current.cnt + 1);
                if (!visited.contains(nextState.toKey())) {
                    visited.add(nextState.toKey());
                    q.add(nextState);
                }
            }

        }
        return 0;
    }

    static int[] move(int row, int col, int dir) {
        while (true) {
            row += dr[dir];
            col += dc[dir];

            if(arr[row][col] == '#') { // 벽을 만날 때 까지 이동
                row -= dr[dir];
                col -= dc[dir];
                break;
            }

            if (arr[row][col] == 'O') {
                break;
            }
        }
        return new int[] {row, col};
    }
}

class State {
    int rR, rC, bR, bC, cnt;

    State(int rR, int rC, int bR, int bC, int cnt) {
        this.rR = rR;
        this.rC = rC;
        this.bR = bR;
        this.bC = bC;
        this.cnt = cnt;
    }

    public String toKey() {
        return rR + ", " + rC + ", " + bR + ", " + bC;
    }
}
