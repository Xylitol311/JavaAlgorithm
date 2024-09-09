package sw_expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 달팽이숫자 {
    private static int[] dx = new int[] {1, 0, -1, 0};
    private static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            // 변수 설정
            int cnt = 1;
            int idx = 0;
            int nx = 0;
            int ny = 0;

            // 첫 값 입력
            arr[ny][nx] = cnt++;

            // 달팽이 입력
            while(cnt <= N*N) {
                if(turn(arr, ny + dy[idx], nx + dx[idx], N)) {
                    idx = ++idx % 4;
                }
                nx = nx + dx[idx];
                ny = ny + dy[idx];

                arr[ny][nx] = cnt++;
            }

            // 배열 출력
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    sb.append(arr[row][col] + " ");
                }
                if (row == N - 1)
                    break;
                sb.append("\n");
            }
            System.out.printf("#%d\n", tc);
            System.out.println(sb);
        } // test case
    } // main

    private static boolean turn(int[][] nums, int y, int x, int N) {
        if (x >= N || x < 0 || y >= N || y < 0) {
            return true;
        }
        if (nums[y][x] != 0) {
            return true;
        }
        return false;
    }
}
