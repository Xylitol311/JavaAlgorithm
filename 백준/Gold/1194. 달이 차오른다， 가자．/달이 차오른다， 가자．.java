// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1. bfs를 이용해 탐색
2. 방문체크 시 열쇠의 소유 여부를 함께 체크한다
3. 탐색 중 열쇠를 만나면 해당 열쇠를 현재 열쇠에 추가
    3-1. 열쇠의 소지 여부 확인은 비트마스킹을 이용한다. (열쇠의 종류는 6개라서 가능)
    3-2. 추가는 쉬프트 연산
    3-2. and 연산으로 현재 키를 갖고 있는지 확인
4. 탐색 중 문을 만나면 해당 문과 맞는 열쇠를 갖고 있는지 확인 후 이동
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static char[][] map;
    static int ROW, COL;
    static Node start;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};


    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());

        map = new char[ROW][COL];

        // map 초기화 플레이어 위치 초기화
        for (int row = 0; row < ROW; row++) {
            String str = br.readLine();
            for (int col = 0; col < COL; col++) {
                map[row][col] = str.charAt(col);
                if (map[row][col] == '0') {
                    start = new Node(row, col, 0, 0);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[ROW][COL][64]; // 열쇠 종류는 6개 = 2^6 = 64
        queue.offer(start);
        // 시작위치 방문체크
        visited[start.r][start.c][0] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            // 목적지에 도착한 경우 현재까지 이동한 횟수 출력
            if(map[current.r][current.c] == '1')
                return current.count;

            for(int dir = 0; dir < 4; dir++) {
                int nx = current.r + dx[dir];
                int ny = current.c + dy[dir];

                // 범위 내인지 확인
                if(nx < 0 || ny < 0 || nx >= ROW || ny >= COL) continue;
                // 현재 소유한 키의 종류와 같은 상태로 방문한 적이 있거나 벽인 경우
                if(visited[nx][ny][current.key] || map[nx][ny] == '#') continue;

                // 다음 위치의 값이 열쇠 종류인 경우
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    // 열쇠를 비트마스킹 형태로 변환
                    int next_key = 1 << (map[nx][ny] - 'a');
                    // 소지하도록 변경. 기존에 이미 소지한 종류인 경우 변화 없음
                    next_key = current.key | next_key;
                    // 소지한 상태와 다음 위치 값을 방문 체크
                    visited[nx][ny][next_key] = true;
                    // 큐에 추가
                    queue.offer(new Node(nx, ny, current.count + 1, next_key));
                }

                // 다음 위치가 문인 경우
                else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    // 현재 문에 맞는 키를 소유했는지 확인
                    if((current.key & 1 << (map[nx][ny] - 'A'))
                            != 0) {
                        // 소지하고 있다면 다음 위치로 이동 가능
                        visited[nx][ny][current.key] = true;
                        queue.offer(new Node(nx, ny, current.count + 1, current.key));
                    }

                    // 소지하지 않았다면 가지 못함. 아무 처리 없음
                }

                // 그냥 빈 공간인 경우
                else {
                    visited[nx][ny][current.key] = true;
                    queue.offer(new Node(nx, ny, current.count + 1, current.key));
                }
            }
        }
        return -1;
    }

    public static class Node {
        int r, c, count, key;

        public Node(int r, int c, int count, int key) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.key = key;
        }
    }
}