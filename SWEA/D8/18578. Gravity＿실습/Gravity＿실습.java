import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] array = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			

			int max = 0;
			int cnt;
			for (int i = 0; i < N; i++) {
				cnt = 0;
				for (int j = i+1; j < N; j++) {
					if(array[i] > array[j]) {
						cnt++;
					}
				}
				max = Math.max(max, cnt);
			}
			sb.append(String.format("#%d %d\n", test_case, max));
		}
		System.out.println(sb.toString());
	}
}