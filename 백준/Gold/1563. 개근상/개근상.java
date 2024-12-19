import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp를 이용해 해결
[필요 변수]
- dp[날짜][연속 결석 횟수][총 지각 횟수]

[dp 저장 방식]
각 날짜 i에 대해:
- 출석(O)하는 경우: 연속 결석은 0이 되고, 지각 횟수는 유지
- 지각(L)하는 경우: 연속 결석은 0이 되고, 지각 횟수는 1 증가
- 결석(A)하는 경우: 연속 결석은 1 증가하고, 지각 횟수는 유지
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, answer;
    static int dp[][][];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3][2]; // 연속 결석은 3번, 지각은 2번부터 개근상을 받지 못함.

        // 초깃값 입력
        dp[0][0][0] = 1; // 첫날 출석
        dp[0][1][0] = 1; // 첫날 결석
        dp[0][0][1] = 1; // 첫날 지각

        solution();

        System.out.println(answer);
    }

    private static void solution() {
        // dp 입력
        for (int i = 1; i < N; i++) {
            for (int absence = 0; absence < 3; absence++) {
                for (int late = 0; late < 2; late++) {
                    // 정상 출석
                    dp[i][0][late] += dp[i - 1][absence][late];
                    dp[i][0][late] %= 1_000_000;

                    // 결석
                    if (absence + 1 < 3) {
                        dp[i][absence + 1][late] += dp[i - 1][absence][late];
                        dp[i][absence + 1][late] %= 1_000_000;
                    }

                    // 지각
                    if (late + 1 < 2) {
                        dp[i][0][late + 1] += dp[i - 1][absence][late];
                        dp[i][0][late + 1] %= 1_000_000;
                    }
                }
            }
        }

        // 정답 계산
        answer = 0;
        for (int absence = 0; absence < 3; absence++) {
            for (int late = 0; late < 2; late++) {
                answer += dp[N - 1][absence][late];
                answer %= 1_000_000;
            }
        }
    }
}