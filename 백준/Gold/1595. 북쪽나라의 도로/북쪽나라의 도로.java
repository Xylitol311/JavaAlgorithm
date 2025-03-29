// 메모리: 00000 KB / 시간: 00 ms
import java.io.*;
import java.util.*;

/*
 * 1. 도시와 간선 정보 입력 후 임의의 노드에서 가장 멀리 떨어진 노드를 먼저 찾는다.
 * 	- DFS 탐색을 통해서 거리와 함께  가장 먼 노드를 저
 * 2. 첫번째 dfs 탐색에서 얻은 가장 멀리 떨어진 노드에서 다시 가장 멀리 떨어진 노드를 찾는다
 * 	- 여기서 찾아진 가장 멀리 떨어진 노드가 트리의 지름이 
 * 3. 찾은 트리의 지름 출
 */
class Edge {
	int to;
	int weight;

	public Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static ArrayList<Edge>[] graph;

	static boolean[] visited;
	static int maxDistance; // 현재까지 최대 누적 거리
	static int farthestNode; // 최대 누적 거리를 갖는 노드 번호

	static int startCity; 	// DFS 시작 도시 번호


	static int input() throws Exception {
		ArrayList<int[]> edges = new ArrayList<>(); // 간선 정보 저
		int maxNode = 0;
		String line;
		
		startCity = -1; // 간선이 없을 경우
		
		while ((line = br.readLine()) != null && !line.trim().equals("")) {
			st = new StringTokenizer(line);
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new int[] { u, v, w });
			
			// 시작 도시를 첫 번째 간선의 첫 번째 도시로 설정
			if (startCity == -1) {
				startCity = u;
			}
			
			maxNode = Math.max(maxNode, Math.max(u, v));
		}

		if (edges.size() == 0)
			return 0;

		graph = new ArrayList[maxNode + 1];
		for (int i = 1; i <= maxNode; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1], w = edge[2];
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		return maxNode;
	}

	public static void main(String[] args) throws Exception {
		int n = input();

		// 간선 정보가 하나도 없다면 종료
		if (n == 0) {
			System.out.println(0);
			return;
		}

		visited = new boolean[n + 1];

		// 첫 번째 DFS: 입력된 시작 도시(startCity)에서 가장 멀리 떨어진 노드를 찾음
		maxDistance = 0;
		farthestNode = startCity;
		dfs(startCity, 0);

		visited = new boolean[n + 1];
		maxDistance = 0;
		
		// 첫 번째 DFS에서 얻은 farthestNode에서 다시 DFS 실행
		dfs(farthestNode, 0);

		// 최종적으로 계산된 maxDistance가 트리의 지름(두 도시 사이 최대 거리)이 됨
		System.out.println(maxDistance);
	}

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
