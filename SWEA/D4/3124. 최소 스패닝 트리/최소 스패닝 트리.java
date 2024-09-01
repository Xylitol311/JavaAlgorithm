
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
정점의 개수와 간선 정보가 주어지고 이를 통해 만들 수 있는 최소 스패닝 트리의 가중치를 출력하는 문제
프림 알고리즘을 사용해서 최소 스패닝 트리를 구한다.
1. 테스트 케이스 번호 입력 받는다.
2. 정점의 수와 간선의 수를 입력 받는다.
3. 정점의 수 크기만큼 Node 배열을 만들고 각각의 인덱스에 노드를 연결리스트 형태로 구현한다.
4. 간선의 수만큼 for문을 반복한다. 한 줄에 시작 정점, 끝 정점, 가중치가 입력된다.
    4-1. 간선 정보를 from, to, cost로 나누어 입력받는다.
    4-2. 무향 그래프이기 때문에 from-to, to-from 두 가지 경우의 간선을 Node 배열에 입력한다.
5. Prim 알고리즘과 우선순위 큐를 이용하여 MST를 구현하고 완성된 MST의 최소 비용을 출력한다. 
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int vertexNum, edgeNum;
    static Node[] nodes;
    static boolean[] visited;
    private static Queue<Node> pq;

    static long answer;

    // main solution
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            input();
            solution();
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solution() {
        // 우선순위 큐 초기화
        pq = new PriorityQueue<>();
        
        // 첫 노드 입력
        pq.offer(new Node(1, 0, null));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 현재 노드 방문 체크
            if (visited[node.to])
                continue;

            // 방문 처리 및 비용 누적
            visited[node.to] = true;
            answer += node.cost;

            // 현재 노드의 다음 노드가 null일 때까지 반복
            for (Node nextNode = nodes[node.to]; nextNode != null; nextNode = nextNode.next) {
                // 다음 노드를 이미 방문한 경우 패스
                if (visited[nextNode.to]) continue;
                pq.add(nextNode);
            }
        }

    }

    // input
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        vertexNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());

        nodes = new Node[vertexNum + 1];
        visited = new boolean[vertexNum + 1];
        

        // 각 정점의 간선과 가중치 정보 입력
		// 무향 그래프이기 때문에 양쪽 정보를 다 입력해줘야 함
        for (int idx = 0; idx < edgeNum; idx++) {
            st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			nodes[from] = new Node(to, cost, nodes[from]);
			nodes[to] = new Node(from, cost, nodes[to]);
        }
        answer = 0;
    }

    static class Node implements Comparable<Node> {
        int to, cost;
        Node next;

        public Node(int to, int cost, Node next) {
            this.to = to;
            this.cost = cost;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
