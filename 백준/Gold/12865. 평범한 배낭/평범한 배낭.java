import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp knapsack을 이용해 풀이
1. 아이템의 개수와 견딜 수 있는 최대 무게를 입력받고 각 아이템의 무게와 가치를 배열에 저장한다.
2. 견딜 수 있는 최대 무게부터 탐색하여 담을 수 있는 무게 한계치가 넘지 않을 때까지 반복
3. dp에 저장된 기본값과 현재 무게에서 가능한 다른 조합의 가치 + 현재 아이템 가치 중 큰 값으로 갱신
 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int numOfItems, maxWeight, weights[], values[];
	static int[] dp;
	
	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");

		numOfItems = Integer.parseInt(st.nextToken());
		maxWeight = Integer.parseInt(st.nextToken());

		weights = new int[numOfItems + 1]; // 무게
		values = new int[numOfItems + 1]; // 가치
        dp = new int[maxWeight + 1];

        for (int i = 1; i <= numOfItems; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
	}
	
    public static void main(String[] args) throws IOException {
    	input();
        solution();
        System.out.println(dp[maxWeight]);
    }
    
    private static void solution() {
    	// 아이템을 반복
    	for (int i = 1; i <= numOfItems; i++) {
    		// 최대 무게부터 담을 수 있는 무게의 한계치까지 반복
            for (int j = maxWeight; j - weights[i] >= 0; j--) {
            	// dp에 저장된 기본값과 현재 무게에서 가능한 다른 조합의 가치 + 현재 아이템 가치 중 큰 값으로 갱신
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
    }
}