import java.io.*;
import java.util.*;

public class BOJ_2632 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] aArr = new int[2*a]; int[] bArr = new int[2*b];

        for(int i = 0; i < a; i++) {
            aArr[i] = Integer.parseInt(br.readLine());
            aArr[a+i] = aArr[i];
        }
        for(int i = 0; i < b; i++) {
            bArr[i] = Integer.parseInt(br.readLine());
            bArr[b+i] = bArr[i];
        }

        int[] aSum = new int[n+1];
        aSum[0] = 1;

        int[] bSum = new int[n+1];
        bSum[0] = 1;

        // 방법 1
        /*
        int aTotal = 0;
        for(int i = 0; i < a; i++) aTotal += aArr[i];
        if(aTotal <= n) aSum[aTotal]++;

        for(int i = 0; i < a; i++) {
            int sum = 0;
            // 길이 1 ~ a-1 까지만
            for(int len = 1; len < a; len++) {
                sum += aArr[i + len - 1];
                if(sum <= n) aSum[sum]++;
                else break;
            }
        }

        int bTotal = 0;
        for(int i = 0; i < b; i++) bTotal += bArr[i];
        if(bTotal <= n) bSum[bTotal]++;

        for(int i = 0; i < b; i++) {
            int sum = 0;
            // 길이 1 ~ b-1 까지만
            for(int len = 1; len < b; len++) {
                sum += bArr[i + len - 1];
                if(sum <= n) bSum[sum]++;
                else break;
            }
        }
        */

        // 방법 2 -> 누적합
        int[] prefixA = new int[2*a+1];
        for (int i = 1; i <= 2*a; i++) {
            prefixA[i] = prefixA[i-1] + aArr[i-1];
        }

        int[] prefixB = new int[2*b+1];
        for (int i = 1; i <= 2*b; i++) {
            prefixB[i] = prefixB[i-1] + bArr[i-1];
        }

        int aTotal = prefixA[a];
        if(aTotal <= n) aSum[aTotal]++;
        int bTotal = prefixB[b];
        if(bTotal <= n) bSum[bTotal]++;

        // A 부분합 (길이 1 ~ a-1)
        for (int len = 1; len < a; len++) {
            for (int start = 0; start < a; start++) {
                int sum = prefixA[start + len] - prefixA[start];
                if (sum <= n) aSum[sum]++;
            }
        }

        // B 부분합 (길이 1 ~ b-1)
        for (int len = 1; len < b; len++) {
            for (int start = 0; start < b; start++) {
                int sum = prefixB[start + len] - prefixB[start];
                if (sum <= n) bSum[sum]++;
            }
        }

//        System.out.println("aSum = " + Arrays.toString(aSum));
//        System.out.println("bSum = " + Arrays.toString(bSum));

        int res = 0;
        for(int i = 0; i <= n; i++) {
            res += aSum[i] * bSum[n-i];
//            System.out.printf("a = %d b = %d res = %d\n", i, n-i, res);
        }
        System.out.println(res);
    }
}
