
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
정점의 개수와 간선 정보가 주어지고 이를 통해 만들 수 있는 최소 스패닝 트리의 가중치를 출력하는 문제
크루스칼 알고리즘을 사용해서 최소 스패닝 트리를 구한다.
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	static int vertexNum;
	static int[] parents;
	static Edge[] edges;

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
		make();

		int cnt = 0; 

		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				answer += edge.weight;
				if (++cnt == vertexNum - 1)
					break;
			}
		}
	}

	// input
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		vertexNum = Integer.parseInt(st.nextToken());
		int edgeNum = Integer.parseInt(st.nextToken());
		answer = 0;

		edges = new Edge[edgeNum];

		// 간선 입력
		for (int i = 0; i < edgeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(start-1, end-1, weight);
		}

		// 간선 가중치 기준 오름차순 정렬
		Arrays.sort(edges);
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
