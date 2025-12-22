import java.io.*;
import java.util.*;

public class BOJ_1016 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        Set<Long> set = new HashSet<>();
        for(long i = 2; i <= Math.sqrt(max); i++) {
            long num = i * i;
            for(long j = min / num; j <= max / num; j++) {
                if(num * j < min) continue;
                set.add(num*j);
            }
        }
//        System.out.println("set = " + set);
//        System.out.println("size = " + set.size());
        System.out.println(max-min+1-set.size());
    }
}
