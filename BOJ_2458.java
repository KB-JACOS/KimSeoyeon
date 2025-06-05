import java.io.*;
import java.util.*;

public class BOJ_2458 {
    public static void main(String[] args) throws IOException {

        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] childList = new ArrayList[N+1];
        List<Integer>[] parentList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            childList[i] = new ArrayList<>();
            parentList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            childList[a].add(b);
            parentList[b].add(a);
        }

        for (int i = 1; i <= N; i++) { // 1부터 N까지 N번 반복
            boolean[] visited = new boolean[N+1];
            visited[i] = true;
            check(i, childList, visited);
            check(i, parentList, visited);

            int check = 0;
            for (boolean b : visited) {
                if (b) check++;
            }

            if (check == N) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void check(int start, List<Integer>[] list, boolean[] visited) {

        if (list[start].isEmpty()) {
            return;
        }

        List<Integer> node = list[start];
        for (Integer i : node) {
            if (!visited[i]) {
                visited[i] = true;
                check(i, list, visited);
            }
        }
    }
}
