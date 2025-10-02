import java.io.*;
import java.util.*;

public class BOJ_2169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        System.out.println("arr = " + Arrays.deepToString(arr));

        int[][] answer = new int[N][M];

        answer[0][0] = arr[0][0];
        for(int i = 1; i < M; i++) {
            answer[0][i] = answer[0][i-1] + arr[0][i];
        }

        for(int i = 1; i < N; i++) {
            int[][] temp = new int[2][M];

            // 왼쪽에서 오른쪽으로 이동하는데 위에서랑 비교
            temp[0][0] = answer[i-1][0] + arr[i][0];
            for (int j = 1; j < M; j++) {
                temp[0][j] = Math.max(temp[0][j-1], answer[i-1][j]) + arr[i][j];
            }
//            System.out.println("1) temp = " + Arrays.deepToString(temp));

            // 오른쪽에서 왼쪽으로 이동하는데 위에서랑 비교
            temp[1][M-1] = answer[i-1][M-1] + arr[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j+1], answer[i-1][j]) + arr[i][j];
            }
//            System.out.println("2) temp = " + Arrays.deepToString(temp));

            for (int j = 0; j < M; j++) {
                answer[i][j] = Math.max(temp[0][j], temp[1][j]);
            }

//            System.out.println("answer = " + Arrays.deepToString(answer));

        }

//        System.out.println("answer = " + Arrays.deepToString(answer));
        System.out.println(answer[N-1][M-1]);
    }
}
