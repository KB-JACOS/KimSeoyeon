import java.io.*;
import java.util.*;

public class BOJ_10800 {
    // 완탐 - 시간 초과
    /*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            int answer = 0;
            int curColor = arr[i][0], curSize = arr[i][1];
            for(int j = 0; j < n; j++) {
                if(i == j) continue;

                int color = arr[j][0], size = arr[j][1];

                if(curColor != color && curSize > size) answer += size;
            }
            System.out.println(answer);
        }
    }
    */

    // 시간 터짐
    /*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arrays = new int[n][2];
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            arrays[i][0] = color; arrays[i][1] = size;

            map.putIfAbsent(size, new ArrayList<>());
            map.get(size).add(color);
        }

        for(int[] arr : arrays) {
            int answer = 0;
            int curColor = arr[0], curSize = arr[1];

            for(int size : map.headMap(curSize).keySet()) {
                for(int color : map.get(size)) {
                    if(curColor != color) answer += size;
                }
            }

            System.out.println(answer);
        }
    }
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            arr[i][0] = color; arr[i][1] = size; arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> a[1] - b[1]); // size로 정렬

        int[] answer = new int[n];
        int[] colorSum = new int[200001];
        int totalSum = 0;

        answer[arr[0][2]] = 0;

        int idx = 0;
        for (int i = 1; i < n; i++) {
            int curColor = arr[i][0], curSize = arr[i][1];

            while (arr[idx][1] < curSize) {
                int color = arr[idx][0], size = arr[idx][1];
                totalSum += size;
                colorSum[color] += size;
                idx++;
            }

            answer[arr[i][2]] = totalSum - colorSum[curColor];
        }

        for(int ans : answer) {
            System.out.println(ans);
        }
    }
}
