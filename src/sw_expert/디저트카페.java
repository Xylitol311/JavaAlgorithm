package sw_expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
임의의 한 카페에서 출발해서 대각선 방향으로 서로 다른 디저트만 먹으면서
사각형 모양을 그리며 다시 출발점으로 돌아오는 경우, 디저트를 가장 많이 먹을 수 있는 경로를 찾고,
그때 디저트 수를 정답으로 출력하는 문제

<전략>
1. 사각형이 만들어질 수 있는 최소한의 공간을 확보해주는 작업
2. 먹은 디저트와 그 위치에 대한 방문 처리
3. dfs를 활용해서 이전 방향의 다음 방향만 탐색하도록 구현
4. 방향 전환을 3번 이상해서 자신에게 돌아온 경우 종료하고 answer(max값) 갱신
 */
public class 디저트카페 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int mapSize;
    static int[][] map;

    // 우하 좌하 좌상 우상
    static int[] dr = { 1, 1, -1, -1 };
    static int[] dc = { 1, -1, -1, 1 };

    static boolean[][] visit; // 지도 방문 처리
    static boolean[] dessert; // 먹은 디저트 방문 처리
    static int answer = 0;

    private static void input() throws IOException{
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize][mapSize];

        // 정답 초기화
        answer = 0;

        for (int i = 0; i < mapSize; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            input();
            solution();
            print(tc);
        }
        System.out.println(sb);
    }

    private static void solution() {
        // 시작점의 양옆과 밑으로 최소 2칸 여유가 있어야 사각형을 만들 수 있음
        for (int row = 0; row < mapSize - 2; row++) {
            for (int col = 1; col < mapSize - 1; col++) {
                // 방문 체크용 변수 초기화
                visit = new boolean[mapSize][mapSize];
                dessert = new boolean[101];

                // 시작점 체크
                visit[row][col] = true;
                dessert[map[row][col]] = true;

                // dfs 호출
                dfs(1, row, col, row, col, 0);
            }
        }

        if (answer == 0)
            answer = -1;
    }

    private static void dfs(int cnt, int row, int col, int startRow, int startCol, int prevDelta) {

        // prevDelta : 이전 방향을 받아서 그 방향과 같거나 다음 방향으로만 이동.
        for (int delta = prevDelta; delta < 4; delta++) {
            int nr = row + dr[delta];
            int nc = col + dc[delta];

            // 범위 안에 있는지 체크
            if (nr >= 0 && nr < mapSize && nc >= 0 && nc < mapSize) {
                // 3번 이상 탐색하고 시작점으로 돌아온 경우. 투어에 성공했으므로 answer 갱신
                if ((nr == startRow) && (nc == startCol) && cnt > 2) {
                    answer = Math.max(answer, cnt);
                    return;
                    // 종료
                }

                // 다음 위치가 좌표와 디저트 모두 방문한 적 없는 경우
                // 방문 처리 후 dfs 호출 및 후처리
                if (!visit[nr][nc] && !dessert[map[nr][nc]]) {
                    visit[nr][nc] = true;
                    dessert[map[nr][nc]] = true;
                    dfs(cnt + 1, nr, nc, startRow, startCol, delta);
                    visit[nr][nc] = false;
                    dessert[map[nr][nc]] = false;
                }
            }
        }
    }

    private static void print(int tc) {
        sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }
}
