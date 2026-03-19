import java.io.*;
import java.util.*;

public class BOJ_2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int start = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;

            if(start < num) start = num;

            end += num;
        }

//        System.out.println("start = " + start + " end = " + end);

        while(start <= end) {
            int mid = (start + end) / 2;

            int sum = 0;
            int cnt = 1;

            for(int i = 0; i < n; i++) {
                if(sum + arr[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum += arr[i];
            }

            if(cnt > m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }
}
