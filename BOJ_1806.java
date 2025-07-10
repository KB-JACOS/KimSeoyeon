import java.io.*;
import java.util.*;

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;

        int sum = arr[start];
        int length = Integer.MAX_VALUE;

        while(start <= end) {
//            System.out.println("start = " + start + " end = " + end);

            if (sum < s) {
                end++;

                if (end == n) break;
                sum += arr[end];
            } else {
                length = Math.min(length, end - start + 1);
//                System.out.println(length);
                sum -= arr[start];
                start++;
            }

//            if(start >= n) break;
        }

        if (length == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(length);
        }
//        System.out.println(length);
    }
}
