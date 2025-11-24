import java.io.*;
import java.util.*;

public class BOJ_16496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = st.nextToken();
        }
//        String[] arr = br.readLine().split(" ");
        System.out.println("arr = " + Arrays.toString(arr));

//        Arrays.sort(arr, (a, b) -> a.compareTo(b)); // 오름차순
//        Arrays.sort(arr, (a, b) -> b.compareTo(a)); // 내림차순

        Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));

        System.out.println("arr = " + Arrays.toString(arr));
        for(int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }

        String result = sb.toString();
        if(result.charAt(0) == '0') {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}