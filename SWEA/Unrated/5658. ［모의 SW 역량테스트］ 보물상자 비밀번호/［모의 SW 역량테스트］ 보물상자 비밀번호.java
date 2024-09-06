import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int length, rank;
	static String str;

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		rank = Integer.parseInt(st.nextToken());

		str = br.readLine();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			sb.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.println(sb);
	}

	private static long solution() {
		int numLength = length / 4;
		Set<Long> set = new HashSet<>();
		for (int idx = 0; idx < length; idx++) {
			int endIdx = idx + numLength - 1;
			String hex;
			if (endIdx >= length)
				hex = str.substring(idx) + str.substring(0, endIdx % length+1);
			else
				hex = str.substring(idx, idx + numLength);
			long num = Integer.parseInt(hex, 16);
			set.add(num);
		}
		List<Long> list = new ArrayList<>(set);
		Collections.sort(list);
		Collections.reverse(list);
		return list.get(rank - 1);
	}
}