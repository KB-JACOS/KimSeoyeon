import java.io.*;
import java.util.*;

public class BOJ_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
//        System.out.println("arr = " + Arrays.deepToString(arr));

        int[][] max = new int[n][3];
        int[][] min = new int[n][3];
        for(int i = 0; i < 3; i++) {
            max[0][i] = arr[0][i];
            min[0][i] = arr[0][i];
        }

        for(int i = 1; i < n; i++) {
            max[i][0] = arr[i][0] + Math.max(max[i-1][0], max[i-1][1]);
            min[i][0] = arr[i][0] + Math.min(min[i-1][0], min[i-1][1]);

            max[i][1] = arr[i][1] + Math.max(max[i-1][0], Math.max(max[i-1][1], max[i-1][2]));
            min[i][1] = arr[i][1] + Math.min(min[i-1][0], Math.min(min[i-1][1], min[i-1][2]));

            max[i][2] = arr[i][2] + Math.max(max[i-1][1], max[i-1][2]);
            min[i][2] = arr[i][2] + Math.min(min[i-1][1], min[i-1][2]);
        }

        int maxAns = Integer.MIN_VALUE;
        int minAns = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            maxAns = Math.max(maxAns, max[n-1][i]);
            minAns = Math.min(minAns, min[n-1][i]);
        }
        System.out.println(maxAns + " " + minAns);
    }
}
