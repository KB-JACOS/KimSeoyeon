import java.io.*;
import java.util.*;

public class BOJ_17471v2 {

    static int n;
    static int[] people;
    static List<Integer>[] graph;
    static int answer;
    static int totalPeople = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        people = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            people[i] = num;
            totalPeople += num;
        }

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int linkCnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j < linkCnt; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        answer = Integer.MAX_VALUE;

        // 비트마스킹으로 모든 부분집합 탐색
        for (int mask = 1; mask < (1 << n) - 1; mask++) {
            boolean[] selected = new boolean[n+1];
            int sumA = 0;
            int countA = 0;

            // A 선거구 만들기
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    selected[i+1] = true;
                    sumA += people[i+1];
                    countA++;
                }
            }

            int countB = n - countA;

            // 연결성 검사
            if (isLinked(selected, true, countA) && isLinked(selected, false, countB)) {
                int sumB = totalPeople - sumA;
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 선거구가 모두 연결되어있는지 확인
    static boolean isLinked(boolean[] selected, boolean flag, int targetCount) {
        boolean[] visited = new boolean[n+1];
        Deque<Integer> q = new ArrayDeque<>();

        // 시작점 찾기
        for (int i = 1; i <= n; i++) {
            if (selected[i] == flag) {
                visited[i] = true;
                q.add(i);
                break;
            }
        }

        int count = 1;
        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                // 방문 이력 없고, 선거구에 해당
                if (!visited[next] && selected[next] == flag) {
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        return count == targetCount;
    }
}