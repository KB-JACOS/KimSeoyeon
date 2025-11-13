import java.io.*;
import java.util.*;

public class BOJ_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sumMap = new int[n+1][n+1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sumMap[i+1][j+1] = map[i][j] + sumMap[i][j+1] + sumMap[i+1][j] - sumMap[i][j];
            }
        }
//        System.out.println("sumMap = " + Arrays.deepToString(sumMap));

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int answer = sumMap[x2][y2] - sumMap[x1-1][y2] - sumMap[x2][y1-1] + sumMap[x1-1][y1-1];
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
