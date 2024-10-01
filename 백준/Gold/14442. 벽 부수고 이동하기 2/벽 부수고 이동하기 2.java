// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Player {

        int y, x, moveCnt, possibleGeonBbu;

        public Player(int y, int x, int moveCnt, int possibleGeonBbu) {
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
            this.possibleGeonBbu = possibleGeonBbu;
        }

    }

    static int width, height, maxGeonBbu, field[][];

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        maxGeonBbu = Integer.parseInt(st.nextToken());

        field = new int[height][width];

        for (int row = 0; row < height; row++) {
            String str = br.readLine();
            for (int col = 0; col < width; col++) {
                field[row][col] = str.charAt(col) - '0';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static boolean[][][] visited;

    private static int bfs() {
        visited = new boolean[height][width][maxGeonBbu + 1]; //0부터 k까지 기록
        Queue<Player> queue = new ArrayDeque<>();
        queue.add(new Player(0, 0, 0, maxGeonBbu));
        visited[0][0][maxGeonBbu] = true;

        while (!queue.isEmpty()) {
            Player player = queue.poll();
            int y = player.y;
            int x = player.x;
            int moveCnt = player.moveCnt;
            int possibleGeonBbu = player.possibleGeonBbu;

            // 목적지에 도달했으면 종료
            if (y == height - 1 && x == width - 1) {
                return moveCnt + 1;
            }

            // 벽을 부수지 않고 이동하는 경우
            for (int direction = 0; direction < 4; direction++) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                // 배열 밖이면 통과
                if (ny < 0 || ny >= height || nx < 0 || nx >= width)
                    continue;

                // 벽(1)이거나 이미 같은 횟수로 벽을 부수고 도착한 곳이면 패스
                // 현 위치가 벽이라면 이전 위치의 possibleGeonBbu는 하나 큰 것과 비교
                if (field[y][x] == 1) {
                    if (visited[ny][nx][possibleGeonBbu + 1] || field[ny][nx] == 1)
                        continue;
                } else {
                    if (visited[ny][nx][possibleGeonBbu] || field[ny][nx] == 1)
                        continue;
                }

                // 현재 벽을 부순 횟수로 방문 처리 후 큐에 추가
                visited[ny][nx][possibleGeonBbu] = true;
                queue.add(new Player(ny, nx, moveCnt + 1, possibleGeonBbu));
            }

            // 벽을 부수고 이동하는 경우
            if (possibleGeonBbu == 0) continue;
            for (int direction = 0; direction < 4; direction++) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                // 배열 밖이면 통과
                if (ny < 0 || ny >= height || nx < 0 || nx >= width)
                    continue;

                // 벽이 아니거나 이미 같은 횟수로 벽을 부수고 도착한 곳이면 패스
                if (field[ny][nx] == 0 || visited[ny][nx][possibleGeonBbu - 1]) {
                    continue;
                }

                // 현재 벽을 부순 횟수 - 1로 방문 처리 후 큐에 추가
                visited[ny][nx][possibleGeonBbu - 1] = true;
                queue.add(new Player(ny, nx, moveCnt + 1, possibleGeonBbu - 1));
            }
        }
        return -1;
    }
}