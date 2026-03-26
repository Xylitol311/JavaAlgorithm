import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K, X;

    static List<List<Integer>> graph;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        bfs(X);
        System.out.println(sb.toString());
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[1] == K) {
                result.add(now[0]);
                continue;
            }

            if (now[1] > K) continue;

            List<Integer> nowNode = graph.get(now[0]);

            for (int next : nowNode) {
                if (visited[next]) continue;
                queue.add(new int[]{next, now[1] + 1});
                visited[next] = true;
            }
        }

        Collections.sort(result);
        if (result.size() == 0) {
            sb.append(-1);
        } else {
            for (int num : result) {
                sb.append(num + "\n");
            }
            
        }

    }
}
