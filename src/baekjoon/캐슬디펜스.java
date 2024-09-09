package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
<풀이 전략>
    // 1. 궁수 조합 구하기
    // 2. 게임 진행
    // 2-1. 각 궁수에 대해서 bfs로 하살 범위 안에 가장 가까운 적 찾아서 제거
    // 2-2. 적 한 줄 움직임
    // 2-3. 모든 적이 제외될 때까지 반복
 */
public class 캐슬디펜스 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int maxRow;
    static int maxCol;
    static int attackRange;// 궁수 사거리
    static int[][] map; // 적이 표시된 맵 배열
    static int[][] playMap; // 적이 표시된 맵 배열
    static int[] archers; // 궁수 위치 조합 배열

    static final int ARCHER_NUM = 3;

    static int[] dx = {-1, 0, 1}; // 적을 왼쪽부터 탐색.
    static int[] dy = {0, -1, 0}; // 적을 왼쪽부터 탐색.


    static int answer;// 가장 많은 적을 처치한 경우

    public static void main(String[] args) throws IOException {
        input();
        combination(0, 0, 0);
        System.out.println(answer);
    }

    private static void combination(int elementIdx, int cnt, int isSelected) {
        // 기저 조건
        // 선택할 수 있을만큼 다 선택했으면
        if (cnt == ARCHER_NUM) {
            int selectedIdx = 0;
            for (int idx = 0; idx < maxCol; idx++) {
                if ((isSelected & 1 << idx) != 0) {
                    archers[selectedIdx++] = idx;
                }
            }
            // 플레이할 맵 완성
            for (int row = 0; row < maxRow; row++) {
                playMap[row] = map[row].clone();
            }

            answer = Math.max(answer, play());
            return;
        }

        // 선택하면서 모든 원소들을 확인했으면
        if (elementIdx == maxCol) {
            return;
        }

        // 원소를 선택해서 담아줌
        combination(elementIdx + 1, cnt + 1, isSelected | 1 << elementIdx);

        // 원소를 선택하지 않고 넘어감
        combination(elementIdx + 1, cnt, isSelected);
    }

    static Position[] enemies;

    private static int play() {
        int killCount = 0;
        int archerRow;
        // 모든 줄을 돌 때까지 반복
        // 한 턴이 끝날 때마다 궁수를 한 줄 위로 이동(적들이 내려오는 것과 같은 효과)
        for (archerRow = maxRow; archerRow > 0; archerRow--) {
            // 가장 가까운 적 탐색
            // 동시 공격을 고려해 각각 찾아 바로 제거하지 않고 임시 배열에 저장
            enemies = new Position[ARCHER_NUM];

            for (int targetIdx = 0; targetIdx < ARCHER_NUM; ++targetIdx) {
                bfs(archerRow, targetIdx);
            }

            for (int targetIdx = 0; targetIdx < enemies.length; targetIdx++) {
                Position enemy = enemies[targetIdx];
                // 처리할 수 있는 적이 없으면 패스
                if (enemy == null)
                    continue;

                // 적 제거
                if (playMap[enemy.row][enemy.col] == 1) {
                    playMap[enemy.row][enemy.col] = 0;
                    killCount++;
                }
            }
        }
        return killCount;
    }

    private static void bfs(int archerLine, int col) {// 궁수의 row, col
        // 현재 궁수가 있는 위치까지만 탐색하기 위해 알맞은 크기의 방문 배열 생성
        boolean[][] visited = new boolean[archerLine][maxCol];

        // 입력받은 archerLine과 col을 기준으로 거리 내에 있는 적들을 탐색.
        // 가장 가까운 줄 왼쪽부터 탐색하며 적을 찾으면 적의 위치를 static enemies 배열에 저장
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(archerLine, archers[col]));

        while (!queue.isEmpty()) {
            Position now = queue.poll();
            for (int delta = 0; delta < 3; delta++) {
                int nextRow = now.row + dy[delta];
                int nextCol = now.col + dx[delta];

                // 범위 밖인지 체크
                if (nextRow < 0 || nextRow >= archerLine || nextCol < 0 || nextCol >= maxCol)
                    continue;

                // 궁수의 사거리 안 쪽인지 체크
                if (Math.abs(archerLine - nextRow) + Math.abs(col - nextCol) > attackRange)
                    continue;

                // 방문했는지 체크
                if (visited[nextRow][nextCol])
                    continue;

                // 적인지 체크
                if (playMap[nextRow][nextCol] == 1) {
                    enemies[col] = new Position(nextRow, nextCol);
                    return;
                }

                // 큐에 추가
                visited[nextRow][nextCol] = true;
                queue.add(new Position(nextRow, nextCol));
            }
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        attackRange = Integer.parseInt(st.nextToken());

        map = new int[maxRow][maxCol];
        playMap = new int[maxRow][maxCol];
        archers = new int[ARCHER_NUM];

        answer = 0;

        // 맵 입력
        for (int row = 0; row < maxRow; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < maxCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }
    }
}
