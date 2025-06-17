/**
 * 이 풀이가 안되는 이유
 * 만약에 교차되는 전깃줄의 개수가 같다면 어떤 걸 먼저 삭제해야 최적인지 보장할 수 없어
 */
//import java.io.*;
//import java.util.*;

//public class BOJ_2565 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int n = Integer.parseInt(br.readLine());
//
//        List<int[]> list = new ArrayList<>();
//        for(int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//
//            list.add(new int[] {a, b, 0});
//        }
//
//        br.close();
//
//        list.sort(Comparator.comparingInt(o -> o[0]));
//
//        int result = 0;
//
//        while (true) {
//            int[] max = new int[2];
//            max[1] = Integer.MIN_VALUE;
//            for (int i = 0; i < list.size(); i++) {
//                int cnt = check(list.get(i), list);
//                list.get(i)[2] = cnt;
//
//                if (cnt > max[1]) {
//                    max[0] = i; // index 저장
//                    max[1] = cnt;
//                }
//            }
//
//            System.out.println("체크하기");
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(Arrays.toString(list.get(i)));
//            }
//            System.out.println("max = " + Arrays.toString(max));
//            System.out.println("=================");
//
//            int tmp = 0;
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i)[2] == 0) {
//                    tmp++;
//                }
//            }
//
//            if (tmp == list.size()) break;
//
//            list.remove(max[0]);
//            result++; // 삭제 개수 추가
//
//    //        for (int i = 0; i < list.size(); i++) {
//    //            System.out.println(Arrays.toString(list.get(i)));
//    //        }
//        }
//
//        System.out.println(result);
//
//    }
//
//    static int check(int[] comp1, List<int[]> list) {
//        int crossCnt = 0;
//        int a = comp1[0];
//        int b = comp1[1];
//
//        for (int i = 0; i < list.size(); i++) {
//            int aa = list.get(i)[0];
//            int bb = list.get(i)[1];
//
//            if (a < aa && b > bb) crossCnt++;
//            else if (a > aa && b < bb) crossCnt++;
//        }
//
//        return crossCnt;
//    }
//}

/**
 * 일단 교차가 일어나지 않으려면 오름차순일 떄 오름차순이거나 내림차순일 떄 내림차순이어야 한다는 점임
 */

import java.io.*;
import java.util.*;

public class BOJ_2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // A 정렬
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // 초기화
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { // (1, 0), (2, 0), (2, 1), (3, 0), (3, 1), (3, 2), ...
                if (arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(dp));

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }

        // 최대 max를 만들기 위해서 n-max를 제거해줘야 함
        System.out.println(n - max);
    }
}

//import java.io.*;
//import java.util.*;
//
//public class BOJ_2565 {
//    static int[] memo;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int n = Integer.parseInt(br.readLine());
//        int[][] arr = new int[n][2];
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            arr[i][0] = Integer.parseInt(st.nextToken());
//            arr[i][1] = Integer.parseInt(st.nextToken());
//        }
//
//        // A 정렬
//        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
//
//        memo = new int[n+1];
//
//        int len = 0;
//        int idx = 0;
//        for(int i = 0; i < n; i++) {
//            if(arr[i][1] > memo[len]) {
//                len += 1;
//                memo[len] = arr[i][1];
//            } else {
//                idx = binarySearch(0, len, arr[i][1]);
//                memo[idx] = arr[i][1];
//            }
//        }
//
//        // 최대 max를 만들기 위해서 n-len를 제거해줘야 함
//        System.out.println(n - len);
//    }
//6
//    static int binarySearch(int left, int right, int key) {
//        int mid = 0;
//        while(left < right) {
//            mid = (left + right) / 2;
//            if(memo[mid] < key) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return right;
//    }
//}