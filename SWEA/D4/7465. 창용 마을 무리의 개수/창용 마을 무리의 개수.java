import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
서로소 집합을 이용해 관계를 각각의 집합으로 정리하고
마지막에 루트 노드가 몇개인지 출력
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] parents;
	static int maxSize;
	static int operationNum;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			print(tc);
		}
		System.out.println(sb);

	}

	// union 함수를 이용해 관계를 정의하고 마지막에 루트 노드 개수를 출력
	private static void solution() throws IOException {
		for (int taskNum = 0; taskNum < operationNum; taskNum++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
			
		}
		
		// 모든 위치에 대해 find연산 수행
		for (int idx = 1; idx <= maxSize; idx++) {
			findSet(idx);
		}
		
		// 루트 노드가 몇개인지 탐색
		for (int idx = 1; idx <= maxSize; idx++) {
			if (idx == parents[idx]) answer++;
		}
	}

	// findSet 연산
	private static int findSet(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = findSet(parents[a]);
	}

	// union 연산
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		maxSize = Integer.parseInt(st.nextToken());
		operationNum = Integer.parseInt(st.nextToken());

		answer = 0;
		
		parents = new int[maxSize + 1];

		for (int idx = 1; idx <= maxSize; idx++) {
			parents[idx] = idx;
		}
	}

	private static void print(int tc) {
		sb.append("#").append(tc).append(" ").append(answer).append("\n");
	}

}