import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] visitOrder;
    static boolean[] visited;
    static int order;
    
    // 입력을 받고 그래프를 초기화하는 함수
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        
        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // 오름차순 정렬 (인접 정점을 오름차순으로 방문하기 위함)
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }
        
        visitOrder = new int[N + 1];
        visited = new boolean[N + 1];
        order = 1;
        
        // DFS 실행
        dfs(R);
        
        // 결과 출력
        for (int i = 1; i <= N; i++) {
            System.out.println(visitOrder[i]);
        }
    }
    
    // DFS 탐색을 수행하는 함수
    public static void dfs(int node) {
        visited[node] = true;
        visitOrder[node] = order++;
        
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
    
    // 메인 함수
    public static void main(String[] args) throws IOException {
        input();
    }
}