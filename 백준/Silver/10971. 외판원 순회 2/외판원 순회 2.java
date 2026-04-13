import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, result, fullMask;
    static int[][] map;
    static int[][] dp;

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        fullMask = (1 << N) - 1;
        dp = new int[fullMask + 1][N];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        // 출발 지점 초기화
        dp[1][0] = 0;

        // dp 계산
        for (int visited = 0; visited < (1 << N); visited++) {
            // 현재도시
            for (int city = 0; city < N; city++) {
                // 방문한 적도 없는데 현재 도시에 있는 게 말이 안됨
                if ((visited & 1 << city) == 0) continue;
                // 0부터 차근차근 방문여부를 기준으로 체크하는데 INF인 경우는 이전에 방문을 못했다는 얘기이므로 통과.
                if (dp[visited][city] == Integer.MAX_VALUE) continue;

                // 다음 도시
                for (int next = 0; next < N; next++) {
                    // 이미 방문했거나, 현재 도시 == 다음 도시이거나, 각 도시 간 연결이 없는 경우 통과
                    if ((visited & (1 << next)) != 0 || city == next || map[city][next] == 0) continue;

                    // 갈 수 있는 경우 최솟값 갱신
                    dp[visited | 1 << next][next] = Math.min(dp[visited | 1 << next][next],
                        dp[visited][city] + map[city][next]);
                }

            }
        }

        // 마지막 최솟값 갱신
        for (int i = 0; i < N; i++) {
            if (dp[fullMask][i] != Integer.MAX_VALUE && map[i][0] != 0) {
                result = Math.min(result, dp[fullMask][i] + map[i][0]);
            }
        }

        System.out.println(result);
    }
}