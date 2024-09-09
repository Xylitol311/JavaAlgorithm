package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DNA_password {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int strLength; // 주어진 문자열의 길이
	static int resultLength; // 만들 부분 문자열의 길이

	static String str; // 주어진 문자열

	// a, c, g, t 순서
	static int[] dna; // 문제의 조건 입력 배열
	static int[] test; // 실시간으로 몇 개의 요소들을 가지고 있는지 확인할 배열

	static int answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
		System.out.println(answer);
	}

	private static void solution() {
		int startIdx = 0; // 부분 문자열의 시작 위치를 가리키는 인덱스
		int idx; // text 배열의 인덱스를 가리키는 변수

		for (int nowIdx = 0; nowIdx < strLength; nowIdx++) {
			// 현재 char를 보고 해당 인덱스 test 배열에 입력
			idx = returnIdx(str.charAt(nowIdx));
			test[idx]++;

			// 두 번째 이상 부분 문자열인 경우
			if (nowIdx > resultLength - 1) {
				// 이전 부분 문자열의 맨 앞 글자 제거
				idx = returnIdx(str.charAt(startIdx++));
				test[idx] = Math.max(0, test[idx] - 1);
			}

			// 부분 문자열이 완성된 경우 조건에 맞는지 체크
			if (nowIdx >= resultLength - 1) {
				boolean check = true;
				for (int testIdx = 0; testIdx < dna.length; testIdx++) {
					if (test[testIdx] < dna[testIdx]) {
						check = false;
						break;
					}
				}
				if (check) answer++;
			}
		}
	}

	private static int returnIdx(char c){
		if (c == 'A') return 0;
		if (c == 'C') return 1;
		if (c == 'G') return 2;
		return 3;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		strLength = Integer.parseInt(st.nextToken());// 전체 문자열의 길이
		resultLength = Integer.parseInt(st.nextToken()); // 만들 부분 문자열의 길이
		dna = new int[4];
		test = new int[4];

		str = br.readLine(); // 주어진 문자열

		// dna 조건 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < 4; idx++) {
			dna[idx] = Integer.parseInt(st.nextToken());
		}
	}
}
