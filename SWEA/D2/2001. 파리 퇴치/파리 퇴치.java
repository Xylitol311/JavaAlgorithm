

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] accumulatedSum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static StringTokenizer st;

	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		accumulatedSum = new int[N + M][N + M];
		return;
	}

	// 누적합 입력 및 M*M 범위 죽인 파리 수의 최댓값을 구하는 함수
	public static int calculateFlies() throws IOException {
		int max = -1; // 최댓값
		int nowValue = -1; // for문 내부에서 현재 위치의 M*M의 합계를 저장할 변수

		// 누적합 입력과 동시에 최댓값 갱신
		for (int row = M; row < N + M; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = M; col < N + M; col++) {
				
				// 현재 위치 누적합 입력
				accumulatedSum[row][col] = 
						Integer.parseInt(st.nextToken()) 
						+ accumulatedSum[row - 1][col]
						+ accumulatedSum[row][col - 1] 
						- accumulatedSum[row - 1][col - 1];
				
				// 현 위치 기준 왼쪽 상단 M*M의 합계 계산 후 max 갱신
				nowValue = accumulatedSum[row][col]
						- accumulatedSum[row-M][col]
						- accumulatedSum[row][col-M]
						+ accumulatedSum[row-M][col-M];
				max = Math.max(max, nowValue);
			}
			
		}
		
		return max;
	} // calculateFlies

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		int max;

		for (int tc = 1; tc <= T; tc++) {
			input();
			max = calculateFlies();
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		} // test case
		
		System.out.println(sb);
	} // main

}
