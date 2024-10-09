// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
다익스트라를 활용해서 출발지에서 다른 모든 헛간으로 가는 비용을 최솟값으로 갱신하며 최종적으로 목적지까지 가는 가장 적은 비용을 탐색
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, dist[];
    static List<Node>[] list;
    static boolean[] visited;
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향임으로 양쪽으로 입력
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }

        visited = new boolean[N + 1];
        dist = new int[N + 1];
        // 경로 수 * 경로 최대 cost + 1 = 50000001
        Arrays.fill(dist, 50000001);

    }

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
        System.out.println(dist[N]); //목적지는 n
    }

    public static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[1] = 0; //시작 지점은 1
        q.offer(new Node(1, 0));

        while(!q.isEmpty()) {
            Node current = q.poll();

            // 방문했으면 패스, 안 했으면 방문 처리
            if(visited[current.num]) continue;
            visited[current.num] = true;

            for(int i = 0; i < list[current.num].size(); i++) {
                Node next = list[current.num].get(i);
                // 최솟값 갱신이 필요한 경우 갱신 후 큐에 추가
                if(dist[next.num] > dist[current.num] + next.cost) {
                    dist[next.num] = dist[current.num] + next.cost;
                    q.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}