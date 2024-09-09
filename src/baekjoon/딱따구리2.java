package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 딱따구리2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[][] island;
    static int startX;
    static int startY;

    // 상하좌우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        input();
        int answer = bfs();
        print(answer);
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();

        // 딱따구리 시작 위치 입력
        queue.add(new Node(startY, startX, 1));

        // 시작 위치를 기준으로 탐색 시작
        // 벽이면 패스, 매 반복마다 카운트를 증가시키고 3,4,5 중에 하나가 나오는 순간 종료
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int y = node.y;
            int x = node.x;
            int cnt = node.cnt;

            // 지나온 곳을 되돌아가는 걸 방지하기 위해 현위치를 1로 변경
            island[y][x] = 1;

            for (int direction = 0; direction < 4; direction++) {
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx < 0 || nx >= island[0].length || ny < 0 || ny >= island.length) continue;

                // 음식인 경우
                if (island[ny][nx] == 3 || island[ny][nx] == 4 || island[ny][nx] == 5) {
                    return cnt;
                }
                // 벽이면 패스
                if (island[ny][nx] != 1) {
                    queue.add(new Node(ny, nx, cnt + 1));
                }
            }
        }

        // 여기 도달했다는 건 음식을 먹을 수 없는 경우
        return -1;
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        island = new int[row][col];

        // 섬 정보 입력
        for (int r = 0; r < row; r++) {
            String str = br.readLine();
            for (int c = 0; c < col; c++) {
                int num = str.charAt(c) - '0';
                island[r][c] = num;
                if (num == 2) {
                    startX = c;
                    startY = r;
                }
            }
        }

    }

    private static void print(int answer) throws IOException {
        if (answer == -1) {
            bw.write("NIE");
        } else {
            bw.write("TAK\n");
            bw.write(Integer.toString(answer));
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static class Node {
        int y;
        int x;
        int cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
