import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <풀이 방법>
 * 1. 테스트케이스 및 배열 정보를 입력 받는다.
 * 2. 서비스의 영역을 나타내는 k를 1부터 모든 집을 포함했을 때 손해를 보지 않는 크기까지 키우면서 배열을 탐색한다.
 * 		2-1. 손해를 보지 않는 최대 크기 : 모든 집의 개수 * M >= K^2 + (K-1)^2 를 만족하는 K
 * 3. 각 K 사이즈별로 모든 배열을 탐색하며 운영비용 이상의 수익을 얻을 수 있는 경우의 집의 개수를 구하고 그 중 최댓값을 answer 변수에 갱신한다.
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int fieldSize, homeCost;
	static int[][] map;
	static boolean[][] visited;
	static int maxHouse;

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		fieldSize = Integer.parseInt(st.nextToken());
		homeCost = Integer.parseInt(st.nextToken());

		map = new int[fieldSize][fieldSize];
		maxHouse = 0;

		for (int row = 0; row < fieldSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < fieldSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1)
					maxHouse++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			sb.append("#").append(tc).append(" ").append(maxHomeCnt).append("\n");
		}
		System.out.println(sb);
	}

	static int maxHomeCnt;
	static int serviceArea;

	private static void solution() {
		maxHomeCnt = 0;
		serviceArea = 1;
		int maxServiceArea = 1;
		// 늘릴 수 있는 최대 K 계산
		while (Math.pow(maxServiceArea, 2) + Math.pow(maxServiceArea - 1, 2) <= maxHouse * homeCost) {
			maxServiceArea++;
		}

		// K를 1부터 maxServiceArea보다 작은 최댓값까지 반복
		while (serviceArea < maxServiceArea) {
			for (int row = 0; row < fieldSize; row++) {
				for (int col = 0; col < fieldSize; col++) {
					bfs(new int[] { row, col });
				}
			}
			serviceArea++;
		}

	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	// 배열을 탐색
	private static void bfs(int[] start) {
		// 현재 서비스 영역 운영 비용
		int nowAreaCost = (int) (Math.pow(serviceArea, 2) + Math.pow(serviceArea - 1, 2));
		int homeCnt = 0; // 현재 영역 내 집의 개수
		int selectCnt = 0; // 현재까지 선택된 칸
		visited = new boolean[fieldSize][fieldSize];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;
		if (map[start[0]][start[1]]== 1) homeCnt++;

		while (!queue.isEmpty()) {
			// 남은 칸의 개수가 전부 집이어도 적자인 경우 종료
			if ((nowAreaCost - selectCnt) * homeCost + homeCnt * homeCost < nowAreaCost) {
				return;
			}

			int[] now = queue.poll();
			int row = now[0];
			int col = now[1];

			for (int direction = 0; direction < 4; direction++) {
				int nr = row + dy[direction];
				int nc = col + dx[direction];

				// 배열 밖인 경우
				if (nr < 0 || nr >= fieldSize || nc < 0 || nc >= fieldSize)
					continue;

				// 거리를 넘은 경우
				if (calDistance(start, new int[] {nr,nc}) > serviceArea - 1) continue;

				// 방문한 곳인 경우
				if (visited[nr][nc]) continue;
				
				// 집인 경우
				if (map[nr][nc]== 1) homeCnt++;
				
				// 큐에 추가
				queue.add(new int[] {nr,nc});
				visited[nr][nc] = true;
				selectCnt++;
			}

		}

		// 손해가 나지 않은 경우 집 최댓값 갱신
		if (homeCnt * homeCost >= nowAreaCost) {
			maxHomeCnt = Math.max(maxHomeCnt, homeCnt);
		}

	}

	private static int calDistance(int[] start, int[] end) {
		return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
	}
}