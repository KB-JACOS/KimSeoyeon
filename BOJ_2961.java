/*
// 백트래킹
import java.io.*;
import java.util.*;

public class BOJ_2961 {
    static int[][] flavors;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        flavors = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); // 신맛
            int b = Integer.parseInt(st.nextToken()); // 쓴맛

            flavors[i][0] = s;
            flavors[i][1] = b;
        }

        boolean[] visited = new boolean[n];
        int[] idx = new int[n]; // 인덱스 배열
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // 조합 찾기
        for (int i = 1; i <= n; i++) { // 1개 ~ n개
            combination(idx, visited, 0, n, i);
        }

        System.out.println(result);
    }

    // 백트래킹 사용
    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            calculate(arr, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    static void calculate(int[] arr, boolean[] visited, int n) {
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                idx.add(i);
            }
        }

        int smat = 1;
        int bmat = 0;
        for (int i = 0; i < idx.size(); i++) {
            smat *= flavors[idx.get(i)][0];
            bmat += flavors[idx.get(i)][1];
        }

        int answer = Math.abs((smat - bmat));
        result = Math.min(result, answer);
    }
}
*/
// 비트 마스킹 방법
import java.io.*;
import java.util.*;

public class BOJ_2961 {
    static int[][] flavors;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        flavors = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); // 신맛
            int b = Integer.parseInt(st.nextToken()); // 쓴맛

            flavors[i][0] = s;
            flavors[i][1] = b;
        }

        int lth = 1 << n; // 2의 n승 -> 부분집합의 개수
        for(int i = 1; i < lth; ++i) { // 1부터 시작 -> 공집합 제외
            int smat = 1;
            int bmat = 0;

            for (int j = 0; j < n; ++j) { // 0번째 재료, 1번째 재료...
                if((i & (1 << j)) != 0) {
                    smat *= flavors[j][0];
                    bmat += flavors[j][1];
                }
            }

            int answer = Math.abs(smat - bmat);
            result = Math.min(result, answer);
        }

        System.out.print(result);
    }
}
