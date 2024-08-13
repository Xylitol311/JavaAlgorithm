import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

		long[] nums = new long[N + 1]; // 입력과 동시에 이전 값과 합계를 구하기 위해 하나 크게 생성
		st = new StringTokenizer(br.readLine());

		// 입력과 동시에 누적값을 저장하도록 구현
		for (int i = 1; i <= N; i++) {
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 시작 인덱스
			int end = Integer.parseInt(st.nextToken()); // 마지막 인덱스
			// 시작 인덱스 전까지의 누적값을 마지막 인덱스에서 빼면 start부터 end지점까지의 합을 구할 수 있다.
			sb.append(nums[end] - nums[start - 1]).append("\n");
		}
		
		System.out.println(sb);
	}
}