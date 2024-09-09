package baekjoon.study8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 최소신장트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int vertexNum;
    static int[] parents;
    static long answer;

    // union-find
    static void make() {
        parents = new int[vertexNum];
        Arrays.fill(parents, -1);
    }

    static int findSet(int a) {
        if (parents[a] < 0)
            return a;
        return parents[a] = findSet(parents[a]); // 집합의 대표자를 자신의 부모로 변경 : 패스 압축!
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot)
            return false;

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }

    static PriorityQueue<Edge> pq;
    static int edgeNum;
    // input
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        vertexNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        answer = 0;

        pq = new PriorityQueue<>();

        // 간선 입력
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge(start - 1, end - 1, weight));
        }

    }

    // main solution
    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(answer);
    }

    static void solution() {
        make();

        int cnt = 0;

        while (true) {
            Edge edge = pq.poll();
            if (union(edge.start, edge.end)) {
                answer += edge.weight;
                if (++cnt == vertexNum - 1)
                    break;
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
