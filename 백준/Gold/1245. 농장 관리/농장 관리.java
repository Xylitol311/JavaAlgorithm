import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int rowCount;
    static int colCount;
    static int[][] grid; // 높이 정보
    static boolean[][] visited;
    
    static int[] dRow = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dCol = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        int peakCount = solution();
        sb.append(peakCount);
        System.out.print(sb.toString());
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        rowCount = Integer.parseInt(st.nextToken());
        colCount = Integer.parseInt(st.nextToken());

        grid = new int[rowCount][colCount];
        visited = new boolean[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colCount; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int solution() {
        int countOfPeaks = 0;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                // 아직 방문하지 않은 칸이면 새로운 덩어리 검사
                if (!visited[i][j]) {
                    // DFS를 통해 이 높이 덩어리가 봉우리인지 검사
                    if (isPeakComponent(i, j, grid[i][j])) {
                        countOfPeaks++;
                    }
                }
            }
        }

        return countOfPeaks;
    }

    /**
     * 특정 칸에서 시작해 sameHeight 높이를 가진 연결 덩어리를 DFS로 탐색
     * 주변에 더 높은 칸이 있는지 검사.
     * 덩어리가 봉우리 조건을 만족하면 true, 아니면 false
     */
    static boolean isPeakComponent(int startRow, int startCol, int peakHeight) {
        visited[startRow][startCol] = true;
        boolean isPeak = true;  // 처음엔 봉우리 조건을 유지한다고 가정

        for (int dir = 0; dir < 8; dir++) {
            int nextRow = startRow + dRow[dir];
            int nextCol = startCol + dCol[dir];

            // 격자 범위 안 확인
            if (0 <= nextRow && nextRow < rowCount && 0 <= nextCol && nextCol < colCount) {
                // 이웃 칸 높이가 같으면 덩어리의 일부이므로 재귀 탐색
                if (!visited[nextRow][nextCol] && grid[nextRow][nextCol] == peakHeight) {
                    if (!isPeakComponent(nextRow, nextCol, peakHeight)) {
                        isPeak = false;
                    }
                }
                // 이웃 칸 높이가 peakHeight보다 크면 봉우리 아님
                if (grid[nextRow][nextCol] > peakHeight) {
                    isPeak = false;
                }
            }
        }

        return isPeak;
    }
}

