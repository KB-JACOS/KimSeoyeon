import java.io.*;
import java.sql.Time;
import java.util.*;

public class BOJ_11000 {
    static class Time implements Comparable<Time> {
        int s;
        int t;

        Time(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Time other) {
//            if (this.s > other.s) return 1;
//            else if (this.s == other.s) {
//                if (this.t > other.t) return 1;
//            }
//            return -1;
            return Integer.compare(this.t, other.t);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Time> q = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            q.add(new Time(s, t));
        }

        // 맨 처음 가장 빨리 시작하는 강의 뽑기
        Time firstTime = q.poll();
        // 뽑은 강의가 끝나는 시간 담기
        Queue<Integer> result = new PriorityQueue<>();
        result.add(firstTime.t);

        for (int i = 1; i < N; i++) {
            Time time = q.poll();

            if (time.s < result.peek()) {
                result.add(time.t);
            } else {
                result.poll();
//                result.remove(0);
                result.add(time.t);
            }
        }
        System.out.println(result.size());

    }
}
