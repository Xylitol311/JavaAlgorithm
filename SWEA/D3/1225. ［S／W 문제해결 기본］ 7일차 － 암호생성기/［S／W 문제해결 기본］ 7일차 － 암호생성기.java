import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static Queue<Integer> answer;

	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			input();
			solution();
			print(tc);
		}
		System.out.println(sb);
	}

	private static void solution() {
		int lastNum = Integer.MAX_VALUE;
		while (true) {
			for (int num = 1; num <= 5; num++) {
				lastNum = Math.max(answer.poll() - num, 0);
				answer.add(lastNum);
				if (lastNum == 0)
					return;
			}
		}
	}

	private static void input() throws IOException {
		answer = new LinkedList<>();
		br.readLine();
		st = new StringTokenizer(br.readLine());

		// 총 8자리 비밀번호 입력
		for (int idx = 0; idx < 8; idx++) {
			answer.add(Integer.parseInt(st.nextToken()));
		}
	}

	private static void print(int tc) {
		sb.append("#").append(tc);
		while (!answer.isEmpty()) {
			sb.append(" ").append(answer.poll());
		}
		sb.append("\n");
	}
}
