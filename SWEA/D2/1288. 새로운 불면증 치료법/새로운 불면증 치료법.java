// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int result, answer;
	static long n, cnt;

	private static void input() throws IOException {
		answer = 1023; // 비트마스킹을 이용해 0~9까지 1로 표시한 값

		result = 0;

		n = Integer.parseInt(br.readLine());
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			input();
			solution();

			sb.append("#").append(i).append(" ").append(cnt * n).append("\n");

		}
		System.out.println(sb);
	}

	private static void solution() {
		cnt = 1;
		while (true) {
			long now = cnt * n;
			
			do {
				result |= 1 << now % 10;
				now /= 10;
			} while (now != 0);
			
			if(result == answer) break;
			cnt++;
		}
	}
}