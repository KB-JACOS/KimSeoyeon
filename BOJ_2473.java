import java.io.*;
import java.util.*;

public class BOJ_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long[] answer = new long[3];
        long abs = Long.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            int left = i+1;
            int right = n-1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];

                if (Math.abs(sum) < abs) {
                    abs = Math.abs(sum);
                    answer = new long[] { arr[i], arr[left], arr[right] };
                }

                if (sum < 0) left++;
                else right--;
            }
        }

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
