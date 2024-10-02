import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] dp;
	static final int mod = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		// 양수면 1
		if (N > 0) {
			System.out.println(1);

		// 음수인 경우 짝수면 -1, 홀수면 1.
		// 음수의 경우 양수 피보나치 수열과 같지만 짝수일 경우 음수이다.
		} else if (N < 0) {
			N = -N;
			if (N % 2 == 0) {
				System.out.println(-1);
			} else {
				System.out.println(1);
			}

		} else {
			System.out.println(0);
		}

		// 피보나치 수열 저장 (bottom-up)
		dp = new int[1_000_001];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i < N + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
		}

		System.out.println(dp[N]);
	}
}