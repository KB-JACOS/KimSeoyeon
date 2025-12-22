import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static void hanoi(int n, int a, int b, int c) {

        if (n == 1) {
            sb.append(a).append(" ").append(c).append("\n");
        } else {
            hanoi(n - 1, a, c, b);
            sb.append(a).append(" ").append(c).append("\n");
            hanoi(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 이동 횟수 출력
        sb.append((1L << n) - 1).append("\n");

        hanoi(n, 1, 2, 3);

        // 결과 출력
        System.out.print(sb.toString());
    }
}

