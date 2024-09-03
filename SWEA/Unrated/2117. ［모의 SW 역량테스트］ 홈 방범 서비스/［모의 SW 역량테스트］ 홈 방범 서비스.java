import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <풀이 방법>
 * 1. 테스트케이스 및 배열 정보를 입력 받는다.
 * 		1-1. 이때 집의 좌표를 따로 리스트에 저장.
 * 2. 서비스의 영역을 나타내는 k를 1부터 모든 집을 포함했을 때 손해를 보지 않는 크기까지 키우면서 배열을 탐색한다.
 * 		2-1. 손해를 보지 않는 최대 크기 : 모든 집의 개수 * M >= K^2 + (K-1)^2 를 만족하는 K
 * 3. 각 K 사이즈별로 모든 배열을 탐색하며 운영비용 이상의 수익을 얻을 수 있는 경우의 집의 개수를 구하고 그 중 최댓값 갱신한다.
 * 		3-1. 배열 탐색 시 각 좌표에서부터 모든 집까지의 거리를 비교하고 k 거리 이내에 있는 집들의 개수를 구한다.
 * 		3-2. 집들의 개수와 집에서 지불 가능한 금액의 합을 구해서 운영 비용보다 작으면 다음 좌표를 탐색한다.
 * 		3-3. 적자가 나지 않은 경우 해당 집의 개수와 전체 최댓값을 비교하여 더 큰 값을 갱신한다.
 * 4. 모든 K를 탐색한 후 집의 개수의 최댓값을 출력한다.
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int fieldSize, homeCost;
	static int[][] map;
	static List<House> houses;

	static class House {
		int row, col;

		public House(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		fieldSize = Integer.parseInt(st.nextToken());
		homeCost = Integer.parseInt(st.nextToken());

		map = new int[fieldSize][fieldSize];
		houses = new ArrayList<>();

		for (int row = 0; row < fieldSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < fieldSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1)
					houses.add(new House(row, col));
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
		while (Math.pow(maxServiceArea, 2) + Math.pow(maxServiceArea - 1, 2) <= houses.size() * homeCost) {
			maxServiceArea++;
		}

		// K를 1부터 maxServiceArea보다 작은 최댓값까지 반복
		while (serviceArea < maxServiceArea) {
			int serviceCost = (int) (Math.pow(serviceArea, 2) + Math.pow(serviceArea - 1, 2));
			for (int row = 0; row < fieldSize; row++) {
				for (int col = 0; col < fieldSize; col++) {
					int homeCnt = 0;
					// 현 위치에서 서비스 가능한 집 개수 파악
					for (House house : houses) {
						int distance = calDistance(row, col, house);
						if (distance <= serviceArea - 1)
							homeCnt++;
					}

					// 적자인지 확인
					if (homeCnt * homeCost < serviceCost)
						continue;

					// 집의 개수 최댓값 갱신
					maxHomeCnt = Math.max(maxHomeCnt, homeCnt);
				}
			}
			serviceArea++;
		}

	}

	private static int calDistance(int row, int col, House house) {
		return Math.abs(row - house.row) + Math.abs(col - house.col);
	}
}
