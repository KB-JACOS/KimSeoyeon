import java.util.*;

class programmers_178139 {
    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int resultA = Integer.MAX_VALUE;
    static int[][] visitedA;
    static int resultB = Integer.MAX_VALUE;
    static int[][] visitedB;
    
    public int solution(String[] maps) {
        char[][] arr = new char[maps.length][maps[0].length()];
        visitedA = new int[maps.length][maps[0].length()];
        visitedB = new int[maps.length][maps[0].length()];

        Deque<int[]> q1 = new ArrayDeque<>();
        Deque<int[]> q2 = new ArrayDeque<>();

        for (int i = 0; i< maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                arr[i][j] = maps[i].charAt(j);
                visitedA[i][j] = Integer.MAX_VALUE;
                visitedB[i][j] = Integer.MAX_VALUE;

                if (maps[i].charAt(j) == 'S') {
                    q1.add(new int[] {i, j, 0});
                    visitedA[i][j] = 0;
                }

                if (maps[i].charAt(j) == 'L') {
                    q2.add(new int[] {i, j, 0});
                    visitedB[i][j] = 0;
                }
            }
        }
//        System.out.println(Arrays.deepToString(arr));

        // S -> L
        while(!q1.isEmpty()) {
            int[] v = q1.poll();
            int row = v[0];
            int col = v[1];
            int cnt = v[2];

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if(nr >= 0 && nr < arr.length && nc >= 0 && nc < arr[0].length) { // 범위 내에 있고

                    if (arr[nr][nc] == 'X' || visitedA[nr][nc] != Integer.MAX_VALUE) continue;

                    visitedA[nr][nc] = Math.min(cnt + 1, visitedA[nr][nc]);

                    if (arr[nr][nc] == 'L') {
                        resultA = Math.min(resultA, visitedA[nr][nc]);
                    }

                    q1.add(new int[] {nr, nc, visitedA[nr][nc]});
                }
            }
        }

        // L -> E
        while(!q2.isEmpty()) {
            int[] v = q2.poll();
            int row = v[0];
            int col = v[1];
            int cnt = v[2];

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if(nr >= 0 && nr < arr.length && nc >= 0 && nc < arr[0].length) { // 범위 내에 있고

                    if (arr[nr][nc] == 'X' || visitedB[nr][nc] != Integer.MAX_VALUE) continue;

                    visitedB[nr][nc] = Math.min(cnt + 1, visitedB[nr][nc]);

                    if (arr[nr][nc] == 'E') {
                        resultB = Math.min(resultB, visitedB[nr][nc]);
                    }

                    q2.add(new int[] {nr, nc, visitedB[nr][nc]});
                }
            }
        }
        if (resultA == Integer.MAX_VALUE || resultB == Integer.MAX_VALUE) {
            return -1;
        } else {
            return resultA + resultB;
        }
    }
}
