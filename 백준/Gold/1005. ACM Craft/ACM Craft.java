import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 각 테스트 케이스마다 사용하는 변수들
    static int n;                   // 건물의 개수
    static int k;                   // 건설 규칙(간선)의 개수
    static int target;              // 목표 건물 번호
    static int[] d;                 // 각 건물의 기본 건설 시간
    static List<List<Integer>> adj; // 건물 간 의존관계를 저장하는 인접 리스트
    static int[] indegree;          // 각 건물의 진입차수
    static int[] result;            // 각 건물까지의 누적 건설 시간

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        d = new int[n + 1];
        indegree = new int[n + 1];
        result = new int[n + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            d[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(to);
            indegree[to]++;
        }
        
        target = Integer.parseInt(br.readLine());
    }

    private static void solution() {
        Queue<Integer> queue = new LinkedList<>();
        // 초기 건설 시간은 각 건물의 기본 시간이며, indegree가 0인 건물부터 시작
        for (int i = 1; i <= n; i++) {
            result[i] = d[i];
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // 현재 건물이 건설 완료된 후, 인접 건물의 누적 건설 시간을 갱신
            for (int next : adj.get(curr)) {
                result[next] = Math.max(result[next], result[curr] + d[next]);
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        sb.append(result[target]).append("\n");
    }

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            input();
            solution();
        }
        System.out.println(sb);
    }
}