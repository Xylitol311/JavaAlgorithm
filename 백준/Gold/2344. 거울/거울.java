// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;

    static {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, map[][];
    static int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 1) {
                    map[row][col] = -1; // 거울을 -1로 저장
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            map[i][0] = i; // 왼쪽 출구
            map[N + 1 - i][M + 1] = N + M + i; // 오른쪽 출구
        }

        for (int i = 1; i <= M; i++) {
            map[N + 1][i] = N + i; // 아래쪽 출구
            map[0][M + 1 - i] = N * 2 + M + i; // 위쪽 출구
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb);
    }

    private static void solution() {
        for (int i = 1; i <= N; i++) {
            sb.append(dfs(i, 0, 3)).append(" ");
        }
        for (int i = 1; i <= M; i++) {
            sb.append(dfs(N + 1, i, 0)).append(" ");
        }
        for (int i = N; i >= 1; i--) {
            sb.append(dfs(i, M + 1, 2)).append(" ");
        }
        for (int i = M; i >= 1; i--) {
            sb.append(dfs(0, i, 1)).append(" ");
        }
    }

    private static int dfs(int y, int x, int dir) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        if (map[ny][nx] >= 1) {
            return map[ny][nx];
        } else {
            if (map[ny][nx] == -1) {
                return dfs(ny, nx, changeDir(dir));
            } else {
                return dfs(ny, nx, dir);
            }
        }
    }

    private static int changeDir(int dir) {
        if (dir == 0) return 3; // 위 -> 오른쪽
        else if (dir == 1) return 2; // 아래 -> 왼쪽
        else if (dir == 2) return 1; // 왼쪽 -> 아래
        else return 0; // 오른쪽 -> 위
    }
}