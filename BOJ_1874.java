import java.io.*;
import java.util.*;

public class BOJ_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int number = 1;
        for(int targetIdx = 0; targetIdx < n; targetIdx++) {
            int target = Integer.parseInt(br.readLine());

            while(target >= number) {
                stack.push(number);
                sb.append("+\n");
                number++;
            }

            if(stack.peek() == target) {
                stack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb);
    }
}
