import java.io.*;
import java.util.*;

public class BOJ_1074 {
    static int result = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());


        System.out.println(check(r, c));
    }

    static int check(int r, int c) {
        if (r == 0 && c == 0) return result;

        if (r % 2 == 0 && c % 2 == 0) {
            r /= 2;
            c /= 2;
            return check(r, c) * 4;
        }
        else if (r % 2 == 1 && c % 2 == 1) {
            r -= 1;
            c -= 1;
            return check(r, c) + 3;
        }
        else if (r % 2 == 1 && c % 2 == 0) {
            r -= 1;
            return check(r, c) + 2;
        }
        else if (r % 2 == 0 && c % 2 == 1) {
            c -= 1;
            return check(r, c) + 1;
        }
        return 0;
    }

}
