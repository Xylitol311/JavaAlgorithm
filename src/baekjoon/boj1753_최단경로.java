package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘을 이용한 최단경로 문제
public class boj1753_최단경로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int vertex, edge, startNum; // 정점 수, 간선 수, 시작 정점
    static ArrayList<int[]>[] graph;   // 인접 리스트 그래프
    static int[] dist;                 // 최단 거리 배열

    // 우선순위 큐를 위한 노드 클래스
    static class Node implements Comparable<Node>{
        int end, weight; // 도착 정점, 가중치

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        // 가중치 기준으로 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }
    }

    private static void input() throws IOException {
        // 정점 수, 간선 수 입력
        st = new StringTokenizer(br.readLine());
        vertex = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        startNum = Integer.parseInt(br.readLine());
        
        // 그래프 초기화
        graph = new ArrayList[vertex + 1];
        for(int i = 0; i <= vertex; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 거리 배열 초기화 (무한대로 설정)
        dist = new int[vertex + 1];
        Arrays.fill(dist, INF);

        // 간선 정보 입력
        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new int[]{end, weight});
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
        print();
        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[vertex + 1];
        
        // 시작점 초기화
        dist[startNum] = 0;
        pq.add(new Node(startNum, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int cur = current.end;

            // 이미 방문한 정점은 건너뛰기
            if(visited[cur]) continue;
            visited[cur] = true;

            // 현재 정점과 연결된 모든 간선 탐색
            for(int[] edge : graph[cur]) {
                int next = edge[0];   // 다음 정점
                int weight = edge[1]; // 간선 가중치
                
                // 더 짧은 경로를 찾으면 업데이트
                if(dist[next] > dist[cur] + weight){
                    dist[next] = dist[cur] + weight;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }
    }

    private static void print() {
        // 각 정점까지의 최단 거리 출력
        for(int i = 1; i <= vertex; i++){
            sb.append(dist[i] == INF ? "INF" : dist[i]).append('\n');
        }
    }
}
