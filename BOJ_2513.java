import java.io.*;
import java.util.*;

public class BOJ_2513 {
    static int k;
    static Queue<int[]> left;
    static Queue<int[]> right;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 아파트 단지 수
        k = Integer.parseInt(st.nextToken()); // 버스 정원
        int s = Integer.parseInt(st.nextToken()); // 학교 위치

        left = new PriorityQueue<>((a, b) -> b[0] - a[0]); // 학교 왼쪽에 있는 집
        right = new PriorityQueue<>((a, b) -> b[0] - a[0]); // 학교 오른쪽에 있는 집

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken()); // 아파트 위치
            int student = Integer.parseInt(st.nextToken()); // 학생 수

            if(p < s) { // 학교 왼쪽에 집이 있다면
                left.add(new int[] {s-p, student});
            } else if(p > s) { // 학교 오른쪽에 집이 있다면
                right.add(new int[] {p-s, student});
            }
        }

        int answer = 0;
        answer += cal(left);
        answer += cal(right);

        System.out.println(answer);
    }

    public static int cal(Queue<int[]> q) {
        int res = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int dist = cur[0];
            int student = cur[1];

            while (student > 0) {
                res += dist * 2;
                int cap = k;

                if (student >= cap) {
                    student -= cap;
                    continue;
                } else {
                    cap -= student;
                    student = 0;
                }

                while (cap > 0 && !q.isEmpty()) {
                    int[] next = q.poll();
                    if (next[1] <= cap) {
                        cap -= next[1];
                    } else {
                        next[1] -= cap;
                        cap = 0;
                        q.add(next);
                    }
                }
            }
        }
        return res;
    }
}
