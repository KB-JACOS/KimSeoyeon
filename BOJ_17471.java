import java.io.*;
import java.util.*;

public class BOJ_17471 {

    static int n;
    static int[] people;
    static List<Integer>[] graph;
    static int answer;
    static int totalPeople = 0;

    static int[] parent;

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

        // 조합 구하기
        List<Integer> aList = new ArrayList<>();
        for(int cnt = 1; cnt <= n / 2; cnt++) {
            combi(aList, 1, cnt);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 조합 구하기
    static void combi(List<Integer> aList, int start, int r) {
        if(r == 0) {
//            sol1(aList);
            sol2(aList);
            return;
        }

        for(int i = start; i <= n; i++) {
            aList.add(i);
            combi(aList, i+1, r-1);
            aList.remove(aList.size()-1);
        }
    }

    static void sol1(List<Integer> aList) {
        // A구가 잘 연결되어있는지 확인
        if(!isLinked(aList)) return;

        List<Integer> bList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!aList.contains(i)) {
                bList.add(i);
            }
        }

        // B구가 잘 연결되어있는지 확인
        if(!isLinked(bList)) return;

        int result = calcDiff(aList, bList);
        answer = Math.min(answer, result);
    }

    static void sol2(List<Integer> aList) {
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // aList 연결하기
        link(aList);

        // 연결이 다 됐는지
        int rootA = find(aList.get(0));
        for (int i : aList) {
            if (find(i) != rootA) return;
        }

        List<Integer> bList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!aList.contains(i)) {
                bList.add(i);
            }
        }

        // bList 연결하기
        link(bList);

        // 연결이 다 됐는지
        int rootB = find(bList.get(0));
        for (int i : bList) {
            if (find(i) != rootB) return;
        }

        int result = calcDiff(aList, bList);
        answer = Math.min(answer, result);
    }

    static void link(List<Integer> list) {
        for (int i : list) {
            for (int j : graph[i]) {
                if (list.contains(j)) union(i, j);
            }
        }
    }

    // 선거구가 모두 연결되어있는지 확인
    static boolean isLinked(List<Integer> list) {
        boolean[] visited = new boolean[n+1];
        Deque<Integer> q = new ArrayDeque<>();

        visited[list.get(0)] = true;
        q.add(list.get(0));

        int count = 1;
        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                // 방문 이력 없고, 선거구에 해당
                if (!visited[next] && list.contains(next)) {
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        return count == list.size();
    }

    static int calcDiff(List<Integer> aList, List<Integer> bList) {
        int resultA = 0, resultB = 0;

        // A 지역구 인구 계산
        for (int i : aList) {
            resultA += people[i];
        }

        // B 지역구 인구 계산
        for (int i : bList) {
            resultB += people[i];
        }

        return Math.abs(resultA - resultB);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}