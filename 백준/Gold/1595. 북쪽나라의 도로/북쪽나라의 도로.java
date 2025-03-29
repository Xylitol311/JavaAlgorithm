import java.io.*;
import java.util.*;

// Edge 클래스: 각 노드와 연결된 간선의 정보를 저장합니다.
class Edge {
    int to;      // 연결된 노드 번호
    int weight;  // 해당 간선의 가중치 (도로 길이)

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {

    // 전역 변수로 BufferedReader, StringTokenizer, StringBuilder 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 그래프(트리)를 저장할 인접 리스트 배열
    static ArrayList<Edge>[] graph;
    
    // DFS 관련 전역 변수
    static boolean[] visited;  // 방문 체크 배열
    static int maxDistance;    // 현재까지 최대 누적 거리
    static int farthestNode;   // 최대 누적 거리를 갖는 노드
    // DFS 시작 도시 번호 (입력된 간선의 첫 번째 도시)
    static int startCity;

    public static void main(String[] args) throws Exception {
        // 입력 및 그래프 생성 후 도시의 총 개수 반환
        int n = input();
        
        // 만약 간선 정보가 하나도 없다면 0을 출력하고 종료
        if(n == 0) {
            System.out.println(0);
            return;
        }
        
        // DFS 탐색을 위한 visited 배열 초기화
        visited = new boolean[n + 1];

        // 첫 번째 DFS: 입력된 시작 도시(startCity)에서 가장 멀리 떨어진 노드를 찾음
        maxDistance = 0;
        farthestNode = startCity;
        dfs(startCity, 0);

        // 두 번째 DFS를 위해 visited 배열 재초기화
        visited = new boolean[n + 1];
        maxDistance = 0;
        // 첫 번째 DFS에서 얻은 farthestNode에서 다시 DFS 실행
        dfs(farthestNode, 0);

        // 최종적으로 계산된 maxDistance가 트리의 지름(두 도시 사이 최대 거리)이 됨
        System.out.println(maxDistance);
    }

    // input 함수: 입력을 받아 그래프를 구성하고 도시의 총 개수를 반환
    static int input() throws Exception {
        // 임시로 입력된 간선 정보를 저장할 리스트
        ArrayList<int[]> edges = new ArrayList<>();
        int maxNode = 0;
        String line;
        // 입력된 간선이 없을 경우에 대비하여 startCity 초기화 (존재하는 경우에 업데이트됨)
        startCity = -1;
        // EOF까지 한 줄씩 읽음
        while ((line = br.readLine()) != null && !line.trim().equals("")) {
            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{u, v, w});
            // 시작 도시를 첫 번째 간선의 첫 번째 도시로 설정
            if(startCity == -1) {
                startCity = u;
            }
            // 최대 노드 번호 업데이트
            maxNode = Math.max(maxNode, Math.max(u, v));
        }
        
        // 간선 정보가 없다면 0 반환
        if(edges.size() == 0) return 0;
        
        // 도시의 총 개수를 구한 후, 인접 리스트 배열 초기화
        graph = new ArrayList[maxNode + 1];
        for (int i = 1; i <= maxNode; i++) {
            graph[i] = new ArrayList<>();
        }
        // 각 간선 정보를 인접 리스트에 저장 (양방향 그래프)
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        return maxNode;
    }

    // dfs 함수: 현재 노드에서 시작하여 누적 거리를 계산하며 가장 먼 노드를 찾음
    static void dfs(int current, int distance) {
        visited[current] = true;
        // 만약 현재까지의 누적 거리가 maxDistance보다 크다면 갱신
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = current;
        }
        // 현재 노드와 연결된 모든 이웃 노드에 대해 DFS 수행
        for (Edge edge : graph[current]) {
            if (!visited[edge.to]) {
                dfs(edge.to, distance + edge.weight);
            }
        }
    }
}
