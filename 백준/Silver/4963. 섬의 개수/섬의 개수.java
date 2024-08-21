import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int OCEAN = 0;
    static final int ISLAND = 1;


    static int[][] map;
    static boolean[][] check;
    // 위에서부터 시계방향 탐색
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int width;
    static int height;
    static int answer; // 섬의 개수


    public static void main(String[] args) throws IOException{
        while (true) {
            if (input()) return;
            solution();
        }


    }

    private static void solution() throws IOException {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // 방문한 적 없으면서 섬일 때
                if (!check[row][col] && map[row][col] == ISLAND){
                    bfs(col, row);
                }
            }
        }
        bw.write(answer+"\n");
        bw.flush();
    }

    private static void bfs(int col, int row){
        Queue<int[]> queue = new LinkedList<>();

        // 딱따구리 시작 위치 입력
        queue.add(new int[]{row, col});

        // 시작 위치를 기준으로 탐색 시작
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];

            for (int direction = 0; direction < 8; direction++) {
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
                if (check[ny][nx]) continue;
                check[ny][nx] = true;
                if (map[ny][nx] == OCEAN) continue;
                queue.add(new int[]{ny, nx});
            }
        }
        answer++;
    }

    private static boolean input() throws IOException {
        st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        if (width == 0 && height == 0) return true; // 종료

        map = new int[height][width];
        check = new boolean[height][width];
        answer = 0;

        for (int row = 0; row < height; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < width; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        return false;
    }
}
