import java.io.*;
import java.util.*;

public class BOJ_20191 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        Map<Character, List<Integer>> map = new HashMap<>();

        // t 저장
        for (int i = 0; i < t.length(); i++) {
            Character word = t.charAt(i);

            map.putIfAbsent(word, new ArrayList<>());
            map.get(word).add(i);
        }

//        // 확인용 출력
//        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " -> " + entry.getValue());
//        }

        int result = 1;
        int curIdx = -1; // 전역으로 현재 위치 저장
        for(int i = 0; i < s.length(); i++) {
            Character target = s.charAt(i);

            // 단어가 없는 경우
            if (!map.containsKey(target)) {
                System.out.println(-1);
                return;
            }

            List<Integer> targetIdx = map.get(target);
            System.out.println("target 단어: " + target + ", 해당 단어의 위치 인덱스: " + targetIdx + ", 지금 내 위치: " + curIdx);

            // 이분 탐색으로 curIdx 업데이트
            int left = 0;
            int right = targetIdx.size();
            while(left < right) {
                int mid = (left + right) / 2;
                if(targetIdx.get(mid) > curIdx) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int pos = left;

            if (pos == targetIdx.size()) {
                result++;
                curIdx = targetIdx.get(0);
            } else {
                curIdx = targetIdx.get(pos);
            }
        }

        System.out.println(result);
    }
}
