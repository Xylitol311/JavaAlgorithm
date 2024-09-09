package sw_expert;

import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimPQTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); // 정점 수
		int[][] adjMatrix = new int[V][V];
		boolean[] visited = new boolean[V]; // 방문여부 배열
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 자신과 타접점들간의 간선비용 중 최소 간선 비용
		int cost = 0;

		// 배열 입력
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = sc.nextInt();
			}
		}

		pq.offer(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (visited[node.num])
				continue;

			visited[node.num] = true;
			cost += node.cost;

			for (int i = 0; i < adjMatrix[node.num].length; i++) {
				if (!visited[i] && adjMatrix[node.num][i]> 0) {
					pq.offer(new Node(i, adjMatrix[node.num][i]));
				}
			}

		}

		System.out.println(cost);
	}

	static class Node implements Comparable<Node> {
		int num, cost;

		public Node(int num, int cost) {
			super();
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
/*
 * 5 0 5 10 8 7 5 0 5 3 6 10 5 0 1 3 8 3 1 0 1 7 6 3 1 0
 * 
 * output==>10
 * 
 * 7 0 32 31 0 0 60 51 32 0 21 0 0 0 0 31 21 0 0 46 0 25 0 0 0 0 34 18 0 0 0 46
 * 34 0 40 51 60 0 0 18 40 0 0 51 0 25 0 51 0 0
 * 
 * output==>175
 */
