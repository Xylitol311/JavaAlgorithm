import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		Map<Integer, Integer> map = new HashMap<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cow = Integer.parseInt(st.nextToken());
			int position = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(cow)) {
				if (map.get(cow) != position) {
					cnt++;
				}
			}
			map.put(cow, position);
		}
		
		System.out.println(cnt);
	}
}
