import java.io.*;
import java.util.*;

public class BOJ_28278 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> q = new ArrayDeque<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch(st.nextToken()) {
                case "1":
                    q.add(Integer.parseInt(st.nextToken()));
                    break;
                case "2":
                    if(!q.isEmpty()) {
                        System.out.println(q.pollLast());
                    } else {
                        System.out.println(-1);
                    }
                    break;
                case "3":
                    System.out.println(q.size());
                    break;
                case "4":
                    System.out.println(q.isEmpty() ? 1 : 0);
                    break;
                case "5":
                    if(!q.isEmpty()) {
                        System.out.println(q.peekLast());
                    } else {
                        System.out.println(-1);
                    }
                    break;
            }
        }
    }
}

