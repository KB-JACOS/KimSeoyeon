import java.io.*;
import java.util.*;

public class BOJ_4195 {
    static HashMap<String, Integer> map;
    static int[] parent;
    static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) { // 테스트

            // 시작
            int n = Integer.parseInt(br.readLine());
            parent = new int[2*n];
            for(int j = 0; j < 2*n; j++) {
                parent[j] = j;
            }

            dp = new int[2*n];
            Arrays.fill(dp, 1);

            map = new HashMap<>();
            
            int num = 0;
            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                String first = st.nextToken();
                String second = st.nextToken();

                if(!map.containsKey(first)) map.put(first, num++);
                if(!map.containsKey(second)) map.put(second, num++);

                int result = union(map.get(first), map.get(second));
                sb.append(result).append("\n");
//                System.out.println(result);
            }
            // 끝
        }
        System.out.println(sb);
    }

    static int find(int x) {
//        if(x == parent[x]) return x;
//        else return find(parent[x]);

        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static int union(int x, int y) {
        x = find(x);
        y = find(y);

//        if(x == y) return dp[x];
//        else {
//            parent[y] = x;
//            dp[x] += dp[y];
//            return dp[x];
//        }

        if(x != y){
            parent[y] = x;
            dp[x] += dp[y];
        }
        return dp[x];
    }
}
