import java.io.*;
import java.util.*;

public class BOJ_13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int maxNum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            maxNum = Math.max(maxNum, num);
        }

        int[] cnt = new int[maxNum+1];

        int start = 0, end = 0;

        long result = 0;

        while(end < n) {
            while (cnt[arr[end]] > 0) {
                cnt[arr[start]]--;
                start++;
            }

            cnt[arr[end]]++;
            end++;
            result += end - start;
        }

        System.out.println(result);
    }
}
