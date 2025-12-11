import java.io.*;
import java.util.*;

public class BOJ_10159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] arr = new int[N+1][N+1];

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[b][a] = -1;
        }

        System.out.println("1 arr = " + Arrays.deepToString(arr));

        for(int k = 1; k < N+1; k++) {
            for(int i = 1; i < N+1; i++) {
                for(int j = 1; j < N+1; j++) {
                    if(arr[i][k] == 1 && arr[k][j] == 1) arr[i][j] = 1;
                    if(arr[i][k] == -1 && arr[k][j] == -1) arr[i][j] = -1;
                }
            }
        }

        System.out.println("2 arr = " + Arrays.deepToString(arr));

        for(int i = 1; i < N+1; i++) {
            int cnt = 0;
            for(int j = 1; j < N+1; j++) {
                if(arr[i][j] != 0) {
                    cnt++;
                }
            }
            sb.append(N-1-cnt).append("\n");
        }
        System.out.println(sb);
    }
}
