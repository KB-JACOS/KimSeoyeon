import java.io.*;
import java.util.*;

public class BOJ_1717 {
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 0; i <=n; i++) {
            parent[i] = i;
        }
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int check = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if(check == 1) {
                // 출력해야 함
                sb.append(find(num1) == find(num2) ? "YES" : "NO").append("\n");
            } else if(check == 0) {
                // 합해야 함
                union(num1, num2);
            }
        }
        System.out.println(sb);
    }
    static int find(int num) {
        if(num != parent[num]){
            parent[num] = find(parent[num]);
        }
        return parent[num];
    }

    static void union(int num1, int num2) {
        num1 = find(num1);
        num2 = find(num2);
        
        if(num1 < num2) {
            parent[num2] = num1;
        } else if(num2 < num1) {
            parent[num1] = num2;
        }
    }
}
