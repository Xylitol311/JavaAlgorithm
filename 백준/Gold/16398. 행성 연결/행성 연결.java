// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, parents[];
    static long answer;
    static PriorityQueue<Edge> pq;

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        parents = new int[N];
        Arrays.fill(parents, -1);

        answer = 0;

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                long cost = Long.parseLong(st.nextToken());
                pq.add(new Edge(row, col, cost));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    private static void solution() {
        int cnt = 0;
        // 1. 엣지를 순서대로 꺼내어 union-find 실행
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (union(e.start, e.end)) {
                // 2. union-find가 실행될 때 cost 누적
                answer += e.cost;
                // 3. 연결된 경로의 개수가 전체 집 - 1이면 모든 집이 연결된 것이므로 종료
                if (++cnt == N - 1) break;
            }
        }
        System.out.println(answer);
    }

    private static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]); // find set을 할 때마다 입력된 노드의 부모 노드를 갱신
    }

    private static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB)
            return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        long cost;

        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost > o.cost) {
                return 1;
            } else if (this.cost < o.cost) {
                return -1;
            }
            return 0;
        }
    }
}