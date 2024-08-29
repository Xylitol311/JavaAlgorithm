import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
3. 간선의 개수가 N-1개가 될 때까지 union,find를 이용하여 각 지점의 최소 거리를 
	이용해 최소 신장 트리가 되는 간선을 구한다.
4. 각 간선이 구해질 때 간선의 개수와 총 거리를 갱신하고, 모든 간선을 찾았을 때 총 거리 * 환경세율을 곱하여 출력한다.

 */

public class Solution {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	static List<Edge> edges; // 섬의 간선 정보 저장
	static int[] parents; // 서로소 집합의 루트를 저장하는 배열                                        
	
	static int islandNum; // 섬의 개수
	static double answer; // 최종 정답
	static double envTax; // 환경 부담 세율
	//===================================================
	// union-find
	static void make() {
		parents = new int[islandNum];
		Arrays.fill(parents, -1);
	}
	
	static int findSet(int a) {
		if (parents[a] < 0) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		if (parents[aRoot] < parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		return true;
	}
	//====================================================
	// main solution
	
	public static void main(String[] args) {
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			answer(tc);
		}
		System.out.println(sb);
	}
	
	static void solution() {
		make();

		int cnt = 0, cost = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				answer += edge.distance;
				if (++cnt == islandNum - 1)
					break;
			}
		}
		
		answer = Math.round(answer * envTax);
	}
	
	static class Edge implements Comparable<Edge> {
		int start, end;
		double distance;

		public Edge(int start, int end, double distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Double.compare(this.distance, o.distance);
		}
	}
	
	// =================================================
	// IO
	
	static void input() {
		answer = 0;
		islandNum = sc.nextInt();
		
		int islandsX[] = new int[islandNum];
		int islandsY[] = new int[islandNum];
		edges = new ArrayList<>();
		
		// 섬 정보 입력
		for (int idx = 0; idx < islandNum; idx++) {
			islandsX[idx] = sc.nextInt();
		}
		for (int idx = 0; idx < islandNum; idx++) {
			islandsY[idx] = sc.nextInt();
		}
		
		// 환경 부담 세율 실수
		envTax = sc.nextDouble();
		
		// 간선 정보 입력
		boolean[][] visited = new boolean[islandNum][islandNum];
		for (int startIdx = 0; startIdx < islandNum; startIdx++) {
			for (int endIdx = 0; endIdx < islandNum; endIdx++) {
				// 자기 자신이면 패스
				if (startIdx == endIdx) continue;
				// 입력한 간선이면 패스
				if (visited[startIdx][endIdx]) continue;
				
				// 방문 처리(양방향)
				visited[startIdx][endIdx] = true;
				visited[endIdx][startIdx] = true;
				
				// 섬 간선 입력
				double distance = Math.pow(islandsX[startIdx]-islandsX[endIdx], 2) + 
						Math.pow(islandsY[startIdx]-islandsY[endIdx], 2);
				edges.add(new Edge(startIdx, endIdx, distance));
			}	
		}
		// 섬 오름차순 정렬
		Collections.sort(edges);	
	}
	
	static void answer(int tc) {
		sb.append("#").append(tc).append(" ").append((long)answer).append("\n");
	}
}
