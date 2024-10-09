// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
현재 위치에서 세 가지 경우 중 큰 수를 dp에 저장한다.
N - 2 / N - 1 / N
1) O/X/O : dp[n-2] + arr[n]
2) X/O/O : dp[n-3] + arr[n-1] + arr[n]
3) O/O/X : dp[n-1] => 현재 값을 포함하지 않는 경우 이미 최댓값이 계산된 dp[n-1]을 사용
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int result;

    static int N, wines[], dp[];

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        wines = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N + 1];
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(dp[N]);
    }

    private static void solution() {
        dp[1] = wines[1];
        if (N > 1) {
            dp[2] = wines[1] + wines[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));
        }
    }
}