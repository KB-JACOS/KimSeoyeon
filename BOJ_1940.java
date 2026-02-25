import java.io.*;
import java.util.*;

public class BOJ_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

//        System.out.println("arr = " + Arrays.toString(arr));

        int s = 0, e = n-1;
        int result = 0;
        while(s < e) {
            int sum = arr[s] + arr[e];

            if(sum < m) s++;
            else if (sum > m) e--;
            else if(sum == m) {
                s++;
                e--;
                result++;
            }
        }

        // 넉넉해서 2중 for문 해도 되긴함
        /*
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int sum = arr[i] + arr[j];
                if(sum == m) result++;
            }
        }
         */

        System.out.println(result);
    }
}
