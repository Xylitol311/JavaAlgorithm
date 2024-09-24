import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    

    static final int N = 5; // 미로의 크기
    static int[][][] maze; // 미로
    static int[][][] tempMaze; // 회전 및 순서를 바꾼 임시 미로
    private static void input() throws IOException {
        maze = new int[N][N][N];
        tempMaze = new int[N][N][N];

        // 3차원 배열 입력 받기
        for (int mazeIdx = 0; mazeIdx < N; mazeIdx++) { // 각 2차원 배열
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    maze[mazeIdx][row][col] = Integer.parseInt(st.nextToken()); // 5개의 2차원 배열을 입력
                }
            }
        }
    }

    static int result;
    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    private static int solution() {
        // 각 2차원 배열을 쌓는 순서의 경우의 수(순열)
        List<int[]> permutations = getPermutations();
        int minDistance = Integer.MAX_VALUE;

        // 각 순열에 대해 시계, 반시계 방향으로 회전하며 BFS 탐색.. 6중 for문,, 될까..?
        for (int[] order : permutations) {
            for (int r1 = 0; r1 < 4; r1++) {
                for (int r2 = 0; r2 < 4; r2++) {
                    for (int r3 = 0; r3 < 4; r3++) {
                        for (int r4 = 0; r4 < 4; r4++) {
                            for (int r5 = 0; r5 < 4; r5++) {
                                // 5개의 배열을 각각 주어진 순서로 회전
                                tempMaze[0] = rotate(maze[order[0]], r1);
                                tempMaze[1] = rotate(maze[order[1]], r2);
                                tempMaze[2] = rotate(maze[order[2]], r3);
                                tempMaze[3] = rotate(maze[order[3]], r4);
                                tempMaze[4] = rotate(maze[order[4]], r5);

                                // BFS로 최단 경로 탐색
                                int dist = bfs();
                                if (dist != -1) {
                                    minDistance = Math.min(minDistance, dist);
                                }
                            }
                        }
                    }
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // 배열 쌓는 순열을 List<int[]> 형태로 저장해서 반환. 각 int[]은 2차원 배열의 순서를 저장한다.
    public static List<int[]> getPermutations() {
        List<int[]> result = new ArrayList<>();
        permutation(result, new int[N], 0, 0);
        return result;
    }

    public static void permutation(List<int[]> result, int[] arr, int depth,int used) {
        if (depth == N) {
            result.add(arr.clone()); // 순열 완성 시 결과에 추가
            return;
        }
        for (int i = 0; i < N; i++) {
            // 추가된 배열인지 체크
            if ((used & (1 << i)) == 0) {
                arr[depth] = i;
                permutation(result, arr, depth + 1, used | (1 << i)); // 사용 상태 업데이트
            }
        }
    }

    // 2차원 배열 회전 함수 (0: 원 상태, 1: 90도, 2: 180도, 3: 270도)
    public static int[][] rotate(int[][] layer, int rotation) {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (rotation == 0) {
                    rotated[i][j] = layer[i][j]; // 회전 없음
                } else if (rotation == 1) {
                    rotated[j][N - 1 - i] = layer[i][j]; // 90도
                } else if (rotation == 2) {
                    rotated[N - 1 - i][N - 1 - j] = layer[i][j]; // 180도
                } else if (rotation == 3) {
                    rotated[N - 1 - j][i] = layer[i][j]; // 270도
                }
            }
        }
        return rotated;
    }
    
    static int[] dx = {1, -1, 0, 0, 0, 0}; // 이동 가능한 6방향 (x, y, z)
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static boolean[][][] visited;
    // BFS로 최단 경로 탐색
    public static int bfs() {
        if (tempMaze[0][0][0] == 0 || tempMaze[4][4][4] == 0) return -1; // 출발지 또는 도착지가 막힌 경우

        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N][N][N];
        queue.add(new int[]{0, 0, 0, 0}); // x, y, z, 이동 횟수
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], z = current[2], dist = current[3];

            // 도착지에 도달한 경우
            if (x == 4 && y == 4 && z == 4) return dist;

            // 6방향 탐색
            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                // 범위를 벗어나지 않고, 방문하지 않았으며, 이동 가능한 칸인 경우
                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < N && ny < N && nz < N && !visited[nx][ny][nz] && tempMaze[nx][ny][nz] == 1) {
                    visited[nx][ny][nz] = true;
                    queue.add(new int[]{nx, ny, nz, dist + 1});
                }
            }
        }
        return -1; // 도달할 수 없는 경우
    }
}