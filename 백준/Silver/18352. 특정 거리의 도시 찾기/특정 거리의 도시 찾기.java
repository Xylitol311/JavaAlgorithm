import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int N, M, K, X;
    static List<Integer>[] graph;
    static int[] distance;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 변수 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 그래프, 거리 저장 배열 초기화
        graph = new ArrayList[N + 1];
        distance = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList();
            distance[i] = -1; // 경로가 존재하지 않는 경우를 대비해 -1
        }

        // 그래프 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {
        // 최단 경로 계산
        bfs(X);

        // 출발점에서 K만큼의 거리인 목적지 구하기
        List<Integer> result = new ArrayList();

        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                result.add(i);
            }
        }

        if (result.isEmpty()) {
            sb.append(-1);
            return;
        }

        Collections.sort(result);
        for (int num : result) {
            sb.append(num).append("\n");
        }

    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque();
        queue.add(start);
        distance[start] = 0; // 출발점의 경로는 0

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (distance[next] == -1) { // 방문하지 않은 경우에만 진행
                    distance[next] = distance[current] + 1;
                    queue.add(next);
                }
            }
        }
    }
}