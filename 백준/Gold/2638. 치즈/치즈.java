// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();



    static class Position {
        int y, x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int height, width, map[][], cheeseCnt;

    // 치즈를 저장할 리스트
    static List<Position> cheeseList;
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        cheeseList = new ArrayList<>();
        cheeseCnt = 0;

        // 배열에 치즈 입력(리스트에도 치즈 저장, 치즈 개수 갱신)
        for(int row = 0; row < height; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < width; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if(map[row][col] == 1) {
                    cheeseList.add(new Position(row, col));
                    cheeseCnt += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    static boolean[][] visited;
    private static int solution() {
        int time = 0;

        // 치즈가 없을 때까지 반복
        while(cheeseCnt != 0) {
            time++;
            visited = new boolean[height][width];
            bfs(); // 외부 공기 표시
            removeCheese(); // 치즈 녹이기
        }

        return time;
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    // 외부 공기 영역 파악 메서드
    private static void bfs() {
        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(0, 0));
        visited[0][0] = true;
        map[0][0] = 2;

        while(!queue.isEmpty()) {
            Position now = queue.poll();
            int y = now.y;
            int x = now.x;

            for(int direction = 0; direction < 4; direction++) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                // 배열 벗어나면 pass
                if (ny < 0 || nx < 0 || ny >= height || nx >= width) continue;
                // 방문했거나 치즈인 경우 pass
                if (visited[ny][nx] || map[ny][nx] == 1) continue;

                // 외부 공기를 2로 설정, 방문 체크
                map[ny][nx] = 2;
                visited[ny][nx] = true;
                queue.add(new Position(ny, nx));
            }
        }
    }

    // 외부 공기와 맞닿은 치즈 제거
    private static void removeCheese() {
        // 모든 치즈 탐색
        for(int idx = 0; idx < cheeseList.size(); idx++) {
            int y = cheeseList.get(idx).y;
            int x = cheeseList.get(idx).x;
            int cnt = 0;

            // 치즈의 4방 탐색
            for(int direction = 0; direction < 4; direction++) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                // 외부 공기와 닿은 면적 체크
                if(map[ny][nx] == 2) {
                    cnt++;
                }
            }

            // 외부 공기에 노출된 변이 2개 이상인 경우 제거
            if(cnt >= 2) {
                map[y][x] = 0;
                cheeseCnt--;
                cheeseList.remove(idx);
                idx--;
            }
        }
    }
}