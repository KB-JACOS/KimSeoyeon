import java.io.*;
import java.util.*;
public class BOJ_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int left = 1, right = 1;

        int sum = 1;
        int answer = 0;

        while(left <= right) {
            if(sum == n) answer++;

            if(sum < n) {
                right++;
                sum += right;
            }

            else if(sum >= n) {
                sum -= left;
                left++;
            }
        }
        System.out.println(answer);
    }
}
