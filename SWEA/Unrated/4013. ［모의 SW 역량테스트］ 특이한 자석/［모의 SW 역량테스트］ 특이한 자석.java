import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
각 자석의 빨간 화살표, 왼쪽, 오른쪽 접점을 포인터를 활용해 관리한다.
각 자석의 포인터는 포인터 클래스를 만들어 관리하고 회전 방향 횟수를 입력하면 포인터를 수정하는 메서드를 구현한다.
자석 정보와 명령어를 입력받고 명령어에 따라 자석을 회전시킨다.
 
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int orderCnt;
	static Magnet[] magnets;

	static class Magnet {
		int top, left, right, magnet[];

		public Magnet(int top, int left, int right, int[] magnet) {
			this.top = top;
			this.left = left;
			this.right = right;
			this.magnet = magnet;
		}

		public void rotate(int direction) {
			top = (top + direction + 8) % 8;
			left = (left + direction + 8) % 8;
			right = (right + direction + 8) % 8;
		}
	}

	private static void input() throws IOException {
		orderCnt = Integer.parseInt(br.readLine().trim());

		magnets = new Magnet[4];

		// 자석 정보 및 포인터 입력
		for (int mgNum = 0; mgNum < magnets.length; mgNum++) {
			st = new StringTokenizer(br.readLine().trim());
			int[] tmpMg = new int[8];
			for (int idx = 0; idx < tmpMg.length; idx++) {
				tmpMg[idx] = Integer.parseInt(st.nextToken());
			}
			magnets[mgNum] = new Magnet(0, 6, 2, tmpMg);
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			for (int i = 0; i < orderCnt; i++) {
				solution();
			}
			print(tc);
		}
		System.out.println(sb);
	}

	private static void solution() throws IOException {
		st = new StringTokenizer(br.readLine());
		int magNum = Integer.parseInt(st.nextToken()) - 1;
		int direction = -Integer.parseInt(st.nextToken());

		// 회전 시킬 자석 및 방향 체크
		int[] checkMagnet = new int[4];
		checkMagnet[magNum] = direction;

		// 맞물려 돌아가는 자석을 큐를 이용해서 체크
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { magNum, direction });

		while (!queue.isEmpty()) {
			int[] nowMag = queue.poll();
			// 1. 맞물린 자석이 다른 극인지 확인.
			// 2. 다른 극이라면 checkMagnet에 현재의 반대방향으로 체크하고 큐에 저장

			// 왼쪽 자석 확인
			if (nowMag[0] > 0 && checkMagnet[nowMag[0] - 1] == 0) {
				Magnet now = magnets[nowMag[0]];
				Magnet left = magnets[nowMag[0] - 1];
				if (left.magnet[left.right] != now.magnet[now.left]) {
					checkMagnet[nowMag[0] - 1] = nowMag[1] * -1;
					queue.add(new int[] { nowMag[0] - 1, nowMag[1] * -1 });
				}
			}

			// 오른쪽 자석 확인
			if (nowMag[0] < 3 && checkMagnet[nowMag[0] + 1] == 0) {
				Magnet now = magnets[nowMag[0]];
				Magnet right = magnets[nowMag[0] + 1];
				if (right.magnet[right.left] != now.magnet[now.right]) {
					checkMagnet[nowMag[0] + 1] = nowMag[1] * -1;
					queue.add(new int[] { nowMag[0] + 1, nowMag[1] * -1 });
				}
			}
		}

		// 자석 회전
		for (int i = 0; i < checkMagnet.length; i++) {
			if (checkMagnet[i] == 0)
				continue;
			magnets[i].rotate(checkMagnet[i]);
		}
	}
	
	private static void print(int tc) {
		int sum = 0;
		for (int i = 0; i < magnets.length; i++) {
			sum += magnets[i].magnet[magnets[i].top] * Math.pow(2, i);
		}
		sb.append("#").append(tc).append(" ").append(sum).append("\n");
	}

}
/*
1
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1
 */
