import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static final int INF = 1_000_001;
	static int size, dp[][];

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());

		size = Integer.parseInt(st.nextToken());
		dp = new int[size + 1][size + 1];

		for (int row = 1; row <= size; row++) {
			for (int col = 1; col <= size; col++) {
				int num = Integer.parseInt(st.nextToken());
				if (row == col) {
					dp[row][col] = 0;
				} else if (num == 1) {
					dp[row][col] = 1;
				} else
					dp[row][col] = INF;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			sb.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.println(sb);
	}

	private static int solution() {
		for (int mid = 1; mid < dp.length; mid++) {
			for (int row = 1; row < dp.length; row++) {
				for (int col = 1; col < dp.length; col++) {
					if (dp[row][col] > dp[row][mid] + dp[mid][col]) {
						dp[row][col] = dp[row][mid] + dp[mid][col];
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int row = 1; row < dp.length; row++) {
			int sum = 0;
			for (int col = 1; col < dp.length; col++) {
				sum += dp[row][col];
			}
			min = Math.min(min, sum);
		}
		return min;
	}
}