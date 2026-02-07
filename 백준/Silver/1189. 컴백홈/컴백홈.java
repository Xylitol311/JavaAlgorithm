import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        visited = new boolean[R][C];
        
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
    
    // DFS로 모든 경로를 탐색하며 정확히 K번 만에 도착하는 경우를 카운트
    static void dfs(int x, int y, int distance) {
        // 목적지에 정확히 K번 만에 도착한 경우
        if (x == 0 && y == C - 1) {
            if (distance == K) {
                answer++;
            }
            return;
        }
        
        // 가지치기: 이미 K번 이상 이동했다면 더 이상 탐색 불필요
        if (distance >= K) {
            return;
        }
        
        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 범위 체크 및 방문 가능 여부 확인
            if (nx >= 0 && nx < R && ny >= 0 && ny < C 
                && !visited[nx][ny] && map[nx][ny] != 'T') {
                visited[nx][ny] = true;
                dfs(nx, ny, distance + 1);
                visited[nx][ny] = false; // 백트래킹
            }
        }
    }
    
    static void solution() {
        // 시작점 (R-1, 0)부터 탐색 시작
        visited[R - 1][0] = true;
        dfs(R - 1, 0, 1);
        System.out.println(answer);
    }
    
    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}