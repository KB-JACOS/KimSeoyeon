import java.io.*;
import java.util.*;

public class BOJ_2217 {

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer> ropes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ropes.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(ropes);

        br.close();

        int result = 0;
        for (int j = 0; j < N; j++) {
            int tmpResult = ropes.get(j)*(N-j);

            if (tmpResult > result) result = tmpResult;
        }
        System.out.println(result);
    }
}