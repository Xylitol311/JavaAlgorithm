// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1. 우선 순위 큐에 가중치 순으로 경로 입력
2. union-find로 길 연결 후 시도
관건
- 모든 길이 연결된 것을 어떻게 확인할 것인지?
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int m, n, answer, totalCost, parents[];
    static PriorityQueue<Edge> edges;

    private static boolean input() throws IOException {
        answer = 0;
        totalCost = 0;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        if (m == 0 && n == 0) return false;

        edges = new PriorityQueue<>();
        parents = new int[m];
        Arrays.fill(parents, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            totalCost += cost;
            edges.add(new Edge(a, b, cost));
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            if (!input()) break;
            solution();
        }
    }

    private static void solution() {
        // 1. 엣지를 순서대로 꺼내어 union-find 실행
        // 2. union-find가 실행될 때 cost 누적
        int cnt = 0;
        while (!edges.isEmpty()) {
            Edge e = edges.poll();
            if (union(e.start, e.end)) {
                answer += e.cost;
                if (++cnt == m - 1) break;
            }
        }
        System.out.println(totalCost - answer);
    }

    private static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB)
            return false;

        // 편의상 a집합에 b집합을 붙임(집합의 크기에 따라 붙이도록 처리도 가능)
        parents[rootA] += parents[rootB]; // 집합 크기 관리 (절대값을 사용하면 집합의 크기가 됨)
        parents[rootB] = rootA;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}