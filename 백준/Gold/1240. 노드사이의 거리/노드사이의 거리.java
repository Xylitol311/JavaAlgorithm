// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int nodes[][];

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(nodes[i], -1);
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            nodes[start][end] = weight;
            nodes[end][start] = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb);
    }

    private static void solution() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(bfs(start, end)).append("\n");
        }
    }
    static boolean[] visited;

    private static int bfs(int start, int end) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N + 1];

        visited[start] = true;
        queue.add(new int[]{start, 0}); // 시작노드, 현재까지 거리

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int i = 1; i <= N; i++) {
                if (nodes[node[0]][i] != -1 && !visited[i]) {
                    int weight = nodes[node[0]][i] + node[1];
                    if (i == end) return weight;

                    queue.add(new int[]{i, weight});
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
}