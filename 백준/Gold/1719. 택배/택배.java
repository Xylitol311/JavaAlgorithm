// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, map[][], next[][];
    static final int INF = 10_000_000;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 최단 시간 기록 배열
        map = new int[n][n];
        // 최단 시간으로 가기 위한 다음 목적지 입력 배열
        next = new int[n][n];

        // map 초기화
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                next[row][col] = col;
                map[row][col] = INF;
            }
        }

        // 경로 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            map[start][end] = time;
            map[end][start] = time;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        floyd();
        print();

    }

    private static void floyd() {
        for (int k = 0; k < n; k++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    // 경유지를 거치는 것이 빠른 경우
                    if (map[start][end] > map[start][k] + map[k][end]) {
                        // 최단 시간 갱신
                        map[start][end] = map[start][k] + map[k][end];
                        // 해당 경유지로 최단 경로로 가기 위한 지점을 갱신
                        next[start][end] = next[start][k];
                    }
                }
            }
        }
    }

    private static void print() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row == col) {
                    sb.append("-").append(" ");
                } else {
                    sb.append(next[row][col] + 1).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}