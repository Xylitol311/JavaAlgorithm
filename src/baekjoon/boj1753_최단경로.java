package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
<문제>
V개의 정점과 E개의 간선 정보가 주어졌을 때, 시작점에서부터 다른 모든 정점으로의 최단 경로를 구하고,
각 정점까지의 최단 경로값을 출력하는 것. 시작점은 0으로 경로가 없을 경우 INF를 출력한다.
<풀이 방법>
간선의 가중치가 음의 정수인 경우가 없으므로 다익스트라 알고리즘으로 풀 수 있다.
1. 간선 정보를 Node(끝정점, 가중치, 다음 노드) 형태의 객체에 저장하고 각 시작 정점에서 갈 수 있는 노드들의 정보를 저장할 노드 배열을 만든다.
2. 정점과 간선의 개수, 시작 정점의 번호, 간선 정보를 입력 받는다.
3. 입력이 완료되면 큐와 다익스트라 알고리즘을 활용하여 입력된 시작지점으로부터 각 정점까지의 최단경로를 dist[]에 저장한다.
    3-1. dist의 초기값은 최대 int값으로 설정 후 최단 경로를 탐색하며, 마지막까지 값이 변하지 않는 경우 시작점과 연결되지 않은 경우다.
 */
public class boj1753_최단경로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int vertex, edge, startNum;
    static Node[] nodeList;
    static int[] dist;

    static class Node implements Comparable<Node>{
        int end, weight;
        Node next; // 노드 배열에 시작점에서 연결된 여러 노드를 탐색하기 위한 변수

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public Node(int end, int weight, Node next){
            this.end = end;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        vertex = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        startNum = Integer.parseInt(br.readLine());
        nodeList = new Node[vertex + 1];
        dist = new int[vertex + 1];

        // 초기값은 최대값인 INF를 사용한다.
        Arrays.fill(dist, INF);

        for(int i = 0 ; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // 입력된 간선 정보를 노드 객체를 생성하고, 노드 배열의 시작점 인덱스에 저장한다.
            nodeList[start] = new Node(end, weight, nodeList[start]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
        print();
        System.out.println(sb);
    }

    private static void dijkstra() {
        // 최단 경로 탐색을 위한 우선순위 큐
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // 최단경로가 확정되면 true로 표시할 배열
        boolean[] check = new boolean[vertex + 1];
        // 시작 노드 입력
        queue.add(new Node(startNum, 0));
        dist[startNum] = 0;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            int cur = curNode.end;

            // 최단 경로가 확정된 정점일 경우 패스
            if(check[cur] == true) continue;
            // 최단 경로 확정
            check[cur] = true;

            // 현 정점에서 갈 수 있는 정점들을 탐색하고 현 정점에서 다음 정점으로 갈 때 가중치의 합이 다음 정점의 기존 가중치보다 작다면
            // 최단 경로 가중치 정보를 갱신하고 해당 정점을 큐에 저장한다.
            for (Node next = nodeList[cur]; next != null; next = next.next) {
                if(dist[next.end] > dist[cur] + next.weight){
                    dist[next.end] = dist[cur] + next.weight;
                    queue.add(new Node(next.end, dist[next.end]));
                }
            }
        }
    }

    private static void print() {
        for(int i = 1; i <= vertex; i++){
            if(dist[i] == INF) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
    }
}
