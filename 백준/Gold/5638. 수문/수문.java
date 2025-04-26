import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int n;
    static long[] flow;
    static long[] cost;
    
    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(sb);
    }
    
    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        flow = new long[n];
        cost = new long[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            flow[i] = Long.parseLong(st.nextToken());
            cost[i] = Long.parseLong(st.nextToken());
        }
    }
    
    private static void solve() throws IOException {
        int m = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= m; tc++) {
            st = new StringTokenizer(br.readLine());
            long V = Long.parseLong(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            
            long answer = Long.MAX_VALUE;
            
            // 2^n 조합을 모두 확인
            for (int mask = 1; mask < (1 << n); mask++) {
                long totalFlow = 0;
                long totalCost = 0;
                
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) { // i번째 수문을 선택한 경우
                        totalFlow += flow[i];
                        totalCost += cost[i];
                    }
                }
                
                // 총 유량 * 시간 >= 총 물량
                if (totalFlow * T >= V) {
                    answer = Math.min(answer, totalCost);
                }
            }
            
            if (answer == Long.MAX_VALUE) {
                sb.append("Case ").append(tc).append(": IMPOSSIBLE\n");
            } else {
                sb.append("Case ").append(tc).append(": ").append(answer).append("\n");
            }
        }
    }
}