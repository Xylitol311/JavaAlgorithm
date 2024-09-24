import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<Integer>[] friends;

    static class Node {
        int num;
        Node next;

        public Node(int num, Node next) {
            this.num = num;
            this.next = next;
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        friends = new ArrayList[N + 1];

        for (int i = 1; i < friends.length; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            friends[start].add(end);
            friends[end].add(start);
        }
    }

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        input();
        visited = new boolean[N + 1];
        visited[1] = true; // 상근이 방문 체크
        dfs(1, 0); // 깊이 0에서 시작
        int answer = 0;

        for (int i = 2; i < visited.length; i++) { // 상근이 제외한 방문된 친구들만 카운트
            if (visited[i]) answer++;
        }

        System.out.println(answer);
    }

    private static void dfs(int start, int depth) {
        if (depth == 2) return; // 친구의 친구까지만 탐색하고 리턴

        for (int i = 0; i < friends[start].size(); i++) {
            int next = friends[start].get(i);
            visited[next] = true;
            dfs(next, depth + 1);
        }
    }
}