import java.io.*;
import java.util.*;

public class BOJ_1655 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> b-a);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if(pq1.isEmpty() || pq1.peek() >= num) pq1.add(num);
            else pq2.add(num);

            // 큐 균형 맞추기
            if(pq1.size() == pq2.size()+2) pq2.add(pq1.poll());
            else if(pq1.size()+1 == pq2.size()) pq1.add(pq2.poll());

            sb.append(pq1.peek()).append("\n");
        }
        System.out.println(sb);
    }
}