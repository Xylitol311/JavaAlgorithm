import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1. 크루스칼 알고리즘을 이용해 (정점 - 1)까지의 간선 가중치를 입력 받는다.
2. 이렇게 최소한의 유지비로 이어지는 마을을 하나 만들고 마을을 분리한다.
3. 집을 하나만 분리해도 마을을 분리하는 것과 같으므로 유지비가 가장 높은 경로를 하나 제거한다.
4. 남은 경로의 가중치를 계산하여 출력한다.
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int V, E;
	static int[] parents;
	static PriorityQueue<Edge> pq;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(start, end, weight));
		}

		parents = new int[V + 1];
		Arrays.fill(parents, -1);
	}

	public static void main(String[] args) throws IOException {
		input();

		int cnt = 0, cost = 0, lastWeight = 0;
		while (cnt < V - 1) {
			Edge edge = pq.poll();
			if (union(edge.start, edge.end)) {
				cost += edge.weight;
				cnt++;
				lastWeight = edge.weight;
			}
		}

		System.out.println(cost - lastWeight);

	}

	static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]); // 집합의 대표자를 자신의 부모로 변경(경로 압축)
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		if (parents[aRoot] <= parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		return true;
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