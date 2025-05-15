import java.io.*;
import java.util.*;

public class BOJ_2800 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] word = br.readLine().toCharArray();

        List<int[]> pairs = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < word.length; i++) {
            if (word[i] == '(') stack.push(i);
            else if (word[i] == ')') pairs.add(new int[]{stack.pop(), i});
        }

        Set<String> result = new TreeSet<>();

        for (int bit = 1; bit < (1 << pairs.size()); bit++) {
            boolean[] isRemove = new boolean[word.length];

            for (int i = 0; i < pairs.size(); i++) {
                if ((bit & (1 << i)) != 0) {
                    int[] idx = pairs.get(i);

                    isRemove[idx[0]] = true;
                    isRemove[idx[1]] = true;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < isRemove.length; i++) {
                if (!isRemove[i]) {
                    sb.append(word[i]);
                }
            }
            result.add(sb.toString());
        }
        for (String s : result) {
            System.out.println(s);
        }
    }
}
