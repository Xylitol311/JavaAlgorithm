import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static List<Integer>[] graph;
    static int[] hackCount;
    static boolean[] visited;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hackCount = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }
    }

    private static void solution() {
        // 각 컴퓨터별로 해킹 가능한 컴퓨터 수 계산
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            hackCount[i] = bfs(i);
        }

        // 최대값 찾기
        int max = 0;
        for (int count : hackCount) {
            max = Math.max(max, count);
        }

        // 최대값과 같은 컴퓨터 번호 출력
        for (int i = 1; i <= N; i++) {
            if (hackCount[i] == max) {
                sb.append(i).append(" ");
            }
        }
    }

    private static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;
                }
            }
        }
        
        return count;
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb);
    }
}