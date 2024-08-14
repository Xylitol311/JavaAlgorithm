import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] accumulatedSum;
	static StringBuilder sb = new StringBuilder();

	// 누적합 입력
	public static void inputArray(int size) throws IOException {
		accumulatedSum = new int[size + 1][size + 1];

		for (int row = 1; row <= size; row++) {
			st = new StringTokenizer(br.readLine());

			for (int col = 1; col <= size; col++) {
				accumulatedSum[row][col] = Integer.parseInt(st.nextToken()) + accumulatedSum[row - 1][col]
						+ accumulatedSum[row][col - 1] - accumulatedSum[row - 1][col - 1];
			}
		}
	}

	// 구간합 계산
	public static void calculate(int biginX, int biginY, int endX, int endY) {
		int result = 
				accumulatedSum[endX][endY] 
				- accumulatedSum[biginX - 1][endY] 
				- accumulatedSum[endX][biginY - 1]
				+ accumulatedSum[biginX - 1][biginY - 1];
		sb.append(result).append("\n");
	}

	public static void main(String[] args) throws IOException {
		// 데이터 입력
		st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int repeat = Integer.parseInt(st.nextToken());

		// 배열 입력
		inputArray(size);

		for (int tc = 0; tc < repeat; tc++) {
			st = new StringTokenizer(br.readLine());
			int biginX = Integer.parseInt(st.nextToken());
			int biginY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			calculate(biginX, biginY, endX, endY);
		}
		System.out.println(sb);
	}
}
