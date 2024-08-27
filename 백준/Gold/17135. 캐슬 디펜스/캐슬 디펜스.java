
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	static Scanner sc;

	static int rowSize;
	static int colSize;
	static int bowRange;
	static int[][] gameBoard;
	static int[][] copyBoard;

	static final int ARCHER_COUNT = 3;

	static final int ENEMY = 1;
	static final int EMPTY = 0;

	static int maxEnemy;

	public static void init() {
		rowSize = sc.nextInt();
		colSize = sc.nextInt();
		bowRange = sc.nextInt();

		gameBoard = new int[rowSize][colSize];
		copyBoard = new int[rowSize][colSize];
		for (int rowIndex = 0; rowIndex < rowSize; ++rowIndex) {
			for (int colIndex = 0; colIndex < colSize; ++colIndex) {
				gameBoard[rowIndex][colIndex] = sc.nextInt();
			}
		}

		archerRow = rowSize;
		archers = new int[ARCHER_COUNT];
		maxEnemy = 0;
	}

	public static int[] archers;
	public static int archerRow;

	public static void setArcher(int colIndex, int selectArcher) {
		// 4. 궁수가 자리할 위치를 구했으면 게임을 진행한다
		if (selectArcher == ARCHER_COUNT) {
			// 게임판을 복사해서 넣어주자
			archerRow = rowSize;
			for (int row = 0 ; row < rowSize; ++row) {
				for (int col = 0; col < colSize; ++col) {
					copyBoard[row][col] = gameBoard[row][col];
				}
			}
			maxEnemy = Math.max(maxEnemy, playGame(copyBoard));
			return;
		}
		if (colIndex == colSize) {
			return;
		}

		// 3. 궁수가 자리할 위치를 구한다 15C3
		archers[selectArcher] = colIndex;
		setArcher(colIndex + 1, selectArcher + 1);
		setArcher(colIndex + 1, selectArcher);
	}

	static Position[] targetEnemy;

	public static int playGame(int[][] board) {
		int killedEnemy = 0;
		// 4-4. 적이 모두 제외될 때까지 반복 궁수가 0번째 행에 도착하는것과 같다
		while (archerRow > 0) {
			// 4-1. 각 궁수에 대해서 bfs로 화살 범위 안의 가장 가까운 적을 찾는다.
			targetEnemy = new Position[ARCHER_COUNT];
			for (int archer = 0; archer < ARCHER_COUNT; ++archer) {
				findEnemy(archerRow, archer, board);
			}
			// 4-2. 적을 제거한다.
			for (int enemy = 0; enemy < targetEnemy.length; ++enemy) {
				if (targetEnemy[enemy] == null) {
					continue;
				}
				if (board[targetEnemy[enemy].row][targetEnemy[enemy].col] == ENEMY) {
					board[targetEnemy[enemy].row][targetEnemy[enemy].col] = EMPTY;
					killedEnemy++;
				}
			}
			// 4-3. 적이 움직인다. 궁수가 한 칸 전진하는 것과 같다
			archerRow--;
		}
		return killedEnemy;
	}

	static class Position {
		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	// 화살은 상 우 좌로만 쏘게끔
	static final int[] ROW_DELTA = { 0, -1, 0 };
	static final int[] COL_DELTA = { -1, 0, 1 };

	public static void findEnemy(int archerRow, int archer, int[][] board) {
		boolean[][] visited = new boolean[archerRow][colSize];
	
		Queue<Position> queue = new ArrayDeque<>();
		queue.add(new Position(archerRow, archers[archer]));

		int range = 0;

		while (!queue.isEmpty()) {
			if (range >= bowRange) {
				queue.clear();
				break;
			}
			int size = queue.size();
			while (--size >= 0) {
				Position currentPosition = queue.poll();
				int currentRow = currentPosition.row;
				int currentCol = currentPosition.col;
				for (int delta = 0; delta < ROW_DELTA.length; ++delta) {
					int nextRow = currentRow + ROW_DELTA[delta];
					int nextCol = currentCol + COL_DELTA[delta];

					if (nextRow < 0 || nextRow >= archerRow || nextCol < 0 || nextCol >= colSize) {
						continue;
					}

					if (visited[nextRow][nextCol]) {
						continue;
					}

					if (board[nextRow][nextCol] == ENEMY) {
						queue.clear();
						targetEnemy[archer] = new Position(nextRow, nextCol);
						return;
					}
					visited[nextRow][nextCol] = true;
					queue.add(new Position(nextRow, nextCol));
				}
			}
			range++;
		}
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		init();
		setArcher(0, 0);
		System.out.println(maxEnemy);
	}
}
