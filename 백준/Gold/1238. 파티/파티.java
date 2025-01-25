// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
플로이드 워셜 혹은 다익스트라를 이용하면 해결 가능
- 다익스트라 사용: 시간복잡도 O(M*logN)
- 플로이드 워셜: 시간복잡도 O(N^3)

각 정점에서 X로 가는 경로, X에서 각 정점으로 가는 경로만 있으면 돼서 이것만 봐도 다익스트라가 유리.

<풀이>
1. 각 집에서 X번 마을로 가는 최단 시간 구하기
2. X번 마을에서 각 집으로 가는 길을 구하기
3. 1+2의 합계 중 최댓값 구하기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();;

    static int N, M, X;
    static ArrayList<ArrayList<Node>> graph;    // 정방향 그래프
    static ArrayList<ArrayList<Node>> revGraph; // 역방향 그래프

    static class Node implements Comparable<Node> {
        int end, weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList<>();
        revGraph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }

        // 도로 정보 입력
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, time));     // 정방향 간선
            revGraph.get(end).add(new Node(start, time));  // 역방향 간선
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    // 문제 해결 함수
    static int solution() {
        // X에서 각 정점으로의 최단 거리
        int[] fromX = dijkstra(graph, X);
        // 각 정점에서 X로의 최단 거리
        int[] toX = dijkstra(revGraph, X);

        // 가장 오래 걸리는 시간 찾기
        int maxTime = 0;
        for(int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, fromX[i] + toX[i]);
        }

        return maxTime;
    }

    static int[] dijkstra(ArrayList<ArrayList<Node>> g, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(dist[curr.end] < curr.weight) continue;

            for(Node next : g.get(curr.end)) {
                if(dist[next.end] > dist[curr.end] + next.weight) {
                    dist[next.end] = dist[curr.end] + next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist;
    }
}