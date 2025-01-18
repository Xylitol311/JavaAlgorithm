// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
풀이 방법
1. 우선 순위 큐에 가중치 순으로 경로 입력
2. union-find로 모든 집이 연결되면 종료

관건
- 0 0이 입력될 때까지 한번에 여러 개의 케이스가 입력된다는 것에 집중해야 함.
- 최소 거리를 출력하는 것이 아닌 케이스 별로 총 거리에서 최소 거리를 뺀 절약할 수 있는 최대 금액을 입력해야 함.
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int m, n, answer, totalCost, parents[];
    static PriorityQueue<Edge> edges;

    private static boolean input() throws IOException {
        answer = 0; // 최소 비용
        totalCost = 0; // 케이스의 총 비용
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        if (m == 0 && n == 0) return false; // 입력이 0, 0이 들어오면 종료

        edges = new PriorityQueue<>();
        
        // union-find 부모 배열
        parents = new int[m];
        Arrays.fill(parents, -1);

        // 경로 입력
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
            if (!input()) break; // 0 0이 입력된 경우 종료
            solution();
        }
    }

    private static void solution() {
        int cnt = 0;
        // 1. 엣지를 순서대로 꺼내어 union-find 실행
        while (!edges.isEmpty()) {
            Edge e = edges.poll();
            if (union(e.start, e.end)) {
                // 2. union-find가 실행될 때 cost 누적
                answer += e.cost;
                // 3. 연결된 경로의 개수가 전체 집 - 1이면 모든 집이 연결된 것이므로 종료
                if (++cnt == m - 1) break;
            }
        }
        System.out.println(totalCost - answer);
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