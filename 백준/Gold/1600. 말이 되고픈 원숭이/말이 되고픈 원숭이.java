import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
원숭이는 체스판의 말처럼 최대 K번 움직일 수 있음.
단순하게 보면 BFS를 이용해 말처럼 이동할 때와 한 칸씩 이동할 때 둘로 나눠 방문처리를 하면 될 것처럼 보이지만,
실제로는 말의 이동방식으로 몇 번을 움직였냐에 따라 같은 지점에 도착해도 다 다른 경우의 결과가 나오므로
지점마다 말의 움직임을 0~K번 사용하여 도착했을 때의 경우를 각각 방문 체크해야 함.

1. 한 칸씩 움직이는 것과 말처럼 이동하는 경우를 나눠 BFS 탐색을 한다.
2. 이때 3차원 배열을 활용해 방문 체크를 한다. [행][열][현 시점에 남아있는 말 점프 가능 횟수]
3. 한 칸씩 이동하는 경우를 먼저 탐색하고 이후 말 점프 횟수가 남아있다면 말처럼 이동하는 경우를 탐색한다.
4. 위 과정을 반복하다가 목적지에 도착하면 answer 변수에 최솟값을 갱신한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int maxHorseMove, width, height, map[][];

    private static void input() throws IOException {
        maxHorseMove = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        map = new int[height][width];

        for (int row = 0; row < height; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < width; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    // 인접 상하좌우
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    // 말의 움직임 (오른쪽 위부터 시계방향)
    static int hdx[] = {1, 2, 2, 1, -1, -2, -2, -1};
    static int hdy[] = {-2, -1, 1, 2, 2, 1, -1, -2};
    static boolean[][][] visited;

    private static int bfs() {
        visited = new boolean[height][width][maxHorseMove + 1]; // 0부터 K까지 기록을 해야하므로 K + 1로 설정
        Queue<Monkey> queue = new ArrayDeque<>();
        queue.add(new Monkey(0, 0, 0, maxHorseMove));
        visited[0][0][maxHorseMove] = true;

        while (!queue.isEmpty()) {
            Monkey monkey = queue.poll();
            int y = monkey.y;
            int x = monkey.x;
            int moveCnt = monkey.moveCnt;
            int horseMove = monkey.horseMove;

            // 목적지 도착했을 때 종료
            if (y == height - 1 && x == width - 1) {
                return moveCnt;
            }

            // 한 칸씩 움직이는 경우
            // 인접 방향으로 움직임
            for (int direction = 0; direction < 4; direction++) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                // 배열 밖이면 통과
                if (ny < 0 || ny >= height || nx < 0 || nx >= width)
                    continue;

                // 벽(1)이거나 이미 같은 횟수의 점프를 사용해서 방문했던 곳이라면 패스
                if (visited[ny][nx][horseMove] || map[ny][nx] == 1)
                    continue;

                // 방문 처리 후 큐에 추가. 한 칸씩 움직였기 때문에 말로 움직인 횟수는 그대
                visited[ny][nx][horseMove] = true;
                queue.add(new Monkey(ny, nx, moveCnt + 1, horseMove));
            }

            // 말처럼 움직임
            if (horseMove == 0) continue;
            for (int direction = 0; direction < 8; direction++) {
                int ny = y + hdy[direction];
                int nx = x + hdx[direction];

                // 배열 밖이면 통과
                if (ny < 0 || ny >= height || nx < 0 || nx >= width)
                    continue;

                // 벽(1)이거나 이미 같은 횟수의 점프를 사용해서 방문했던 곳이라면 패스
                if (visited[ny][nx][horseMove-1] || map[ny][nx] == 1)
                    continue;

                // 방문처리 후 큐에 추가
                visited[ny][nx][horseMove - 1] = true;
                // 말로 움직인 경우니까 말로 움직인 횟수 차감
                queue.add(new Monkey(ny, nx, moveCnt + 1, horseMove - 1));
            }
        }

        return -1;
    }

    static class Monkey {
        int y, x, moveCnt, horseMove;

        public Monkey(int y, int x, int moveCnt, int horseMove) {
            super();
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
            this.horseMove = horseMove;
        }

    }
}