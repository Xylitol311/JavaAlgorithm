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
            field[i] = br.readLine().toCharArray();
        }
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solution());
	}

	private static int solution() {
		int[][] dp = new int[N][M];
        dp[N-1][M-1] = 1;  // 도착점

        // 거꾸로 돌면서 각 칸에서의 경로 수를 채워나감
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (i == N - 1 && j == M - 1) continue;
                char c = field[i][j];
                // 오른쪽 이동 (E 또는 B)
                if (c != 'S' && j + 1 < M) {
                    dp[i][j] = (dp[i][j] + dp[i][j + 1]) % MOD;
                }
                // 아래 이동 (S 또는 B)
                if (c != 'E' && i + 1 < N) {
                    dp[i][j] = (dp[i][j] + dp[i + 1][j]) % MOD;
                }
            }
        }

        // 모든 시작 지점에서의 경로 합산
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = (result + dp[i][j]) % MOD;
            }
        }
        return result;
	}
}
