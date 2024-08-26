import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
지뢰찾기 게임

<전략>
- 각 점마다 상하좌우, 대각선을 탐색한 뒤 지뢰가 하나도 없어야 클릭없이 다음 칸 탐색
- 최소 클릭 수를 구하기 위해 주변 지뢰 수가 0인 칸을 최대한 많이 눌러야 함
- BFS 사용

 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static boolean[][] visited;
    static int size;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; //상 -> 상좌 순으로 팔방탐색
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int answer;



    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());

        //케이스 반복
        for (int tc = 1; tc <= T; tc++) {
            input();
            solution();
            print(tc);
        }
        System.out.println(sb);
    }

    private static void solution() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                //처음에는 최대한 탐색을 넓게 하기 위해 폭탄이 0인 곳을 먼저 탐색
                if (bomb(r, c) == 0 && map[r][c] != '*' && visited[r][c] == false) {
                    answer += 1;
                    bfs(r, c);
                }
            }
        }

        // 폭탄 수가 0인칸을 누르면 주변에 숫자가 나올 때까지 탐색
        // 0이 아닌 칸들은 각 칸마다 클릭 수를 증가
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (map[r][c] == '.') {
                    map[r][c] = (char) (bomb(r, c) + '0');
                    visited[r][c] = true;
                    answer += 1;
                }
            }
        }
    }

    // 주변에 폭탄 여부를 확인하는 함수
    static public int bomb(int row, int col) {
        int cnt = 0;
        for (int d = 0; d < 8; d++) {
            int ny = row + dy[d];
            int nx = col + dx[d];
            if (ny >= 0 && ny < size && nx >= 0 && nx < size) {
                if (map[ny][nx] == '*') {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static public void bfs(int row, int col) {
        Deque<char[]> deque = new ArrayDeque<>();
        visited[row][col] = true;
        deque.offer(new char[]{(char) (row + '0'), (char) (col + '0')});

        //입력을 받을 때 char[] 로 받아와서 int로 바꿔주는 과정이 필요
        while (!deque.isEmpty()) {
            char[] now = deque.poll();
            int nowRow = now[0] - '0';
            int nowCol = now[1] - '0';
            int cnt = bomb(nowRow, nowCol);
            map[nowRow][nowCol] = (char) (cnt + '0');
            if (cnt > 0) {
                continue;
            }
            for (int d = 0; d < 8; d++) {
                int ny = nowRow + dy[d];
                int nx = nowCol + dx[d];
                if (ny >= 0 && ny < size && nx >= 0 && nx < size && !visited[ny][nx] && map[ny][nx] != '*') {
                    visited[ny][nx] = true;
                    deque.offer(new char[]{(char) (ny + '0'), (char) (nx + '0')});
                }
            }
        }
    }

    private static void input() throws IOException {
        answer = 0;
        size = Integer.parseInt(br.readLine().trim());
        map = new char[size][size];
        visited = new boolean[size][size];

        // 배열 입력
        for (int r = 0; r < size; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            String line = st.nextToken();
            for (int c = 0; c < size; c++) {
                map[r][c] = line.charAt(c);
            }
        }
    }

    private static void print(int tc) {
        sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }
}
