package sw_expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 100일 중에 요정이 치즈를 갉아먹고 난 뒤 덩어리가 가장 많을 때의 개수를 출력하는 문제
// 덩어리는 상하좌우로 연결되어있는 경우만 한 덩어리로 인정
// <전략>
// 원본 배열을 입력 받고 매 케이스(하루)마다 요정이 갉아먹고 남은 배열에서 덩어리 개수를 구한다.
// 덩어리 개수는 매 케이스마다 현재 배열의 상태를 반영한 불린 배열을 사용하여 구하고 BFS를 이용해
// 체크한 곳은 false로 바꿔주며 더이상 bfs 탐색이 불가능할 때 덩어리 개수를 하나 추가하는 식으로 구현한다.


public class 치즈도둑 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static boolean[][] isVisited;
	static int[][] cheeze;
	static int size;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int maxValue;
	static int answer;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			print(tc);
		}
		System.out.println(sb);

	}

	private static void solution() {
		// 만약 치즈의 최댓값이 1이면 덩어리의 최대 크기도 1이므로 1 반환
		if (maxValue == 1) {
			answer = 1;
			return;
		}
		// 1부터 100까지 반복
		for (int dailyCase = 1; dailyCase <= 100; dailyCase++) {
			// 각 케이스마다 케이스 번호와 똑같은 부분을 0으로 변경
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (cheeze[row][col] == dailyCase)
						cheeze[row][col] = 0;
				}
			}
			// 변경된 배열에 맞춰 방문 배열도 수정(숫자가 있으면 true, 없으면 false)
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (cheeze[row][col] != 0)
						isVisited[row][col] = true;
				}
			}

			int dailyCount = 0;

			// bfs와 방문 배열을 이용해 상하좌우로 연결된 덩어리 개수 계산
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (isVisited[row][col]) {
						bfs(row, col);
						dailyCount++;
					}
				}
			}
			
			if (dailyCount == 0) return;
			
			// 현재 케이스의 덩어리가 answer보다 많으면 갱신
			answer = Math.max(answer, dailyCount);
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { row, col });
		isVisited[row][col] = false;

		while (!queue.isEmpty()) {
			int[] nowPosition = queue.poll();

			int nx, ny;
			for (int idx = 0; idx < 4; idx++) {
				ny = nowPosition[0] + dy[idx];
				nx = nowPosition[1] + dx[idx];

				// 범위 안 쪽
				if (ny < 0 || ny >= size || nx < 0 || nx >= size)
					continue;
				// 방문한 곳 혹은 치즈가 없는 부위 패스
				if (!isVisited[ny][nx])
					continue;
				// 방문 체크 후 큐에 추가
				isVisited[ny][nx] = false;
				queue.add(new int[] {ny,nx});
			}
		}

	}

	private static void input() throws IOException {
		size = Integer.parseInt(br.readLine());
		answer = 0;

		cheeze = new int[size][size];
		isVisited = new boolean[size][size];

		maxValue = 0;
		
		// 치즈 입력
		for (int row = 0; row < size; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < size; col++) {
				cheeze[row][col] = Integer.parseInt(st.nextToken());
				maxValue = Math.max(maxValue, cheeze[row][col]);
			}
		}

	}

	private static void print(int tc) {
		sb.append("#").append(tc).append(" ").append(answer).append("\n");
	}
}
