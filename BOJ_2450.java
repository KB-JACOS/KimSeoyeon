import java.io.*;
import java.util.*;

public class BOJ_2450 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int result = Integer.MAX_VALUE;
        
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] numCnt = new int[4];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            numCnt[arr[i]]++;
        }

        // 조합 만들기 - 6가지 경우
        int[][] perm = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
        for(int i = 0; i < 6; i++) {
            int[] check = perm[i];

            int[] combi = new int[n];

            int idx = 0;
            for (int num : check) {
                Arrays.fill(combi, idx, idx + numCnt[num], num);
                idx += numCnt[num];
            }

//            System.out.println("combi = " + Arrays.toString(combi));

            // 지금 숫자 배열과 조합을 통해 만들어진 배열의 차이를 나타내는 map을 만들어보자
            int[][] map = new int[4][4];
            for(int k = 0; k < n; k++) {
                if(arr[k] == combi[k]) continue;

                map[combi[k]][arr[k]]++;
            }

//            System.out.println("map = " + Arrays.deepToString(map));

            // 이 조합의 상황에서의 최소로 움직이는 횟수
            int minMove = map[1][2] + map[1][3] + Math.max(map[2][3], map[3][2]);
//            System.out.println("minMove = " + minMove);
            result = Math.min(result, minMove);
        }
        System.out.println(result);
    }
}
