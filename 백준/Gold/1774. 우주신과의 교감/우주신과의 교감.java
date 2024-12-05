// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] parents;
    static double answer;
    static Queue<Edge> edges;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;

        // 부모 노드 배열 초기화
        make();

        // 좌표 입력
        Point[] points = new Point[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points[i] = new Point(i, x, y);
        }

        // 기존 연결 간선 정보 업데이트
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            union(start, end);
        }

        // 모든 지점의 간선 정보를 edgeList에 저장
        edges = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double weight = distance(points[i], points[j]);
                edges.add(new Edge(i, j, weight));
            }
        }
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(String.format("%.2f", answer));
    }

    private static void solution() {
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            if (union(edge.start, edge.end)) {
                answer += edge.weight;
            }
        }
    }


    private static void make() {
        parents = new int[N + 1];
        Arrays.fill(parents, -1);
    }

    private static int findSet(int a) {
        if (parents[a] < 0) {
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }

    private static class Point {
        int num;
        double x, y;

        public Point(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int start, end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (weight < o.weight) {
                return -1;
            }
            return 1;
        }
    }
}