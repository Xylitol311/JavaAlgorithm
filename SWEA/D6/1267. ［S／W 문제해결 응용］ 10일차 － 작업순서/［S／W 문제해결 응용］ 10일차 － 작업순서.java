
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
노드와 간선 정보를 입력받고
정보를 입력할 때 진입 차수에 대한 정보를 담은 배열을 만들어 진입 차수도 함께 저장한다.

1. 진입 차수가 0인 노드들을 큐에 저장하고
2. 큐에서 노드 하나를 추출하여 인접한 노드들의 진입 차수를 한개씩 줄인다.
3. 이때 진입 차수가 0이 된 노드들을 큐에 저장한다.
4. 큐가 비어있을 때까지 진행하며 이 문제는 사이클이 존재하지 않다고 가정하고 있으므로 사이클은 고려하지 않는다.
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] inDegree;
	static Node[] nodes;

	static int vertex;
	static int edge;

	static class Node {
		int to;
		Node nextNode;

		public Node(int num, Node nextNode) {
			super();
			this.to = num;
			this.nextNode = nextNode;
		}
	}

	public static void main(String[] args) throws IOException {
		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			input();
			String answer = solution();
			print(tc, answer);
		}
		System.out.println(sb);

	}

//	1. 진입 차수가 0인 노드들을 큐에 저장하고
//	2. 큐에서 노드 하나를 추출하여 인접한 노드들의 진입 차수를 한개씩 줄인다.
//	3. 이때 진입 차수가 0이 된 노드들을 큐에 저장한다.
//	4. 큐가 비어있을 때까지 진행하며 이 문제는 사이클이 존재하지 않다고 가정하고 있으므로 사이클은 고려하지 않는다.
	private static String solution() {
		StringBuilder answer = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();// 노드 번호(인덱스)를 저장하는 큐
		
		// 진입 차수가 0인 노드 번호 큐에 저장
		for (int idx = 1; idx <= vertex; idx++) {
			if (inDegree[idx] == 0) queue.add(idx);
		}
		
		// 사이클이 존재하지 않는 문제라서 큐가 빌 때까지 위 주석의 2, 3번 반복
		while(!queue.isEmpty()) {
			int nodeNum = queue.poll();
			answer.append(nodeNum).append(" ");
			
			// 현재 노드와 인접한 노드의 진입 차수를 한 개 줄이고 0이 되면 큐에 추가
			// 현재 노드에서 연결된 다음 노드가 null일 때까지 반복
			for(Node node = nodes[nodeNum]; node != null; node = node.nextNode) {
				if (--inDegree[node.to] == 0)
					queue.add(node.to);
			}
		}
		
		return answer.toString().trim();
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		vertex = Integer.parseInt(st.nextToken());
		edge = Integer.parseInt(st.nextToken());

		inDegree = new int[vertex + 1];
		nodes = new Node[vertex + 1];

		st = new StringTokenizer(br.readLine());

		// 노드 입력
		for (int idx = 0; idx < edge; idx++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 노드의 nextNode를 이용해서 다음 노드를 연결하여 연결리스트로 구현
			// 1 -> 5, 1 -> 2 입력 시
			// nodes[1] = new Node(5, null);
			// nodes[1] = new Node(2, nodes[1]);
			// nodes[1]은 2번 -> 5번 -> null 형태로 연결됨
			nodes[from] = new Node(to, nodes[from]);
			inDegree[to]++;
		}
	}

	private static void print(int tc, String answer) {
		sb.append("#").append(tc).append(" ").append(answer).append("\n");
	}

}
