import java.io.*;
import java.util.*;

public class BOJ_12933 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] words = br.readLine().split("");
//        System.out.println("words = " + Arrays.toString(words));

        int answer = 0;

        List<Stack<String>> stackList = new ArrayList<>();

        for(String word : words) {
            if(word.equals("q")) {
                boolean chk = false;
                for(Stack<String> stack : stackList) {
                    if(stack.peek().equals("k")) {
                        stack.add(word);
                        chk = true;
                        break;
                    }
                }
                if(!chk) {
                    Stack<String> s = new Stack<>();
                    s.add(word);
                    stackList.add(s);
                    answer++;
                }
                continue;
            }

            boolean chk2 = false;
            for(Stack<String> stack : stackList) {
                if(stack.peek().equals("q") && word.equals("u")) {
                    stack.add(word);
                    chk2 = true;
                    break;
                } else if(stack.peek().equals("u") && word.equals("a")) {
                    stack.add(word);
                    chk2 = true;
                    break;
                } else if(stack.peek().equals("a") && word.equals("c")) {
                    stack.add(word);
                    chk2 = true;
                    break;
                } else if(stack.peek().equals("c") && word.equals("k")) {
                    stack.add(word);
                    chk2 = true;
                    break;
                } else if(stack.peek().equals("k") && word.equals("q")) {
                    stack.add(word);
                    chk2 = true;
                    break;
                }
            }

            if(!chk2) {
                System.out.println(-1);
                System.exit(0);
            }

        }

        for(Stack<String> stack : stackList) {
            if(stack.size() % 5 != 0) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(answer);

    }
}
