import java.io.*;
import java.util.*;

public class BOJ_2023 {
    static StringBuilder sb;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        dfs(1, "2");
        dfs(1, "3");
        dfs(1, "5");
        dfs(1, "7");

        System.out.println(sb);
    }

    static void dfs(int jarisu, String number) {
        if(jarisu == n) {
            if(isPrime(Integer.parseInt(number))) {
                sb.append(number).append("\n");
            }
            return;
        }

        for(int i = 1; i <= 9; i+=2) {
            number += String.valueOf(i);
            if(isPrime(Integer.parseInt(number))) {
                dfs(jarisu+1, number);
            }

            number = number.substring(0, number.length()-1);
        }
    }
    
    static boolean isPrime(int num) {
        if(num == 1) return false;

        for(int div = 2; div <= Math.sqrt(num); div++) {
            if(num % div == 0) return false;
        }
        return true;
    }
}
