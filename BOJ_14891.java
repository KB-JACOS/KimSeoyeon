import java.io.*;
import java.util.*;

public class BOJ_14891 {

    static int[][] wheels = new int[4][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 4; i++) {
            String s = br.readLine();
            for(int j = 0; j < 8; j++) {
                wheels[i][j] = s.charAt(j) - '0'; // 1 S, 0 N
            }
        }

        int k = Integer.parseInt(br.readLine());
        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken())-1;
            int direction = Integer.parseInt(st.nextToken()); // 1 시계, -1 반시계

            int[] rotate = new int[4]; // 회전 정보를 담음
            rotate[number] = direction;

            // 왼쪽
            for(int i = number; i > 0; i--) {
                if(wheels[i][6] != wheels[i-1][2]) {
                    rotate[i-1] = -rotate[i];
                } else  break;
            }

            // 오른쪽
            for(int i = number; i < 3; i++) {
                if(wheels[i][2] != wheels[i+1][6]) {
                    rotate[i+1] = -rotate[i];
                } else break;
            }

            for (int i = 0; i < 4; i++) {
                if (rotate[i] == 1) right(i);
                else if (rotate[i] == -1) left(i);
            }
        }

        int answer = wheels[0][0] + (wheels[1][0]*2) + (wheels[2][0]*4) + (wheels[3][0]*8);
        System.out.println(answer);
    }

    // 반시계 이동
    public static void left(int idx) {
        int[] copy = new int[8];
        for(int i = 0; i < 7; i++) {
            copy[i] = wheels[idx][i+1];
        }
        copy[7] = wheels[idx][0];

        wheels[idx] = copy;
    }

    // 시계 이동
    public static void right(int idx) {

        int[] copy = new int[8];
        for(int i = 1; i < 8; i++) {
            copy[i] = wheels[idx][i-1];
        }
        copy[0] = wheels[idx][7];

        wheels[idx] = copy;
    }
}
