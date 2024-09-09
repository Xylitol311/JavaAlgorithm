package baekjoon.study8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 청소되지 않은 칸이면 청소
2. 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
    1. 바라보는 방향을 유지한 채로 한 칸 후진 가능하면 후진 후 1번으로 돌아감
    2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진 불가능하면 작동 종료
3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
    1. 반시계 방향으로 90도 회전한다.
    2. 바라보는 방향 기준 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
    3. 1번으로 돌아감
청소한 위치는 -1로 표시. 0은 청소되지 않은 빈 칸, 1은 벽.

<헷갈리는 점>
앞으로 가면서 청소를 하다가 더 이상 청소를 할 수 없는 경우 이전으로 되돌아가서 다른 방향을탐색하는 것이
아니라 뒤로 가면서 더 이상 청소할 곳도 없고 후진을 할 수 없을 때까지 새로운 재귀를 타는 형태인 점.
 */
public class 숨바꼭질 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int maxRow, maxCol, count;
    static int map[][];
    static int nowR, nowC, direction;

    //상, 우, 하, 좌
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        map = new int[maxRow][maxCol];

        st = new StringTokenizer(br.readLine().trim());
        nowR = Integer.parseInt(st.nextToken());
        nowC = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken()); // 방향 0북 , 1동, 2남, 3서

        count = 1; // 기본적으로 시작 위치에서 한 칸을 청소하면서 시작

        for (int i = 0; i < maxRow; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < maxCol; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        dfs(nowR, nowC, direction);
        System.out.println(count);
    }

    private static void dfs(int row, int col, int direction) {
        //현재 위치 청소
        map[row][col] = -1;

        // 현재 위치에서 현 방향 기준으로 시계 반대방향으로 탐색
        for (int i = 0; i < 4; i++) {
            direction = (direction + 3) % 4; // 왼쪽 방향으로 변환 (북 -> 서 -> 남 -> 동)
            int nr = row + dy[direction];
            int nc = col + dx[direction];

            // 배열 안쪽에 위치하며 청소가 안 된 빈칸이 존재하는 경우
            if (nr >= 0 && nr < maxRow && nc >= 0 && nc < maxCol && map[nr][nc] == 0) {
                count++;
                dfs(nr, nc, direction); // 그 위치로 이동하여 재귀 반복
                return;
            }
        }
        // 모든 방향이 청소가 불필요 혹은 불가능한 경우
        // 뒤쪽이 벽이 아닌 경우에만 뒤로 후진
        int back = (direction + 2) % 4;
        int backR = row + dy[back];
        int backC = col + dx[back];
        if (backR >= 0 && backR < maxRow && backC >= 0 && backC < maxCol && map[backR][backC] != 1) {
            dfs(backR, backC, direction);
        }
    }
}
