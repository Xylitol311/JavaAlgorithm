import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
1. CCTV의 종류와 개수를 파악해서 배열로 정리
2. 재귀를 통해서 각 CCTV의 방향을 설정
3. 모든 CCTV의 방향이 설정되면 원본 배열을 카피한 임시 배열에 각각 CCTV 방향에 맞게 시야 범위를 확보 (0을 8으로 교체)
4. 시야가 하나씩 확보될 때마다 cnt 추가
5. 시야 정리가 완료되면 cnt와 전체 과정의 최댓값을 비교해서 최댓값 갱신
6. 모든 cctv 시야 경우를 탐색하면 갱신된 최댓값을 출력
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static final int VISION = 8;
	static final int WALL = 6;

	static boolean[] visited;
	static int[][] office;
	static int[][] copyMap;

	static List<Cctv> cctvType;
	static int[] cctvDirection;

	// 델타 : 상, 우, 하, 좌
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int height;
	static int width;

	static int maxValue; // 처음 입력 시 cctv와 벽을 제외한 최대 사각지대 개수
	static int answer; // 사각지대의 최솟값
	static int tempCnt; // 각 경우의 수마다 커버되는 시야 범위

	static class Cctv {
		int type;
		int row;
		int col;

		public Cctv(int type, int row, int col) {
			super();
			this.type = type;
			this.row = row;
			this.col = col;
		}

	}

	public static void main(String[] args) throws IOException {
		input();
		setDirection(0);
		System.out.println(answer);

	}

	// 각 cctv에 가능한 방향 조합 입력
	private static void setDirection(int cctvIdx) {
		// 모든 방향 선택이 완료된 경우
		if (cctvIdx == cctvType.size()) {
			// cctv의 커버 범위 계산
			int result = searchBlindSpot();
			// maxValue - result = 기존 사각지대 - 현재 시야 커버 범위
			answer = Math.min(answer, maxValue - result);
			return;
		}

		// 현재 cctv 종류
		int nowtype = cctvType.get(cctvIdx).type;
		// 현재 cctv가 볼 수 있는 시야 방향 배열
		int[] nowViewDirections = directionListByType(nowtype);

		// 시야 방향 배열 중 하나를 고르고 재귀로 다음 cctv의 방향 설정
		for (int nowDirection : nowViewDirections) {
			cctvDirection[cctvIdx] = nowDirection;
			setDirection(cctvIdx + 1);
		}
	}

	// cctv 종류에 따라 방향 목록을 반환하는 함수
	private static int[] directionListByType(int type) {
		if (type == 5)
			return new int[] { 0 };
		if (type == 2)
			return new int[] { 0, 1 };
		return new int[] { 0, 1, 2, 3 };
	}

	// 만들어진 시야 조합으로 현재 커버 가능한 최대 범위를 구하는 함수
	private static int searchBlindSpot() {
		tempCnt = 0;

		// 임시 배열 카피
		copyMap = new int[height][width];
		for (int row = 0; row < height; row++) {
			copyMap[row] = office[row].clone();
		}

		// 각 cctv의 종류와 설정된 방향대로 배열에 시야정보를 표시
		for (int cctvIdx = 0; cctvIdx < cctvType.size(); cctvIdx++) {
			addVision(cctvIdx);
		}

		return tempCnt;
	}

	// cctv 종류와 그 cctv의 방향을 기준으로 시야 확보 처리
	private static void addVision(int cctvIdx) {
		Cctv cctv = cctvType.get(cctvIdx);

		// 모든 경우 실행
		coverVision(cctvDirection[cctvIdx], cctv.row, cctv.col);

		// 타입이 3,4,5일 때 실행
		if (cctv.type != 1 && cctv.type != 2)
			coverVision((cctvDirection[cctvIdx] + 1) % 4, cctv.row, cctv.col);

		// 타입이 2, 4, 5일 때 실행
		if (cctv.type != 1 && cctv.type != 3)
			coverVision((cctvDirection[cctvIdx] + 2) % 4, cctv.row, cctv.col);

		// 타입 5만 실행
		if (cctv.type == 5)
			coverVision((cctvDirection[cctvIdx] + 3) % 4, cctv.row, cctv.col);
	}

	// 입력된 좌표와 방향을 기준으로 시야 확보 처리를 하는 함수
	private static void coverVision(int direction, int startY, int startX) {
		// 시작 좌표에서부터 설정 방향까지 목표를 수정하는 함수
		int nx = startX;
		int ny = startY;

		while (true) {
			nx = nx + dx[direction];
			ny = ny + dy[direction];
			// 범위 체크
			if (nx < 0 || nx >= width || ny < 0 || ny >= height)
				return;

			// 벽이면 멈추고 return
			if (copyMap[ny][nx] == WALL)
				return;

			// 방문한 곳이면 패스
			if (copyMap[ny][nx] == VISION)
				continue;

			// cctv면 패스
			if (copyMap[ny][nx] >= 1 && copyMap[ny][nx] <= 5)
				continue;

			// 전부 아니면 시야 확보
			copyMap[ny][nx] = VISION;
			tempCnt++;
		}
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		office = new int[height][width];

		// 시야범위 최댓값
		maxValue = height * width;
		answer = Integer.MAX_VALUE;

		// cctv 개수
		int cctvCnt = 0;

		cctvType = new ArrayList<>();
		// 지도 저장
		for (int row = 0; row < height; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < width; col++) {
				office[row][col] = Integer.parseInt(st.nextToken());
				if (office[row][col] != 0)
					maxValue--;

				if (office[row][col] != 0 && office[row][col] != 6) {
					// cctv인 경우 종류, 행, 열 저장
					cctvType.add(new Cctv(office[row][col], row, col));
				}
			}
		}

		// cctv 방향을 저장할 배열 생성
		cctvDirection = new int[cctvType.size()];
	}
}
