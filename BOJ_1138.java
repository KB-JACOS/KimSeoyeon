import java.io.*;
import java.util.*;

public class BOJ_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] ans = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int idx = Integer.parseInt(st.nextToken());

            int time = 0;
            for(int j = 0; j < n; j++) {
                if(ans[j] == 0) {
                    if(idx == time) {
                        ans[j] = i;
                        break;
                    }
                    time++;
                }
            }
        }

        for(int i : ans) {
            System.out.print(i + " ");
        }
    }
}
