/*
import java.io.*;
import java.util.*;

public class BOJ_2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxBox = Integer.parseInt(br.readLine());

        // 누적합
        int[] sumArr = new int[n+1];
        for(int i = 1; i <= n; i++) {
            sumArr[i] = sumArr[i-1] + arr[i-1];
        }

        // 박스 합
        int[] boxSum = new int[n];
        for(int i = maxBox-1; i < n; i++) {
            boxSum[i] = sumArr[i+1] - sumArr[i+1-maxBox];
        }

        int[][] answer = new int[3][n];

        // 기차 1개
        for(int i = maxBox-1; i < n; i++) {
            answer[0][i] = Math.max(answer[0][i-1], boxSum[i]);
        }

        // 기차 2개
        for(int i = maxBox*2-1; i < n; i++) {
            answer[1][i] = Math.max(answer[1][i-1], answer[0][i-maxBox] + boxSum[i]);
        }

        // 기차 3개
        for(int i = maxBox*3-1; i < n; i++) {
            answer[2][i] = Math.max(answer[2][i-1], answer[1][i-maxBox] + boxSum[i]);
        }

        System.out.println(answer[2][n-1]);
    }
}
*/

import java.io.*;
import java.util.*;

public class BOJ_2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxBox = Integer.parseInt(br.readLine());

        int[] sumArr = new int[n+1];
        for(int i = 1; i <= n; i++) {
            sumArr[i] = sumArr[i-1] + arr[i-1];
        }

        // 앞에서부터 박스 선택
        int[] frontBoxSum = new int[n];
        for(int i = maxBox-1; i < n; i++) {
            frontBoxSum[i] = sumArr[i+1] - sumArr[i+1-maxBox];
        }

        // 뒤에서부터 박스 선택
        int[] backBoxSum = new int[n];
        for(int i = n-maxBox; i >= 0; i--) {
            backBoxSum[i] = sumArr[i+maxBox] - sumArr[i];
        }

        // front
        int[] frontDp = new int[n];
        for(int i = 1; i < n; i++) {
            frontDp[i] = Math.max(frontDp[i-1], frontBoxSum[i]);
        }

        // back
        int[] backDp = new int[n];
        for(int i = n-2; i >= 0; i--) {
            backDp[i] = Math.max(backDp[i+1], backBoxSum[i]);
        }

        int answer = 0;

        // i = 중간
        for(int i = maxBox*2-1; i < n - maxBox; i++) {
            int mid = frontBoxSum[i];

            // 앞 열차
            int front = frontDp[i-maxBox];

            // 뒤 열차
            int back = backDp[i+1];

            answer = Math.max(answer, front + back + mid);
        }

        System.out.println(answer);
    }
}