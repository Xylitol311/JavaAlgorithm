package sw_expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
N개의 섬들이 주어지고 이 섬들을 잇는데 드는 환경 부담금을 최소로 하기 위해
각 섬들을 거리를 최소로 이을 수 있는 방법을 구해서 최소 환경 부담금을 출력하는 문제

<전략>
최소 신장 트리를 이용해서 각 섬들을 잇고 그 경우의 환경부담금을 구한다.
1. 섬들의 좌표를 입력 받는다.
2. 각 섬에서 다른 섬으로 이어지는 간선을 저장한다.
	2-0. 각 간선의 거리는 (x1-x2)^2 + (y1-y2)^2
	2-1. 간선의 정보는 (시작점, 끝지점, 거리)를 저장한다.
	2-2. 배열에 간선들을 저장하고 거리를 기준으로 오름차순 정렬한다.
3. 간선의 개수가 N-1개가 될 때까지 prim 알고리즘을 이용하여 각 지점의 최소 거리를 
	이용해 최소 신장 트리가 되는 간선을 구한다.
4. 각 간선이 구해질 때 간선의 개수와 총 거리를 갱신하고, 모든 간선을 찾았을 때 총 거리 * 환경세율을 곱하여 출력한다.
 */

public class 하나로_프림 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static boolean[] visited;

	static int islandNum; // 섬의 개수
	static long answer; // 최종 정답 (최소 비용)
	static double envTax; // 환경 부담 세율

	static class Node implements Comparable<Node> {
		int islandNum;
		double distance;

		public Node(int num, double distance) {
			super();
			this.islandNum = num;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			if (this.distance < o.distance)
				return -1;
			else if (this.distance > o.distance)
				return 1;
			return 0;
		}
	}

	static int islandsX[];
	static int islandsY[];

	static void input() throws IOException {
		answer = Integer.MAX_VALUE;
		islandNum = Integer.parseInt(br.readLine());

		islandsX = new int[islandNum];
		islandsY = new int[islandNum];
		visited = new boolean[islandNum];

		st = new StringTokenizer(br.readLine());
		// 섬 정보 입력
		for (int idx = 0; idx < islandNum; idx++) {
			islandsX[idx] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < islandNum; idx++) {
			islandsY[idx] = Integer.parseInt(st.nextToken());
		}

		// 환경 부담 세율 실수
		envTax = Double.parseDouble(br.readLine());
	}

	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static void solution() {
		double cost = 0;
		pq.offer(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (visited[node.islandNum])
				continue;

			visited[node.islandNum] = true;
			cost += node.distance;

			for (int idx = 0; idx < islandNum; idx++) {
				if (visited[idx])
					continue;
				double distance = Math.pow(islandsX[node.islandNum] - islandsX[idx], 2)
						+ Math.pow(islandsY[node.islandNum] - islandsY[idx], 2);
				pq.offer(new Node(idx, distance));
			}
		}
		answer = (long) Math.round(cost * envTax);
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
