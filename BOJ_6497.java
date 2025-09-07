import java.io.*;
import java.util.*;

public class BOJ_6497 {

    static class Edge implements Comparable<Edge> {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken()); // 집의 수
            int n = Integer.parseInt(st.nextToken()); // 길이의 수

            if (m == 0 && n == 0) break;

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                graph.add(new ArrayList<>());
            }

            int totalCost = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph.get(x).add(new Edge(y, z));
                graph.get(y).add(new Edge(x, z));

                totalCost += z;
            }

            boolean[] visited = new boolean[m];
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(0, 0));

            int mstCost = 0;
            int count = 0;

            while (!pq.isEmpty() && count < m) {
                Edge cur = pq.poll();
                if (visited[cur.to]) continue;

                visited[cur.to] = true;
                mstCost += cur.cost;
                count++;

                for (Edge next : graph.get(cur.to)) {
                    if (!visited[next.to]) {
                        pq.add(next);
                    }
                }
            }

            System.out.println(totalCost - mstCost);
        }
    }
}
