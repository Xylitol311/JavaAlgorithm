import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, nodes[];
    static boolean[] visited;    // 전체 방문 여부
    static boolean[] finished;   // 현재 DFS에서 탐색 완료된 노드
    static int count;           // 팀에 속하지 않는 학생 수

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        
        nodes = new int[N + 1];
        visited = new boolean[N + 1];
        finished = new boolean[N + 1];
        count = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            input();
            solution();
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

    private static void solution() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    private static void dfs(int now) {
        visited[now] = true;
        
        int next = nodes[now];
        
        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            // 방문은 했으나 아직 처리가 안 된 노드 = 현재 DFS 경로상의 노드
            // = 싸이클 발견
            // 싸이클에 포함된 학생 수를 count에서 제외
            for (int i = next; i != now; i = nodes[i]) {
                count--;
            }
            count--;  // 현재 노드도 포함
        }
        
        // 현재 노드 처리 완료
        finished[now] = true;
        count++;  // 일단 모든 학생을 카운트
    }
}