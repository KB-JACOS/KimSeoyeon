import java.io.*;
import java.util.*;

public class BOJ_1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++) {
            arr[i][0] = i;
            arr[i][1] = Integer.parseInt(br.readLine());
        }
        System.out.println("arr = " + Arrays.deepToString(arr));
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        System.out.println("arr = " + Arrays.deepToString(arr));

        int answer = Integer.MIN_VALUE;
        for (int idx = 0; idx < n; idx++) {
            int move = arr[idx][0]- idx;
            if(move > answer) answer = move;
        }

        System.out.println(answer+1);
    }
}
