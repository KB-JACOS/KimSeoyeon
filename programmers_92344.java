import java.util.*;

class programmers_92344 {
    static int[][] newArr;
    public int solution(int[][] board, int[][] skill) {
        // skill을 다 담은 배열
        newArr = new int[board.length + 1][board[0].length + 1];
        
        for (int row = 0; row < skill.length; row++) {
            // System.out.println(Arrays.toString(skill[row]));
            
            int type = skill[row][0];
            int x1 = skill[row][1], y1 = skill[row][2];
            int x2 = skill[row][3], y2 = skill[row][4];
            int degree = skill[row][5];
            
            if (type == 1) degree = -degree; // 감소

            newArr[x1][y1] += degree;
            newArr[x1][y2 + 1] -= degree;
            newArr[x2 + 1][y1] -= degree;
            newArr[x2 + 1][y2 + 1] += degree;
                        
        }
        // System.out.println(Arrays.deepToString(newArr));
        
        // 누적합 계산 (행만)
        for (int i = 0; i < board.length + 1; i++) {
            for (int j = 1; j < board[0].length + 1; j++) {
                newArr[i][j] += newArr[i][j - 1];
            }
        }

        // System.out.println(Arrays.deepToString(newArr));
        
        // 누적합 계산 (열만)
        for (int j = 0; j < board[0].length + 1; j++) {
            for (int i = 1; i < board.length + 1; i++) {
                newArr[i][j] += newArr[i - 1][j];
            }
        }
        
        // System.out.println(Arrays.deepToString(newArr));

        int answer = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + newArr[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}
