import java.io.*;
import java.util.*;

public class BOJ_11404 {

    static class Node implements Comparable<Node>{
        int node;
        int cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 도시
        int m = Integer.parseInt(br.readLine()); // 버스

        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i<= n; i++) {
            list.add(new ArrayList<>());
        }

        int[][] answer = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(answer[i], Integer.MAX_VALUE);
            answer[i][i] = 0;
        }
//        for(int[] row : answer) {
//            Arrays.fill(row, Integer.MAX_VALUE);
//        }

        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
            answer[a][b] = Math.min(answer[a][b], c);
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) { // 경유 도시
            for (int i = 1; i <= n; i++) { // 출발 도시
                for (int j = 1; j <= n; j++) { // 도착 도시
                    if (answer[i][j] > answer[i][k] + answer[k][j]) {
                        answer[i][j] = answer[i][k] + answer[k][j];
                    }
                }
            }
        }

        // 다익스트라
//        dijkstra(n, answer, list);

        // 출력
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < n+1; j++) {
                System.out.print((answer[i][j] == Integer.MAX_VALUE ? 0 : answer[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private static void dijkstra(int n, int[][] answer, List<List<Node>> list) {
        // 모든 도시에서 출발해봐야 함
        for(int start = 1; start <= n; start++) {
            Queue<Node> q = new PriorityQueue<>();
            q.add(new Node(start, 0));
            answer[start][start] = 0;

            while(!q.isEmpty()) {
                Node cur = q.poll();

                // 이미 더 짧은 경로가 있으면 패스
                if (cur.cost > answer[start][cur.node]) continue;

                for(Node next : list.get(cur.node)) {
                    // cur.node를 통해 next.node로 가는 비용
                    int nextDist = cur.cost + next.cost;

                    if (answer[start][next.node] > nextDist) {
                        q.add(new Node(next.node, nextDist));
                        answer[start][next.node] = nextDist;
                    }
                }
            }
        }
    }
}
