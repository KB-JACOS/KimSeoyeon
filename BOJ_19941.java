import java.io.*;
import java.util.*;

public class BOJ_19941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split("");

        Deque<Integer> p = new ArrayDeque<>();
        Deque<Integer> h = new ArrayDeque<>();

        int answer = 0;

        for(int i = 0; i < n; i++) {
            if(arr[i].equals("H")) {
                if(!p.isEmpty()) {
                    while(!p.isEmpty() && i - p.peek() > k) {
                        p.poll();
                    }
                    if(!p.isEmpty()) {
                        p.poll();
                        answer++;
                    } else {
                        h.add(i);
                    }
                } else {
                    h.add(i);
                }
            } else {
                if(!h.isEmpty()) {
                    while(!h.isEmpty() && i - h.peek() > k) {
                        h.poll();
                    }
                    if(!h.isEmpty()) {
                        h.poll();
                        answer++;
                    } else {
                        p.add(i);
                    }
                } else {
                    p.add(i);
                }

            }
        }
        System.out.println(answer);

    }
}
