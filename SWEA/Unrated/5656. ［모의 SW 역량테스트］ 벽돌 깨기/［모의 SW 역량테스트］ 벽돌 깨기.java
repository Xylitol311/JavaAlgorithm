import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
구슬을 발사하여 벽돌을 깨고 남은 벽돌이 최소가 되는 경우를 찾는다.

1. 구슬 발사
2. 폭발 범위 탐색 -> 폭발 처리
3. 벽돌 내려서 초기화
4. 1~3번을 N번 반복 후 남은 벽돌 개수를 구한다.
5. result 변수에 최솟값을 비교하여 저장.
모든 경우의 수를 탐색하고 마지막에 result를 출력
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int result, bead, width, height, field[][], maxBlock;

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		bead = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		result = Integer.MAX_VALUE;
		maxBlock = 0;

		field = new int[height][width];

		for (int row = 0; row < height; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < width; col++) {
				field[row][col] = Integer.parseInt(st.nextToken());
				if (field[row][col] != 0)
					maxBlock++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	static List<int[]> perm;

	private static void solution() {
		perm = new ArrayList<>();
		// 중복순열을 이용해 발사할 컬럼 순서 구하기
		permutation(0, new int[bead]);

		// 완성된 중복순열 경우의 수를 차례대로 탐색
		for (int[] cols : perm) {
			// 임시 배열 복사
			copy = new int[height][];
			for (int row = 0; row < height; row++) {
				copy[row] = Arrays.copyOf(field[row], width);
			}

			// 중복순열 결과대로 구슬 발사 (남은 벽돌 수 반환)
			int cnt = fireBead(cols);

			// 최솟값 업데이트
			result = Math.min(result, maxBlock - cnt);
		}
	}

	private static void permutation(int selectedCnt, int[] selected) {
		// 구슬 개수만큼 열을 골랐으면 perm에 저장
		if (selectedCnt == bead) {
			perm.add(Arrays.copyOf(selected, bead));
			return;
		}

		for (int col = 0; col < width; col++) {
			selected[selectedCnt] = col;
			permutation(selectedCnt + 1, selected);
		}
	}

	private static int fireBead(int[] cols) {
		int removeBlockCnt = 0;
		for (int col : cols) {
			// 위에서부터 탐색하여 0이 아닌 블럭 찾기
			int startRow;
			for (startRow = 0; startRow < height; startRow++) {
				if (copy[startRow][col] != 0)
					break;
			}
			// 해당 열에 블록이 하나도 없는 경우
			if (startRow == height) continue;
			// 구슬 발사 BFS
			removeBlockCnt += bfs(startRow, col);

			// 맵 업데이트
			mapUpdate();
		}
		// 남은 벽돌 계산
		return removeBlockCnt;
	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] copy;

	private static int bfs(int y, int x) {
		// 파괴한 블럭 개수
		int cnt = 0;
		Queue<Block> queue = new ArrayDeque<>();

		// y좌표, x좌표, 힘의 방향, 남은 힘
		queue.add(new Block(y, x, copy[y][x] - 1));

		while (!queue.isEmpty()) {
			// 삭제할 블럭 가져오기
			Block now = queue.poll();

			// 블럭의 값이 0이면 바로 통과
			if (copy[now.y][now.x] == 0)
				continue;

			// 블럭 삭제 처리
			copy[now.y][now.x] = 0;
			cnt++;

			// 현재 블럭의 파워가 0이면 다른 블럭을 부술 수 없으므로 통과
			if (now.power == 0)
				continue;

			// 사방 탐색
			for (int direction = 0; direction < 4; direction++) {
				int ny = now.y;
				int nx = now.x;

				for (int next = 0; next < now.power; next++) {
					ny = ny + dy[direction];
					nx = nx + dx[direction];

					// 배열을 벗어나는 경우 패스
					if (ny < 0 || ny >= height || nx < 0 || nx >= width)
						continue;

					queue.add(new Block(ny, nx, copy[ny][nx] - 1));
				}
			}
		}

		return cnt;
	}

	private static void mapUpdate() {
		for (int col = 0; col < width; col++) {
			for (int row = height - 1; row > 0; row--) {
				// 현재 값이 0인 경우
				if (copy[row][col] == 0) {
					int nowR = row;
					// 위쪽에 0이 아닌 값을 찾아서 현재 위치에 저장
					while (nowR > 0) {
						if (copy[--nowR][col] != 0) {
							break;
						}
					}
					// 만약 첫째 줄까지 0인 경우 현재 열 탐색 종료
					if (nowR == 0 && copy[nowR][col] == 0) {
						break;
					}

					copy[row][col] = copy[nowR][col];
					copy[nowR][col] = 0;
				}
			}
		}
	}

	static class Block {
		int y, x, power;

		public Block(int y, int x, int power) {
			super();
			this.y = y;
			this.x = x;
			this.power = power;
		}

	}
}