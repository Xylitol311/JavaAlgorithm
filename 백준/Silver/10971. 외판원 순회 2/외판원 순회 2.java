// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
dfs를 통해 모든 도시를 탐색하고 마지막 도시에서 시작 지점으로 돌아오는 경로의 가중치를 더한다.
모든 경로마다 더해진 가중치 중 최솟값을 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, result, city[][];
    static boolean[] visited;

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        city = new int[N][N];
        visited = new boolean[N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        // solution
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            solve(i, i, 0, 0);
            visited[i] = false;
        }

        // print
        System.out.println(result);
    }

    private static void solve(int start, int now, int depth, int sum) {
        if (depth == N - 1) {
            // 마지막 도시에서 처음으로 갈 수 있다면 그 경로를 추가해서 최솟값 갱신
            if (city[now][start] != 0) {
                sum += city[now][start];
                result = Math.min(result, sum);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            // 방문하지 않은 도시이고 현재 도시에서 갈 수 있는 경로가 있는 경우
            if (!visited[i] && city[now][i] > 0) {
                visited[i] = true;
                solve(start, i, depth + 1, sum + city[now][i]);
                visited[i] = false;
            }
        }
    }
}