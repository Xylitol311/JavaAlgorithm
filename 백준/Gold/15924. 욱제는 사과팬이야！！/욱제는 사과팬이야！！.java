// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static char[][] field;
	static int[][] dp;
	static final int MOD = 1_000_000_009;

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		field = new char[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				field[i][j] = s.charAt(j);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solution());
	}

	private static int solution() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 1; j >= 0; j--) {
				// N-1, M-1이면 무조건 1
				if (i == N - 1 && j == M - 1) {
					dp[i][j] = 1;
					continue;
				}
				// E,S,B 확인
				switch (field[i][j]) {
				case 'E':
					if (j < M - 1) {
						dp[i][j] = (dp[i][j] + dp[i][j + 1]) % MOD;
					}
					break;
				case 'S':
					if (i < N - 1) {
						dp[i][j] = (dp[i][j] + dp[i + 1][j]) % MOD;
					}
					break;
				case 'B':
					if (i < N - 1) {
						dp[i][j] = (dp[i][j] + dp[i + 1][j]) % MOD;
					}
					if (j < M - 1) {
						dp[i][j] = (dp[i][j] + dp[i][j + 1]) % MOD;
					}
					break;
				}
			}
		}

		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result = (result + dp[i][j]) % MOD;
			}
		}
		return result;
	}
}
