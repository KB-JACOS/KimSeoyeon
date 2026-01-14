import java.io.*;
import java.util.*;

public class BOJ_1234 {
    static long[][][][] color = new long[11][101][101][101];
    static long[] factorial = new long[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if((1 + N) * N / 2 > r + g + b) { // 칠해야 할 개수보다 색이 부족하면 0 출력
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(solve(N, r, g, b));
    }

    static long solve(int N, int r, int g, int b) {
        // Base Case
        if (r < 0 || g < 0 || b < 0) return 0;
        if (N <= 0) return 1;

        if (color[N][r][g][b] != 0) return color[N][r][g][b];

        // 1가지 색상을 사용할 경우
        color[N][r][g][b] += solve(N-1, r - N, g, b);
        color[N][r][g][b] += solve(N-1, r, g - N, b);
        color[N][r][g][b] += solve(N-1, r, g, b - N);

        // 2가지 색상을 사용할 경우 -> N이 2의 배수인 경우만 가능
        if (N % 2 == 0) {
            int count = N / 2; // 각 색상을 사용할 개수

            long time = factorial(N) / (factorial(count) * factorial(N-count));
            color[N][r][g][b] += solve(N-1, r-count, g-count, b) * time;
            color[N][r][g][b] += solve(N-1, r, g-count, b-count) * time;
            color[N][r][g][b] += solve(N-1, r-count, g, b-count) * time;
        }

        // 3가지 색상을 사용할 경우 -> N이 3의 배수인 경우만 가능
        if (N % 3 == 0) {
            int count = N / 3;
            color[N][r][g][b] += solve(N-1, r-count, g-count, b-count)
                    * factorial(N) / (factorial(count) * factorial(count) * factorial(N-2*count));
        }

        return color[N][r][g][b];
    }

    static long factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }

        if (factorial[number] != 0) {
            return factorial[number];
        }

        return number * factorial(number - 1);
    }
}
