import java.io.*;
import java.util.*;

public class BOJ_15831 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        int start = 0;
        int end = 0;
        int white = 0;
        int black = 0;
        int result = 0;

        while (end < N) {
            if (black > B) {
                if (str.charAt(start) == 'W') {
                    white--;
                } else {
                    black--;
                }
                start++;

            } else {
                if (str.charAt(end) == 'W') {
                    white++;
                } else {
                    black++;
                }
                end++;
            }

            // 조건 만족
            if (black <= B && white >= W){
                result = Math.max(result, end - start); // black + white
            }
        }
        System.out.println(result);
    }
}