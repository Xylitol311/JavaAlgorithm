import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
2차원 배열 안에 격리된 미생물 군집의 M시간 후 각 군집의 이동방향, 미생물 수, 합체 등의 상태를 표현하는 문제

<전략>
- 이동: 군집의 이동방향으로 한 칸 이동. 이전 위치는 null로 변경. 만약 이동한 위치가 가장자리면 미생물 수를 나누기 2(홀수면 -1 후 나누기)
- 이동 좌표가 겹치는 경우:
	- 만약 특정 군집이 이동했는데 해당 위치가 null이 아닌 경우 먼저 있던 군집의 다음 노드로 방금 이동한 군집을 추가
	- 전체 이동 작업이 끝난 후 nextNode가 null이 아닌 군집을 찾아 각각의 노드를 비교하며 미생물의 합계를 구하고 각 군집의
	   최대 미생물 수를 기준으로 방향을 수정.
	- 수정된 데이터는 그 위치에 가장 먼저 들어온 데이터에 업데이트하고 nextNode를 null로 만들어 연결을 제거.
	- 가장 먼저 들어온 데이터는 간편하게 해당 구문 내에서 startNode로 따로 설정해서 사용
- 전체 과정을 M번 동안 반복 후 남아있는 미생물 수의 총합 구하기
	- 처음 입력 받을 때 전체 미생물 수를 구해놓고 가장 자리를 밟는 군집이 생길 때마다 줄어드는 수만큼 전체 미생물 수의 총합도 수정
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static Node[][] map; // Node의 위치 표시
	static Node[] nodes; // Node 정보를 빠르게 조회하고 수정하기 위한 배열

	static int mapSize; // 격리구역 사이즈
	static int timeM; // 격리 시간
	static int microorganism; // 미생물 군집 수
	static int answer; // 전체 미생물 수

	static class Node {
		int num;
		int row;
		int col;
		int microCnt; // 미생물 수
		int direction; // 방향
		Node nextNode; // 다른 군집과 겹치는 경우 그 군집을 저장하기 위한 변수

		public Node(int num, int row, int col, int microCnt, int direction) {
			super();
			this.num = num;
			this.row = row;
			this.col = col;
			this.microCnt = microCnt;
			this.direction = direction;
		}

	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			input();
			for (int time = 0; time < timeM; time++) {
				solution();
			}
			print(tc);
		}
		System.out.println(sb);

	}

	private static void solution() {
		// 모든 군집 이동
		for (Node node : nodes) {
			if (node == null)
				continue;

			int ny = node.row + dy[node.direction];
			int nx = node.col + dx[node.direction];

			// 해당 위치가 가장 자리인 경우 방향 전환 및 개체 수 절반 삭제
			if (ny == 0 || ny == mapSize - 1 || nx == 0 || nx == mapSize - 1) {
				// 방향 순서는 상하좌우. 짝수인 경우 +1, 홀수인 경우 -1을 하면 반대 방향으로 전환됨
				node.direction = ((node.direction % 2) == 1) ? --node.direction : ++node.direction;

				// 미생물 수가 홀수인 경우 나누기 2하고 버림(== 미리 -1하고 나누기 2)
				int minus;
				if (node.microCnt % 2 == 1) {
					minus = node.microCnt / 2 + 1;
				} else
					minus = node.microCnt / 2;
				node.microCnt -= minus;

				// 줄어든 것만큼 정답 갱신
				answer -= minus;
			}

			// 위치 이동 전 처리
			if (node.nextNode != null) {// 아직 이동하지 않은 군집에 다른 군집이 겹쳐진 경우를 고려
				map[node.row][node.col] = node.nextNode;
				node.nextNode = null;
			} else
				map[node.row][node.col] = null;

			// 이동할 위치에 다른 군집이 있으면 거기에 현재 노드 추가. 아니면 현재 노드의 위치 변경
			if (map[ny][nx] != null) {
				node.nextNode = map[ny][nx].nextNode;
				map[ny][nx].nextNode = node;
			} else
				map[ny][nx] = node;

			// 현재 노드의 좌표 변경
			node.row = ny;
			node.col = nx;
		}

		// 겹친 군집 있는 지 확인 후 수정
		for (Node nowNode : nodes) {
			if (nowNode == null)
				continue;
			if (nowNode.nextNode == null)
				continue;

			// 겹친 군집 중 최대 미생물 수
			int maxMicroCnt = nowNode.microCnt;
			// 겹친 군집 중 최대 미생물 수 군집의 방향
			int maxNodeDirection = nowNode.direction;

			Node nextNode = nowNode.nextNode;

			while (nextNode != null) {
				// 겹친 위치의 가장 첫 군집에 미생물 수 몰아주기
				nowNode.microCnt += nextNode.microCnt;
				// 겹친 군집 중 최대 미생물 수 갱신
				maxMicroCnt = Math.max(maxMicroCnt, nextNode.microCnt);
				// 겹친 군집 중 최대 미생물 수의 방향으로 갱신
				maxNodeDirection = (maxMicroCnt == nextNode.microCnt) ? nextNode.direction : maxNodeDirection;
				// 겹쳐진 노드는 노드 목록에서 제거
				nodes[nextNode.num] = null;
				// 다음 겹친 군집
				nextNode = nextNode.nextNode;
			}

			nowNode.direction = maxNodeDirection;
			nowNode.nextNode = null;
		}

	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());

		mapSize = Integer.parseInt(st.nextToken()); // 맵 사이즈
		timeM = Integer.parseInt(st.nextToken()); // M: 시간
		microorganism = Integer.parseInt(st.nextToken()); // 미생물 군집 수
		answer = 0;

		map = new Node[mapSize][mapSize];
		nodes = new Node[microorganism];

		for (int micro = 0; micro < microorganism; micro++) {
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int microCnt = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			answer += microCnt;

			Node node = new Node(micro, row, col, microCnt, direction - 1);

			nodes[micro] = node;
			map[row][col] = node;
		}
	}

	private static void print(int tc) {
		sb.append("#").append(tc).append(" ").append(answer).append("\n");
	}

}
