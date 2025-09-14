import java.util.*;

class programmers_180577 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        // 첫 사이즈 기억
        int firstSize = queue1.length;

        long queue1Sum = 0;
        long queue2Sum = 0;

        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();


        for (int i = 0; i < firstSize; i++) {
            queue1Sum += queue1[i];
            queue2Sum += queue2[i];

            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        long target = queue1Sum + queue2Sum;

        if (target % 2 == 1) return -1;

        target /= 2;

        while (answer <= 3 * firstSize) {
            if (queue1Sum == target) return answer;

            if (queue1Sum < target) {
                if (q2.isEmpty()) return -1;
                int tmp = q2.poll();

                q1.add(tmp);
                queue1Sum += tmp;
                queue2Sum -= tmp;
            } else {
                if (q1.isEmpty()) return -1;
                int tmp = q1.poll();

                q2.add(tmp);
                queue1Sum -= tmp;
                queue2Sum += tmp;

            }

            answer++;
        }

        return -1;
    }
}
