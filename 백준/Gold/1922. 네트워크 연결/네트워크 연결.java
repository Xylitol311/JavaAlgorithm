import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
컴퓨터의 개수와 연결된 선 정보가 주어지고 이를 통해 만들 수 있는 연결하는데 드는 최소 비용(최소 스패닝 트리의 가중치)를 출력하는 문제
크루스칼 알고리즘을 사용해서 최소 스패닝 트리를 구한다.


 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int computerNum;
	static int[] parents;
	static Line[] lines;

	static void make() {
		parents = new int[computerNum];
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
		input();
		int answer = solution();
		System.out.println(answer);
	}

	static int solution() {
		int result = 0;
		
		make();

		int cnt = 0; 

		for (Line edge : lines) {
			if (union(edge.start, edge.end)) {
				result += edge.weight;
				if (++cnt == computerNum - 1)
					break;
			}
		}
		return result;
	}

	// input
	static void input() throws IOException{
		computerNum = Integer.parseInt(br.readLine());
		int lineNum = Integer.parseInt(br.readLine());

		lines = new Line[lineNum];

		// 간선 입력
		for (int i = 0; i < lineNum; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			lines[i] = new Line(start-1, end-1, weight);
		}

		// 간선 가중치 기준 오름차순 정렬
		Arrays.sort(lines);
	}
	
	static class Line implements Comparable<Line> {
		int start, end, weight;

		public Line(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
