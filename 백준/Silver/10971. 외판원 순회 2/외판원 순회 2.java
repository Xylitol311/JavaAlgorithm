import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, result;
    static int[][] map;

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
    }

    public static void main(String[] args) throws Exception {
        input();
        dfs(0, 0, 0, 1);
        System.out.println(result);
    }

    private static void dfs(int startCity, int nowCity, int sum, int isVisit) {
        // 현재까지의 누적 비용이 이전 최저 비용보다 클 경우 취소(더 볼 필요 없음)
        if (sum > result) return;

        // 전체 다 방문?
        if (isVisit == (1 << N) - 1) {
            // 돌아갈 수 있다면
            if (map[nowCity][startCity] != 0) {
                sum += map[nowCity][startCity];
                result = Math.min(result, sum);
            }
            return;
        }

        // 다음 도시 방문
        for (int i = 0; i < N; i++) {
            // 아직 방문하지 않은 곳만
            if ((isVisit & 1 << i) != 0 || map[nowCity][i] == 0) continue;
            dfs(startCity, i, sum + map[nowCity][i], isVisit | 1 << i);
        }
    }
}