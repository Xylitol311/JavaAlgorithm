import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
<규칙으로 풀이>
남은 돌이 2와 7일 때는 무조건 상근이가 패배.
이 규칙이 7개마다 반복되므로 N % 7이 0 또는 2이면 상근이의 패배다.

<dp로 풀이>
i개의 돌이 남았을 때 상근이의 차례여야 이김.
돌은 1,3,4개를 뽑을 수 있으므로 i번째에서 -1, -3, -4번째 중 창영이 차례가 있다면 상근이가 이긴다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
//        if (N % 7 == 0 || N % 7 == 2) {
//            System.out.println("CY");
//        } else
//            System.out.println("SK");

        int[] dp = new int[1001];
        dp[1] = dp[3] = dp[4] = dp[5] = 1;
        for (int i = 6; i <= N; i++) {
            if (dp[i - 1] == 0 || dp[i - 3] == 0 || dp[i - 4] == 0) {
                dp[i] = 1;
            } else {
                dp[i] = 0;
            }
        }
        System.out.println(dp[N] == 1 ? "SK" : "CY");
    }
}