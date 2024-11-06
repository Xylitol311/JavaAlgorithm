// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
모든 사회망에 아이디어를 전파하기 위해서는 얼리어답터가 아닌 사람은 인접한 모든 사람이 얼리어답터여야 한다.
만약 특정 노드가 얼리어답터라면 그와 인접한 노드는 얼리어답터 여부가 중요하지 않다. 하지만 특정 노드가 얼리어답터가 아니라면 인접한 노드는 모두 얼리어답터여야 한다.
dp,dfs 탐색을 이용해서 각 노드가 얼리어답터가 아닌 경우(0)와 얼리어답터인 경우(1)로 나누고 트리 형태의 그래프라고 했으므로 자식노드부터 최소한의 얼리어답터 수를 구한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int result, n;
    static boolean[] visited;
    static List<Integer>[] graph;
    static int[][] dp; //[각 노드][얼리어답터 여부]

    private static void input() throws IOException {
        // 노드 개수
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 입력
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.valueOf(st.nextToken());
            int end = Integer.valueOf(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int number) {
        visited[number] = true;
        dp[number][0] = 0;	//얼리어답터가 아닌 경우
        dp[number][1] = 1;	// 얼리어답터인 경우(본인이 얼리어답터이므로 1로 시작)

        // 인접 노드 탐색
        for(int child : graph[number]) {
            if(!visited[child]) { // 중복 확인
                dfs(child);	//자식 노드의 dp값을 먼저 구함
                dp[number][0] += dp[child][1];	// 자식 노드가 얼리어답터여야 함.
                dp[number][1] += Math.min(dp[child][0], dp[child][1]); // 본인이 얼리어답터인 경우, 자식 노드는 얼리어답터일수도 아닐수도 있음
            }
        }
    }
}