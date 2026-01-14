import java.io.*;
import java.util.*;

class SWEA_1249 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][n];
            for(int i = 0; i < n; i++) {
                char[] c = br.readLine().toCharArray();
                // System.out.println(c);
                for(int j = 0; j < n; j++) {
                    arr[i][j] = c[j] - '0';
                }
            }
            // System.out.println("arr = " + Arrays.deepToString(arr));

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0, 0));

            int answer = Integer.MAX_VALUE;
            int[][] res = new int[n][n];
            for(int i = 0; i < n; i++) {
                Arrays.fill(res[i], Integer.MAX_VALUE);
            }

            while(!pq.isEmpty()) {
                Node cur = pq.poll();

                if(cur.r == n-1 && cur.c == n-1) {
                    answer = Math.min(answer, cur.sum);
                }

                for(int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                    if(res[nr][nc] >= cur.sum + arr[nr][nc] && res[nr][nc] == Integer.MAX_VALUE) {
                        pq.add(new Node(nr, nc, cur.sum + arr[nr][nc]));
                        res[nr][nc] = cur.sum + arr[nr][nc];
                    }
                }
            }

            // System.out.println("answer = " + answer);
            sb.append(String.format("#%d %d\n", test_case, answer));
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int r, c, sum;

        Node(int r, int c, int sum) {
            this.r = r;
            this.c = c;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node other) {
//            return this.sum - other.sum;
            return Integer.compare(this.sum, other.sum);
        }
    }
}