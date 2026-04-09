import java.util.*;

// BFS로 탐색
// 갈 수 있는 곳들을 큐에 넣고 차례대로 탐색한다. 큐에 들어갈 값(x&y좌표, 해당 좌표까지 이동한 횟수). 큐에 넣을 때 방문 체크
// 목적지(n,m)에 도착 시 현재까지 이동한 횟수를 반환하고 종료(BFS 특성상 도착시 최소 거리임)
// 도달하지 못하고 큐 반복이 종료되면 정답은 -1
class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        return bfs(n, m, maps);
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private int bfs(int n, int m, int[][] maps) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] now = queue.poll();

            // 목적지 도달 시
            if (now[0] == n - 1 && now[1] == m - 1) {
                return now[2];
            }
            
            // 목적지 미도달 시 다음 장소 탐색
            for (int d = 0; d < 4; d++) {
                int nr = dr[d] + now[0];
                int nc = dc[d] + now[1];
                
                // 갈 수 없는 곳인지 확인(맵 바깥, 이미 방문, 벽)
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || maps[nr][nc] == 0) continue;
                // 갈 수 있다면 큐에 추가 후 방문 체크
                queue.add(new int[]{nr, nc, now[2] + 1});
                visited[nr][nc] = true;
                
            }
        }
        
        // 여기 도달하면 목적지 도달 불가
        return -1;
    }
}