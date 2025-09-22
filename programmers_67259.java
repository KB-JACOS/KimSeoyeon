import java.io.*;
import java.util.*;

class programmers_67259 {
    // 상하좌우 
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        int N = board.length;
        
        int[][][] cost = new int[N][N][4];
        for (int[][] arr : cost) {
            for (int[] a : arr) {
                Arrays.fill(a, Integer.MAX_VALUE);
            }
        }
        
        Deque<int[]> q = new ArrayDeque<>(); 
        
        // 아래로 가는 경우
        cost[0][0][1] = 0;
        q.add(new int[]{0, 0, 0, 1});

        // 오른쪽으로 가는 경우
        cost[0][0][3] = 0;
        q.add(new int[]{0, 0, 0, 3});
        
        int answer = Integer.MAX_VALUE;
        
        while(!q.isEmpty()) { 
            int[] cur = q.poll(); 
            int r = cur[0], c = cur[1], cCost = cur[2], dir = cur[3];
            
            if (r == N-1 && c == N-1) {
                answer = Math.min(answer, cCost);
                continue;
            }; 
            
            for (int i = 0; i < 4; i++) { 
                int nr = r + dr[i], nc = c + dc[i]; 
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) continue;
                
                int nCost = cCost + (dir == i ? 100 : 600);
                
                if (cost[nr][nc][i] > nCost) {
                    cost[nr][nc][i] = nCost;
                    q.add(new int[] {nr, nc, nCost, i});
                }
            } 
        }
        
        return answer;
    }
    
}
