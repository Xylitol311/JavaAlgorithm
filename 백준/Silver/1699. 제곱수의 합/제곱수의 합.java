// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp를 이용
dp[i]는 i 숫자를 만들 수 있는 제곱수의 최소 개수
1, 4, 9, 16, 25,... 등의 숫자들은 제곱수의 개수가 1
dp[i]에서 dp[1],dp[4],...를 뺴고 나머지 dp[i- 제곱수가 1인 애들] 중 최솟값에 1을 더하면 dp[i]의 최솟값을 구할 수 있다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int dp[];

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}