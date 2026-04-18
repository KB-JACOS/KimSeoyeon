import java.io.*;
import java.util.*;

public class BOJ_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[b]++;
            list[a].add(b);
        }

        System.out.println("arr = " + Arrays.toString(arr));

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(arr[i] == 0) q.add(i);
        }

        List<Integer> answer = new ArrayList<>();

        while(!q.isEmpty()) {
            int number = q.poll();
            answer.add(number);
            for(int num : list[number]) {
                arr[num]--;

                if(arr[num] == 0) q.add(num);
            }
        }

        for(int num : answer) {
            System.out.print(num + " ");
        }
    }
}
