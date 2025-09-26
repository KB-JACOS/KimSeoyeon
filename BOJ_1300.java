import java.io.*;

public class BOJ_1300 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 1;
        long right = K;

        while(left < right) {
            long mid = (left + right) / 2;
            long count = 0;

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if(K <= count) { // mid보다 작은 수가 B[K]보다 많음
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}
