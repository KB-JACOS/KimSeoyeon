import java.io.*;
import java.util.*;

public class BOJ_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("");

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            int removeIdx = i-l;

            while(!q.isEmpty()) {
                if(q.peekLast()[1] >= arr[i]) {
                    q.pollLast();
                } else break;
            }

            if(!q.isEmpty() && q.peekFirst()[0] == removeIdx) {
                q.pollFirst();
            }

            q.addLast(new int[] {i, arr[i]});

            sb.append(q.peekFirst()[1]).append(" ");
        }
        System.out.println(sb);
    }
}
