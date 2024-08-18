import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[][] ingredients;

	static int sourSum = 1;
	static int bitternessSum;

	static int cnt; // 현재 몇 개의 재료를 넣었는 지 파악하는 변수
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		input();
		solution(0);
		System.out.println(answer);
	}

	private static void solution(int elementNum) {
		// 맛 차이의 최솟값 업데이트
		if (cnt >= 1) {
			answer = Math.min(answer, Math.abs(sourSum - bitternessSum));
		}

		// 기저조건(요소 인덱스가 끝까지 간 경우)
		if (elementNum == ingredients.length) {
			return;
		}

		// 현재 요소를 포함하고 재귀
		sourSum *= ingredients[elementNum][0];
		bitternessSum += ingredients[elementNum][1];
		cnt++;
		solution(elementNum + 1);

		// 현재 요소를 포함하지 않고 재귀
		sourSum /= ingredients[elementNum][0];
		bitternessSum -= ingredients[elementNum][1];
		cnt--;
		solution(elementNum + 1);
	}

	private static void input() throws IOException {
		int ingredientsNum = Integer.parseInt(br.readLine());

		ingredients = new int[ingredientsNum][2];

		for (int i = 0; i < ingredientsNum; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}

	}
}
