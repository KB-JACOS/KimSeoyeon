import java.io.*;
import java.util.*;

public class BOJ_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 보석 개수
        int k = Integer.parseInt(st.nextToken()); // 가방 개수

        List<int[]> jewelry = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 무게
            int v = Integer.parseInt(st.nextToken()); // 가격

            jewelry.add(new int[] {m, v});
        }
        Collections.sort(jewelry, (a, b) -> a[0] - b[0]); // 가벼운 순서대로

        List<Integer> bags = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()); // 무게

            bags.add(c);
        }
        Collections.sort(bags); // 가벼운 순서대로

        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int idx = 0;
        for(int i = 0; i < k; i++) {
            while(idx < n && jewelry.get(idx)[0] <= bags.get(i)) {
                pq.add(jewelry.get(idx)[1]);
                idx++;
            }

            if(!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}
