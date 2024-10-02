import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int numArr[];

	private static void input() throws IOException {
		int n = Integer.parseInt(br.readLine().trim());
		numArr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < numArr.length; idx++) {
			numArr[idx] = Integer.parseInt(st.nextToken());
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			sb.append("#").append(tc).append(" ").append(numList.size()).append("\n");
		}
		System.out.println(sb);
	}

	static List<Integer> numList;

	private static void solution() {
		numList = new ArrayList<>();

		for (int num : numArr) {
			// 0. 첫번째 값은 바로 입력
			if (numList.size() == 0) {
				numList.add(num);
				continue;
			}

			// 1. 추가할 값을 마지막 숫자와 비교해서 같거나 크면 마지막에 추가
			if (num >= numList.get(numList.size() - 1)) {
				numList.add(num);
				continue;
			}

			// 2. 첫번째와 비교해서 작으면 첫번째값을 교체
			if (num < numList.get(0)) {
				numList.set(0, num);
				continue;
			}

			// 3. 이분 탐색을 이용해 추가할 숫자보다 처음으로 큰 값의 위치를 찾아 값 변경
			int idx = binarySearch(num);
			numList.set(idx, num);
		}

	}

	// 이분 탐색 : upper bound
	// 탐색값보다 처음으로 큰 값의 위치 찾기.
	private static int binarySearch(int num) {
		int left = 0;
		int right = numList.size() - 1;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (numList.get(mid) <= num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return right;
	}
}