import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static ArrayList<Integer>[] graph;
    static int[] visitOrder;
    static int order = 1;
    
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        visitOrder = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // 내림차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], Collections.reverseOrder());
        }
    }
    
    // DFS를 수행하여 방문 순서를 기록하는 함수
    public static void dfs(int node) {
        visitOrder[node] = order++;
        
        for (int next : graph[node]) {
            if (visitOrder[next] == 0) {
                dfs(next);
            }
        }
    }
    
    // 결과를 출력하는 함수
    public static void solution() {
        dfs(R);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitOrder[i]).append('\n');
        }
        System.out.print(sb);
    }
    
    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}