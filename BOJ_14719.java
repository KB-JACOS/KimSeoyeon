import java.io.*;
import java.util.*;

public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] height = new int[w];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

//        int result = method1(w, height);
        int result = method2(w, height);

        System.out.println(result);
    }

    // 방법 1
    static int method1(int w, int[] height) {
        int result = 0;

        for(int i = 1; i < w-1; i++) {
            int leftMax = 0;
            int rightMax = 0;

            // 왼쪽
            for(int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // 오른쪽
            for(int j = i+1; j < w; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int minHeight = Math.min(leftMax, rightMax);
            if(minHeight > height[i]) result += minHeight - height[i];
        }

        return result;
    }

    // 방법 2
    static int method2(int w, int[] height) {
        int result = 0;

        int[] leftMaxDp = new int[w];
        int[] rightMaxDp = new int[w];

        // 왼쪽 최대 높이 계산
        leftMaxDp[0] = 0;
        for (int i = 1; i < w-1; i++) {
            leftMaxDp[i] = Math.max(leftMaxDp[i-1], height[i-1]);
        }

        // 오른쪽 최대 높이 계산
        rightMaxDp[w-1] = 0;
        for (int i = w-2; i > 0; i--) {
            rightMaxDp[i] = Math.max(rightMaxDp[i+1], height[i+1]);
        }

        // 각 위치의 물 높이 계산
        for (int i = 1; i < w-1; i++) {
            int minHeight = Math.min(leftMaxDp[i], rightMaxDp[i]);
            if (minHeight > height[i]) result += minHeight - height[i];
        }

        return result;
    }
}
