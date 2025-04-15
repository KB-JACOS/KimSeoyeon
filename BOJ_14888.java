import java.io.*;
import java.util.*;

public class BOJ_14888 {
    public static int maxResult = Integer.MIN_VALUE;
    public static int minResult = Integer.MAX_VALUE;
    public static int[] operator = new int[4];
    public static int[] number;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        number = new int[N];
        for(int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // + - * / 순서
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        dfs(number[0], 1);

        System.out.println(maxResult);
        System.out.println(minResult);

    }
    public static void dfs(int num, int idx) {
        if (idx == N) {
            maxResult = Math.max(maxResult, num);
            minResult = Math.min(minResult, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 연산자 개수가 1개 이상인 경우
            if (operator[i] > 0) {

                // 해당 연산자를 1 감소시킨다.
                operator[i]--;

                switch (i) {
                    case 0:	dfs(num + number[idx], idx + 1);	break;
                    case 1:	dfs(num - number[idx], idx + 1);	break;
                    case 2:	dfs(num * number[idx], idx + 1);	break;
                    case 3:	dfs(num / number[idx], idx + 1);	break;

                }
                // 재귀호출이 종료되면 다시 해당 연산자 개수를 복구한다.
                operator[i]++;
            }
        }
    }
}
