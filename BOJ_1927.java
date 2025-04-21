import java.io.*;
import java.util.*;

public class BOJ_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> q = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        int x;
        for (int i = 0; i < N; i++) {
            x = Integer.parseInt(br.readLine());

            // 배열에서 가장 작은 값을 출력하고 제거
            if (x == 0) {
                if (q.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(q.poll());
                }
            } else {
                q.add(x);
            }
        }
    }
}
