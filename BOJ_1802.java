import java.io.*;
import java.util.*;

public class BOJ_1802 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        boolean isEqual;

        /*
        // 방법 1
        for (int i = 0; i < T; i++) {
            isEqual = true;
            String str = br.readLine();
            String[] strArray = str.split("");

            int end = strArray.length;

            while (end > 2) {
                int mid = end / 2; // 중간 인덱스 값 찾기

                String[] leftArray = Arrays.copyOfRange(strArray, 0, mid);
                String[] rightArray = Arrays.copyOfRange(strArray, mid+1, end);

                if (!check(leftArray, rightArray)) {
                    isEqual = false;
                    break;
                }
                end = mid;
            }

            System.out.println(isEqual? "YES":"NO");
        }
        */

        for(int i = 0; i < T; i++) {
            isEqual = true;

            String str = br.readLine();
            int len = str.length();

            int[] input = new int[len];

            for (int j = 0; j < len; j++) {
                input[j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }

            while (isEqual && len > 1) {
                for (int j = 0; j < len / 2; j++) {
                    if (input[j] + input[len - 1 - j] != 1) {
                        isEqual = false;
                        break;
                    }
                }
                len /= 2;
            }
            System.out.println(isEqual ? "YES" : "NO");
        }
    }

    // 비교
    private static boolean check(String[] leftArray, String[] rightArray) {
        for (int i = 0; i < leftArray.length; i++) {
            if (leftArray[i].equals("0")) {
                leftArray[i] = "1";
            } else {
                leftArray[i] = "0";
            }
        }
        List<String> leftList = Arrays.asList(leftArray);
        Collections.reverse(leftList);
        leftList.toArray(leftArray);

        return Arrays.equals(leftArray, rightArray);
    }
}
