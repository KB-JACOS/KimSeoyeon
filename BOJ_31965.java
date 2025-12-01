import java.io.*;
import java.util.*;

public class BOJ_31965 {
    static int n, q;
    static long[] map, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new long[n+1];
        sum = new long[n+1];
        for(int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            map[i] = num;
            sum[i] = sum[i-1] + num;
        }
        // System.out.println("누적합니다: " + sum);

        int start, end;
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

//            int left = upper_bound(start); // 시작하는 첫 집을 구합니다
//            int right = lower_bound(end); // 끝나는 마지막 집을 구합니다

            int left = Arrays.binarySearch(map, start);
            left = left < 0 ? -left-1 : left;

            int right = Arrays.binarySearch(map, end);
            right = right < 0 ? -right-2 : right;

            if(left >= right || left == -1 || right == -1) {
                sb.append("0");
            } else {
                // 최대 회의 비용
                long maxCost = Math.max(getCost(left, left, right), getCost(right, left, right));
                // 최소 회의 비용
                long minCost = getCost((left+right)/2, left, right);

                sb.append(maxCost - minCost).append("\n");

            }
        }
        System.out.println(sb);
    }

    // 시작하는 첫 집을 구하기 위해서는 start보다 큰 가장 작은 수를 구해야 합니다
    static int upper_bound(int target) {
        int s = 1, e = n;
        int pre = map[e] >= target ? e : -1;

        while (s <= e) {
            int mid = (s+e) / 2;

            if(map[mid] == target) return mid;

            if(map[mid] > target) {
                e = mid - 1;
                pre = mid;
            } else {
                s = mid + 1;
            }
        }
        return pre;
    }

    // 끝나는 마지막 집을 구하기 위해서는 end보다 작은 가장 큰 수를 구해야 합니다
    static int lower_bound(int target) {
        int s = 1, e = n;
        int pre = map[0] <= target ? 0 : -1;

        while (s <= e) {
            int mid = (s+e) / 2;

            if(map[mid] == target) return mid;

            if(map[mid] < target) {
                s = mid + 1;
                pre = mid;
            } else {
                e = mid - 1;
            }
        }
        return pre;
    }

    static long getCost(int target, int l, int r) {
        long right = (sum[r] - sum[target-1]) - (long)(map[target]*(r-target+1));
        long left = (long)(map[target] * (target-l+1)) - (sum[target] - sum[l-1]);
        return left + right;
    }
}

/*
import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static long[] nums, sums;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		nums = new long[n + 1];
		sums = new long[n + 1]; // 누적합

		st = new StringTokenizer(bf.readLine());

		for (int i = 1; i <= n; i++) {
			long num = Long.parseLong(st.nextToken());
			nums[i] = num;
			sums[i] = sums[i - 1] + num;
		}

		while (q-- > 0) {
			st = new StringTokenizer(bf.readLine());
			long l = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());

			// 좌표의 범위를 구한다.
			int L = Arrays.binarySearch(nums, l);
			L = L < 0 ? -L-1 : L;

			int R = Arrays.binarySearch(nums, r);
			R = R < 0 ? -R-2 : R;

			if (L >= R) {
				sb.append("0\n");
				continue;
			} else {
				// 최대 회의 비용 => 감소/증가 방향 둘 중 한곳에서 최대 회의 비용이 발생한다.
				long maxCost = Math.max(getCost(L, L, R), getCost(R, L, R));

				// 최소 회의 비용 => 중간값에서 최소 회의 비용이 발생한다.
				long minCost = getCost((L + R) / 2, L, R);

				sb.append(maxCost - minCost).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static long getCost(int target, int l, int r) {
		// 우측 / 좌측을 나눠서 생각해야하는 이유 : 절대값이기 때문에

		long right = (sums[r] - sums[target - 1]) - (long) (nums[target] * (r - target + 1));
		long left = (long) (nums[target] * (target - l + 1)) - (sums[target] - sums[l - 1]);

		return right + left;
	}
}
*/