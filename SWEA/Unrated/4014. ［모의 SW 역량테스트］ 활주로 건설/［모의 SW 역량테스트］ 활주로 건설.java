import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
한 행 혹은 한 열씩 활주로 건설이 가능한지 확인한다.

활주로 건설이 불가한 경우
- 높낮이 차이가 2 이상인 경우
- 활주로를 만들만한 충분한 공간이 없는 경우 (x가 2일 때, 층이 바뀌고 낮은 곳의 길이가 2보다 작은 경우)
- 내리막 활주로를 만든 직후 높은 층이 나온 경우(오르막 활주로를 만들 공간이 없음)
- 내리막 활주로가 완성되지 않았는데 오르막이 나오거나 배열이 끝난 경우
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int fieldLength, runway, field[][];

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());

		fieldLength = Integer.parseInt(st.nextToken());
		runway = Integer.parseInt(st.nextToken());

		field = new int[fieldLength][fieldLength];

		for (int row = 0; row < fieldLength; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < fieldLength; col++) {
				field[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			sb.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.println(sb);
	}

	private static int solution() {
		int cnt = 0;

		// 행 탐색
		for (int row = 0; row < fieldLength; row++) {
			boolean result = isPossibleByRow(row);

			// 활주로 생성이 가능하면 cnt 추가
			if (result)
				cnt++;
		}

		// 열 탐색
		for (int col = 0; col < fieldLength; col++) {
			boolean result = isPossibleByCol(col);

			// 활주로 생성이 가능하면 cnt 추가
			if (result)
				cnt++;
		}

		return cnt;
	}

	private static boolean isPossibleByRow(int row) {
		int stack = 0;
		int preFloor = 0;
		boolean downhill = false;
		boolean possibleRunway;

		possibleRunway = true;

		preFloor = 0;
		for (int col = 0; col < fieldLength; col++) {
			int nowFloor = field[row][col];

			// 각 행의 첫번째 값인 경우
			if (preFloor == 0) {
				stack = 1;
				preFloor = nowFloor;
				continue;
			}

			// <높이가 같은 경우>
			if (preFloor == nowFloor) {
				stack++;
				// 내리막 경사로가 완성된 경우
				if (downhill && stack == runway) {
					stack = 0;
					downhill = false;
				}
				// 마지막 칸인데 내리막 경사로가 다 완성되지 않은 경우 활주로 불가
				if (downhill && col == fieldLength - 1) {
					possibleRunway = false;
					break;
				}
				continue;
			}

			// <높이가 달라진 경우>
			// 층 차이가 2층 이상인 경우 활주로 불가.
			// 아직 내리막 경사로가 다 완성되지 않았는데 층이 바뀐 경우
			if (Math.abs(preFloor - nowFloor) > 1 || downhill) {
				possibleRunway = false;
				break;
			}

			// 오르막인 경우
			if (nowFloor > preFloor) {
				// 활주로를 만들만큼 stack이 쌓이지 않았는데 층이 바뀐 경우 활주로 불가
				if (stack < runway) {
					possibleRunway = false;
					break;
				}
				stack = 1;
			}

			// 내리막인 경우
			if (nowFloor < preFloor) {
				stack = 1;
				downhill = true;
			}

			// 이전 층 갱신
			preFloor = nowFloor;
		}
		
		if (downhill) possibleRunway = false;

		return possibleRunway;
	}

	private static boolean isPossibleByCol(int col) {
		int stack = 0;
		int preFloor = 0;
		boolean downhill = false;
		boolean possibleRunway;

		possibleRunway = true;

		preFloor = 0;
		for (int row = 0; row < fieldLength; row++) {
			int nowFloor = field[row][col];

			// 각 행의 첫번째 값인 경우
			if (preFloor == 0) {
				stack = 1;
				preFloor = nowFloor;
				continue;
			}

			// <높이가 같은 경우>
			if (preFloor == nowFloor) {
				stack++;
				// 내리막 경사로가 완성된 경우
				if (downhill && stack == runway) {
					stack = 0;
					downhill = false;
				}
				// 마지막 칸인데 내리막 경사로가 다 완성되지 않은 경우 활주로 불가
				if (downhill && row == fieldLength - 1) {
					possibleRunway = false;
					break;
				}
				continue;
			}

			// <높이가 달라진 경우>
			// 층 차이가 2층 이상인 경우 활주로 불가.
			// 아직 내리막 경사로가 다 완성되지 않았는데 층이 바뀐 경우
			if (Math.abs(preFloor - nowFloor) > 1 || downhill) {
				possibleRunway = false;
				break;
			}

			// 오르막인 경우
			if (nowFloor > preFloor) {
				// 활주로를 만들만큼 stack이 쌓이지 않았는데 층이 바뀐 경우 활주로 불가
				if (stack < runway) {
					possibleRunway = false;
					break;
				}
				stack = 1;
			}

			// 내리막인 경우
			if (nowFloor < preFloor) {
				stack = 1;
				downhill = true;
			}

			// 이전 층 갱신
			preFloor = nowFloor;
		}

		if (downhill) possibleRunway = false;
		
		return possibleRunway;
	}
}