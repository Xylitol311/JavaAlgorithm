import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int answer, N, M, person1, person2;
    static List<Integer>[] graph;

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        person1 = Integer.parseInt(st.nextToken());
        person2 = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            graph[parent].add(child);
            graph[child].add(parent);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];//특정 노드를 방문했는지 여부만 확인하면 되므로 1차원 배열 사용
        queue.add(new int[]{person1, 0});
        visited[person1] = true;


        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowPerson = now[0];
            int nowDistance = now[1];

            // 같은 사람인 경우
            if (nowPerson == person2) {
                answer = nowDistance;
                return;
            }

            // 아닐 경우 다음 탐색
            for (int next : graph[nowPerson]) {
                // 방문 여부 확인
                if (visited[next]) continue;
                // 방문 안한 경우 큐에 추가
                queue.add(new int[]{next, nowDistance + 1});
                visited[next] = true;
            }

        }

        answer = -1;
    }
}