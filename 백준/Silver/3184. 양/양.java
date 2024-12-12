// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
BFS 탐색을 통해 문제 해결
1. 필드 배열과 방문 배열을 생성하고 필드 입력
2. 필드 입력 시 양과 늑대의 수를 확인.
3. 필드 입력 시 울타리는 방문 배열에 체크
4. 필드의 첫번째 위치부터 탐색하며 방문배열에 체크가 되어있다면 패스. 그렇지 않다면 그 지점부터 bfs 탐색
5. bfs 탐색 시 방문 위치를 체크하고 탐색 중 늑대와 양의 수를 파악.
6. 탐색이 종료되면 늑대와 양의 수를 비교해서 양이 늑대보다 많다면 전체 늑대의 수에서 현재 영역의 늑대 수만큼 차감. 반대인 경우는 양을 차감한다.
7. 모든 필드 탐색이 끝나면 양과 늑대 수를 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int Row, Col, totalWolves, totalSheep;
    static boolean[][] visited;
    static char[][] field;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        Row = Integer.parseInt(st.nextToken());
        Col = Integer.parseInt(st.nextToken());

        field = new char[Row][Col];
        visited = new boolean[Row][Col];

        totalWolves = 0;
        totalSheep = 0;

        for (int row = 0; row < Row; row++) {
            String line = br.readLine();
            for (int col = 0; col < Col; col++) {
                field[row][col] = line.charAt(col);
                if (field[row][col] == '#') visited[row][col] = true;
                if (field[row][col] == 'v') totalWolves++;
                if (field[row][col] == 'o') totalSheep++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        sb.append(totalSheep).append(" ").append(totalWolves);
        System.out.println(sb.toString());
    }

    private static void solution() {
        for (int row = 0; row < Row; row++) {
            for (int col = 0; col < Col; col++) {
                if (visited[row][col]) continue;
                bfs(row, col);
            }
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    private static void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        int areaSheep = 0;
        int areaWolves = 0;

        // 늑대나 양인지 확인
        if (field[y][x] == 'v') areaWolves++;
        if (field[y][x] == 'o') areaSheep++;


        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];

                // 범위 밖이거나 방문했으면 패스
                if (nx < 0 || ny < 0 || nx >= Col || ny >= Row || visited[ny][nx]) continue;

                // 늑대나 양인지 확인
                if (field[ny][nx] == 'v') areaWolves++;
                if (field[ny][nx] == 'o') areaSheep++;

                queue.add(new int[]{ny, nx});
                visited[ny][nx] = true;

            }
        }

        if (areaSheep > areaWolves) totalWolves -= areaWolves;
        else totalSheep -= areaSheep;
    }
}