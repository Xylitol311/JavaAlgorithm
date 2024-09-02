import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static boolean[][] check;
	static int width;
	static int height;
	static int icedAreaNum;

	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());

		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		icedAreaNum = Integer.parseInt(st.nextToken());

		check = new boolean[height][width];

		for (int idx = 0; idx < icedAreaNum; idx++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			checkArea(x1, y1, x2, y2);
		}
	}

	public static void checkArea(int x1, int y1, int x2, int y2) {
		for (int row = y1; row < y2; row++) {
			for (int col = x1; col < x2; col++) {
				if (check[row][col])
					continue;
				check[row][col] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solution();
		sb.append(cntList.size()).append("\n");
		for (int idx = 0; idx < cntList.size(); idx++) {
			sb.append(cntList.get(idx)).append(" ");
		}
		System.out.println(sb);
	}

	static List<Integer> cntList;

	public static void solution() {
		cntList = new ArrayList<>();

		// bfs를 통해 빈 공간 크기 파악 및 cntList에 입력
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (check[row][col])
					continue;
				bfs(row, col);

			}
		}
		
		// cntList 정렬
		Collections.sort(cntList);
	}

	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0, };

	public static void bfs(int row, int col) {
		int cnt = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { row, col });
		check[row][col] = true;
		cnt++;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowY = now[0];
			int nowX = now[1];

			for (int direction = 0; direction < 4; direction++) {
				int ny = nowY + dy[direction];
				int nx = nowX + dx[direction];
				
				// 배열 범위 밖
				if (ny < 0 || ny >= height || nx < 0 || nx >= width)
					continue;
				// 이미 방문한 곳
				if (check[ny][nx])
					continue;
				// 입력
				check[ny][nx] = true;
				queue.add(new int[] {ny,nx});
				cnt++;
			}
		}
		cntList.add(cnt);
	}
}
