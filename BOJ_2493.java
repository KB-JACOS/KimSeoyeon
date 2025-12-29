import java.io.*;
import java.util.*;

public class BOJ_2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] tops = new int[n];
        for(int i = 0; i < n; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            if (stack.isEmpty()) { // 스택이 비어있다면
                sb.append("0 ");
                stack.push(new int[] {i, tops[i]});
            } else {
                while (true) {
                    if (stack.isEmpty()) { // 스택이 비어있다면
                        sb.append("0 ");
                        stack.push(new int[] {i, tops[i]});
                        break;
                    }

                    if (stack.peek()[1] > tops[i]) { // peek한 탑의 높이가 현재 탑의 높이보다 높다면
                        sb.append(stack.peek()[0]+1 + " ");
                        stack.push(new int[] {i, tops[i]});
                        break;
                    } else { // peek한 탑의 높이가 현재 탑의 높이보다 낮다면
                        stack.pop();
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
