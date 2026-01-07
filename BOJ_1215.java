import java.io.*;
import java.util.*;

public class BOJ_1215 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long answer = 0;

        long i = 1;

        // i <= k 까지만 의미 있음
        while(i <= n && i <= k) {
            long ahrt = k / i;
            long last = k / ahrt; // 같은 몫이 유지되는 마지막 i

            if (last > n) last = n;

            long cnt = last - i + 1; // 구간 길이
            long sum = (i + last) * cnt / 2; // Σx (x = i ~ last)

            // x = i ~ last | Σ(k mod x) = Σ(k - ahrt * x) = cnt * k - ahrt * Σx
            answer += cnt * k - ahrt * sum;

            i = last + 1;
        }

        // i > k: (k mod i)의 값은 항상 k 임
        // 그래서 n번까지 반복해야 하니까 (n - i + 1) * k
        if (i <= n) {
            answer += (n - i + 1) * k;
        }

        System.out.println(answer);
    }
}
