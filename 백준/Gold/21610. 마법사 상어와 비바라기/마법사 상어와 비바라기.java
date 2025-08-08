import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<Cloud> clouds;
    static boolean[][] visited;
    
    // 방향: 1부터 8까지 (←, ↖, ↑, ↗, →, ↘, ↓, ↙)
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    // 대각선 방향 (↖, ↗, ↘, ↙)
    static int[] diagX = {-1, -1, 1, 1};
    static int[] diagY = {-1, 1, 1, -1};
    
    static class Cloud {
        int x, y;
        
        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 초기 구름 위치 설정
        clouds = new ArrayList<>();
        clouds.add(new Cloud(N-1, 0));
        clouds.add(new Cloud(N-1, 1));
        clouds.add(new Cloud(N-2, 0));
        clouds.add(new Cloud(N-2, 1));
        
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            
            solution(d, s);
        }
    }
    
    // 구름을 이동시키고 물을 증가시키는 함수
    public static void moveClouds(int d, int s) {
        for (Cloud cloud : clouds) {
            cloud.x = (cloud.x + dx[d] * s % N + N) % N;
            cloud.y = (cloud.y + dy[d] * s % N + N) % N;
            map[cloud.x][cloud.y]++;
        }
    }
    
    // 대각선 방향의 물이 있는 칸 개수를 세는 함수
    public static void waterCopyBug() {
        for (Cloud cloud : clouds) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cloud.x + diagX[i];
                int ny = cloud.y + diagY[i];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] > 0) {
                    count++;
                }
            }
            map[cloud.x][cloud.y] += count;
        }
    }
    
    // 새로운 구름을 생성하는 함수
    public static void createNewClouds() {
        // 이전 구름이 있던 위치 표시
        visited = new boolean[N][N];
        for (Cloud cloud : clouds) {
            visited[cloud.x][cloud.y] = true;
        }
        
        clouds.clear();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !visited[i][j]) {
                    map[i][j] -= 2;
                    clouds.add(new Cloud(i, j));
                }
            }
        }
    }
    
    // 전체 물의 양을 계산하는 함수
    public static int getTotalWater() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += map[i][j];
            }
        }
        return total;
    }
    
    public static void solution(int d, int s) {
        // 1. 모든 구름이 d 방향으로 s칸 이동
        moveClouds(d, s);
        
        // 2. 각 구름에서 비가 내려 해당 칸의 바구니에 저장된 물의 양이 1 증가
        // (moveClouds에서 이미 처리됨)
        
        // 3. 구름이 모두 사라진다.
        // 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전
        waterCopyBug();
        
        // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
        // 단, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
        createNewClouds();
    }
    
    public static void main(String[] args) throws IOException {
        input();
        System.out.println(getTotalWater());
    }
}