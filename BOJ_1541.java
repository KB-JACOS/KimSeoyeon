import java.io.*;
import java.util.*;

public class BOJ_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split("-");
//        System.out.println(Arrays.toString(inputs));

        int[] result = new int[inputs.length];
        for(int i = 0; i < inputs.length; i++) {

            if (inputs[i].contains("+")) {
                StringTokenizer st = new StringTokenizer(inputs[i], "+"); // \\+

                int tmp = 0;
                while(st.hasMoreTokens()) {
                    tmp += Integer.parseInt(st.nextToken());
                }

                result[i] = tmp;
            } else {
                result[i] = Integer.parseInt(inputs[i]);
            }
        }

        int answer = result[0];
        for (int i = 1; i < result.length; i++) {
            answer -= result[i];
        }

        System.out.println(answer);
    }
}
