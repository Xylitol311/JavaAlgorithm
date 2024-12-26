// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
[기본 풀이 방법]
BFS를 이용해 상하좌우를 확인 후 이동.
이동 중 다음 위치가 벽인 경우.
- 벽을 부순 적이 없으면 벽을 부수고 이동
- 벽을 부순 적이 있으면 갈 수 없음

이동한 위치를 저장하는 visited 3차원 boolean배열을 만들어 각 좌표별로 벽을 부순 상태로 이동한 적이 있는지 저장.
- 벽이 아닌 경우
    - 부순 벽이 없었다면 visited[][][0]을 방문처리 후 queue에 저장
    - 부순 벽이 있으면 visited[][][1]을 방문처리 후 queue에 저장
- 벽인 경우
    - 벽을 부순 적이 없다면 visited[][][1]을 방문처리 후 queue에 저장
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[][] map;
    static int n, m;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int row = 0; row < n; row++) {
            String input = br.readLine();
            for (int col = 0; col < m; col++) {
                map[row][col] = input.charAt(col);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    private static int solution() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1, false));


        boolean[][][] visited = new boolean[n][m][2];

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == n - 1 && now.c == m - 1) {
                return now.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dx[d];
                int nc = now.c + dy[d];

                if(nr<0 || nr>=n || nc<0 || nc>=m) continue;

                int next_cnt = now.cnt+1;

                // 벽이 아닌 경우
                if(map[nr][nc]=='0'){
                    // 벽을 부순 적이 없는 경우
                    if(!now.destroyed && !visited[nr][nc][0]) { 
                        q.add(new Point(nr, nc, next_cnt, false));
                        visited[nr][nc][0] = true;
                    }else if(now.destroyed && !visited[nr][nc][1]){ // 벽을 한번 부신 적이 있으면
                        q.add(new Point(nr, nc, next_cnt, true));
                        visited[nr][nc][1] = true;
                    }

                }
                // 벽인 경우
                else if(map[nr][nc]=='1'){
                    // 한번도 벽을 부신 적이 없다면 
                    if(!now.destroyed){ 
                        q.add(new Point(nr, nc, next_cnt, true));
                        visited[nr][nc][1] = true;
                    }
                }
            }

        }

        return -1;
    }

    static class Point {
        int r;
        int c;
        int cnt;
        boolean destroyed;

        public Point(int r, int c, int cnt, boolean d) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.destroyed = d;
        }
    }
}