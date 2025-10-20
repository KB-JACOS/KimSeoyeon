import java.io.*;
import java.util.*;

public class BOJ_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        // 방법 1
        /*
        String[] TArray = T.split("");

        Deque<String> q = new ArrayDeque<>();
        for(int i = 0; i < TArray.length; i++) {
            q.add(TArray[i]);
        }

        Deque<String> newQ;
        while (q.size() != S.length()) {
            if (q.peekLast().equals("A")) {
                q.pollLast();
            } else {
                q.pollLast();
                newQ = new ArrayDeque<>();
                while (!q.isEmpty()) {
                    newQ.add(q.pollLast());
                }
                q = newQ;
            }
        }

        String rest = "";
        while (!q.isEmpty()) {
            rest += q.poll();
        }

        if (rest.equals(S)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
         */

        // 방법 2
        while (S.length() != T.length()) {
            char lastAlpa = T.charAt(T.length() - 1);

            if (lastAlpa == 'A') {
                T = T.substring(0, T.length() - 1);
            } else if (lastAlpa == 'B') {
                T = T.substring(0, T.length() - 1);
                StringBuffer sb = new StringBuffer(T);
                T = sb.reverse().toString();
            }
        }

        System.out.println(T.equals(S) ? 1 : 0);

    }




}
