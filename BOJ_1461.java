import java.io.*;
import java.util.*;

public class BOJ_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        Collections.sort(negative);
        Collections.sort(positive, Collections.reverseOrder());

        int restDist = 0;

        int negativeMax = 0;
        if (!negative.isEmpty()) {
            negativeMax = Math.abs(negative.get(0));
            for (int i = 0; i < negative.size(); i += m) {
                restDist += Math.abs(negative.get(i));
            }
        }

        int positiveMax = 0;
        if (!positive.isEmpty()) {
            positiveMax = positive.get(0);
            for (int i = 0; i < positive.size(); i += m) {
                restDist += positive.get(i);
            }
        }

        int result = Math.max(Math.abs(negativeMax), positiveMax)
                + 2 * (restDist - Math.max(Math.abs(negativeMax), positiveMax));
        System.out.println(result);

    }
}
