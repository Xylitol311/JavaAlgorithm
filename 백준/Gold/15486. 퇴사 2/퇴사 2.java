import java.io.*;
import java.util.StringTokenizer;

/*
dp를 이용해 바텀업 방식으로 1일부터 최댓값을 탐색하여 갱신한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, arr[][], dp[];

    private static void input() throws Exception {
        n = Integer.parseInt(br.readLine());

        arr =new int[n+2][2];
        dp = new int[n+2];
        for(int i=1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i][0] = t; // 기간
            arr[i][1] = p; // 금액
        }
    }
    public static void main(String[] args) throws Exception{
        input();
        // 풀이
        int max = -1;
        for(int i=1; i<=n+1; i++) {
            if(max < dp[i]) {
                max = dp[i];
            }

            int nxt = i +arr[i][0];
            if(nxt < n+2) {
                dp[nxt] = Math.max(dp[nxt], max+arr[i][1]);
            }
        }
        System.out.println(dp[n+1]);
    }
}