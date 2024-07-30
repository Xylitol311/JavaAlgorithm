import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		for (int test = 1; test <= T; test++) {
			int testNum = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] cntArr = new int[101]; // 0~100

			
			for (int i = 0; i < 1000; i++) {
				cntArr[Integer.parseInt(st.nextToken())]++;
			}
			
			int max = 0;
			int maxIdx = 0;
			for (int i = 0; i < 101; i++) {
				if (cntArr[i] >= max) {
					max = cntArr[i];
					maxIdx = i;
				}
			}
			
			sb.append(String.format("#%d %d\n", test, maxIdx));
		}
		
		System.out.println(sb.toString());
	}
}