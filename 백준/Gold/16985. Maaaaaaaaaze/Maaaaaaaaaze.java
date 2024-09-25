// 메모리: 297192KB / 시간: 1268ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
<시간 복잡도 계산>
- 2차원 배열을 쌓는 경우의 수: 5! = 120
- 배열의 회전 경우의 수: 4 ^ 5 = 1024
- 모든 케이스 경우의 수: 122,880 = 120 * 1024
- 5*5*5 3차원 배열의 BFS 탐색 시간 복잡도 O(V + E) = 875
    - V = 125개, E = 125 * 6 = 750개(상,하,좌,우,앞,뒤)

<풀이 방법>
1. 5개의 배열의 순열 경우의 수를 모두 구해서 List에 저장
2. 각 경우의 수를 for문을 통해 반복하고, 각 배열들을 for문을 이용해서 회전된 상태를 만들어줌(여기서 6중 for문 발생..)
3. 만들어진 3차원 배열을 가지고 bfs 탐색
    3-1. 항상 0,0,0에서 4,4,4로 이동 (배열의 회전한 모든 경우의 수를 구해줬으니 출발지, 도착지는 고정 가능)
    3-2. 출발지 혹은 도착지가 0인 경우는 -1 return. (도달할 수 없는 경우)
    3-3. 도달했다면 이동거리 반환
4. 이동거리가 -1이 아닌 경우에만 최솟값을 갱신하고 마지막에 minDistance가 그대로 MAX_VALUE라면 도달할 수 없는 경우이므로 -1 출력

<개선 방안>
- dfs, 백트래킹으로 구체적으로 조건을 설정해서 도달할 수 없는 경우를 판별해서 다음 케이스로 넘어가는 방법
 */
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
        for (int[] order : permutations) { // 각 2차원 배열의 순열 경우의 수 결과
            for (int r1 = 0; r1 < 4; r1++) { // 첫번째 배열의 방향
                for (int r2 = 0; r2 < 4; r2++) { // 두번째 배열의 방향
                    for (int r3 = 0; r3 < 4; r3++) { // 세번째 배열의 방향
                        for (int r4 = 0; r4 < 4; r4++) { // 네번째 배열의 방향
                            for (int r5 = 0; r5 < 4; r5++) { // 다섯번째 배열의 방향
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
                                    if (minDistance == 12) return 12;
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

    public static void permutation(List<int[]> result, int[] arr, int depth, int used) {
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
        if (tempMaze[0][0][0] == 0 || tempMaze[4][4][4] == 0)
            return -1; // 출발지 또는 도착지가 막힌 경우

        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N][N][N];
        queue.add(new int[]{0, 0, 0, 0}); // x, y, z, 이동 횟수
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], z = current[2], dist = current[3];

            // 도착지에 도달한 경우 이동 거리 반환
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