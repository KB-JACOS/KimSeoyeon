import java.io.*;
import java.util.*;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        Arrays.sort(arr);
        for(int i = 0; i < n; i++) {
            int targetNum = arr[i];

            int s = 0, e = n-1;
            while(s < e) {
                if(s == i) {
                    s++;
                    continue;
                }
                if(e == i) {
                    e--;
                    continue;
                }

                int sum = arr[s] + arr[e];

                if(sum == targetNum) {
                    if(s != i && e != i) {
                        result++;
                        break;
                    }
                } else if(sum < targetNum) {
                    s++;
                } else if(sum > targetNum) {
                    e--;
                }
            }
        }

        System.out.println(result);
    }
}
