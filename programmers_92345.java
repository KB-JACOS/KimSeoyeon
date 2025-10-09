import java.util.*;
class programmers_92345 {

    public static void main(String[] args) {
        int result = solution(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, new int[] {1, 0}, new int[] {1, 2});
        System.out.println("result = " + result);
    }
    private static int row, col;

    private static int[] dr = {1, -1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        row = board.length;
        col = board[0].length;

        return move(board, aloc[0], aloc[1], bloc[0], bloc[1]).getMoveCnt(); // A부터 시작
    }

    private static Result move(int[][] board, int ar, int ac, int br, int bc) {

        // 이동할 수 없는 경우 -> 현재 플레이어가 지는 걸 의미, 현재 플레이어는 움직이지 않음.
        if (isEnded(board, ar, ac)) {
            return new Result(false, 0);
        }

        // 같은 위치에 두 플레이어가 모인 경우, 현재 플레이어가 이기는 것을 의미
        if (ar == br && ac == bc) {
            return new Result(true, 1);
        }

        boolean canWin = false; // 승리하는 경우가 하나라도 존재하는지 체크하기 위함
        int maxValue = -1; // 승리하는 경우가 존재하지 않으면 최대한 오래 버티기
        int minValue = Integer.MAX_VALUE; // 승리하는 경우가 존재하면 최대한 빨리 끝내기

        board[ar][ac] = 0; // 현재 위치의 발판 없애기

        for (int i = 0; i < 4; i++) {
            int nr = ar + dr[i];
            int nc = ac + dc[i];

            // 다음 발판으로 갈 수 있는지 체크
            if (!inRange(nr, nc) || board[nr][nc] == 0) continue;

            Result nextResult = move(board, br, bc, nr, nc);

            if (nextResult.getIsWin()) { // 상대 플레이어가 이기면 현재 플레이어는 짐.
                // 다음 갈 수 있는 모든 경로 중 승리하는 경우가 하나도 존재하지 않는 경우
                // 최대한 오래 버티기 위해서 이동 횟수를 최대로 선택.
                maxValue = Math.max(maxValue, nextResult.getMoveCnt());
            } else { // 상대 플레이어가 지면 현재 플레이어는 이김.
                // 다음 갈 수 있는 모든 경로 중 승리하는 경우가 하나라도 존재하는 경우
                // 최대한 빨리 끝내기 위해서 이동 횟수를 최소로 선택.
                canWin = true;
                minValue = Math.min(minValue, nextResult.getMoveCnt());
            }
        }

        board[ar][ac] = 1; // 재귀를 빠져나가기 위해서 없앴던 발판 돌려기

        // canWin이 true면 이길 수 있기에 minValue 값 이용.
        // canWin이 false면 이길 수 없기에 maxValue 값 이용.
        return new Result(canWin, (canWin ? minValue : maxValue) + 1);
    }

    private static boolean isEnded(int[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (inRange(nr, nc) && board[nr][nc] == 1) return false;
        }
        return true;
    }

    // 범위 내에 있는가?
    private static boolean inRange(int r, int c) {
        return 0 <= r && r < row && 0 <= c && c < col;
    }
    
}

class Result {
    private boolean isWin; // 승리 여부
    private int moveCnt; // 움직임 횟수

    public Result(boolean isWin, int moveCnt) {
        this.isWin = isWin;
        this.moveCnt = moveCnt;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public boolean getIsWin() {
        return isWin;
    }
}

/*
 * A = 나, B = 상대방
 * 만약 끝났을 때의 횟수가
 * 짝수: 내가 지는 것, 홀수: 내가 이기는 것
 * 내가 지는 쪽이면 최대한 많이 움직여야 하고, 이기는 쪽이면 적게 움직여야 함
 * 이기는 경우가 1번이라도 있으면 무조건 이기는 경우를 선택해야 함 -> 그렇지 않으면 최선을 다하지 않은 것
 */
class Solution {

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 상하좌우
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    int[][] board; // board 전역변수화
    int row, col;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;

        row = board.length;
        col = board[0].length;

        Point cntA = new Point(aloc[0], aloc[1]);
        Point cntB = new Point(bloc[0], bloc[1]);

        return dfs(cntA, cntB);
    }

    // me : 현재 턴인 사람 // you : 상대방
    public int dfs(Point me, Point you) {

        int cr = me.x;
        int cc = me.y;

        if(board[cr][cc] == 0) return 0;  // 발판이 사라졌다면 0 반환

        int result = 0; // 최종적인 턴 수
        for(int i = 0; i < 4; i++) {
            int nr = cr + dr[i];
            int nc = cc + dc[i];

            if(nr < 0 || nr >= row || nc < 0 || nc >= col || board[nr][nc] == 0) continue;

            board[cr][cc] = 0; // 이전에 서있던 곳으로 이동불가능하게 만듦

            // 여기서 상대방 턴이기 때문에 매개변수로 dfs(you, me) 순서로 들어간다.
            int val = dfs(you, new Point(nr, nc)) + 1; // 턴수 + 1

            board[cr][cc] = 1; // 사용한 것을 원상 복구

            // 지금까지 모두 진 경우고, 이번(val)에 이겼을 때 -> 바로 이긴걸로 바꿔줌
            if(result % 2 == 0 && val % 2 == 1) result = val;

                // 지금까지도 졌고, 이 경우(val)도 진 경우 -> 최대한 많이 움직인다.
            else if(result % 2 == 0 && val % 2 == 0) result = Math.max(result, val);

                // 지금까지도 이겼고, 이 경우(val)도 이긴 경우 -> 최대한 적게 움직인다.
            else if(result % 2 == 1 && val % 2 == 1) result = Math.min(result, val);
        }

        return result;
    }
}
