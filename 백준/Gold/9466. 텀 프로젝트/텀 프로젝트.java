// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
각 학생들이 선택한 결과가 싸이클이 완성되면 팀을 이룰 수 있다.
DFS를 이용해 노드들을 탐색하여 싸이클이 존재하지 않는 노드의 수를 구한다.
싸이클이 존재하는 경우: 자기 자신을 선택하거나 시작 노드로 돌아온 경우
싸이클이 존재하지 않는 경우: 그 외 모두

[풀이방법]
1. DFS로 각 노드를 하나씩 탐색한다. DFS 함수 호출 시 현재 노드를 방문 처리
2. DFS(시작 노드, 깊이)
    a. 기저조건
        ㄱ. 탐색 깊이가 N과 일치된 경우 (싸이클 x)
            - 싸이클이 존재하지 않으므로 정답에 현재 탐색 깊이만큼 추가
    b. 현재 노드가 선택한 다음 노드 탐색
        ㄱ. 현재 노드와 다음 노드가 일치하는 경우: 해당 노드는 싸이클 존재. 이전까지 선택된 노드들은 싸이클 X
            - 현재 노드를 제외한 현재까지 선택된 노드의 수를 정답에 추가.(= 깊이)
        ㄴ. 다음 노드가 이미 방문 체크 된 경우:
            - 다음 노드가 시작노드와 같은 경우: 싸이클이 존재함으로 return
            - 다음 노드가 방문 처리됐는데 현재 노드가 선택되지 않았다는 건 현재까지 선택된 노드들은 싸이클이 될 수 없다는 얘기
            - 현재 노드를 포함해 현재까지 선택된 노드의 수를 정답에 추가. (= 깊이 + 1)
        ㄴ. 방문 체크 후 DFS 탐색 (시작노드, 깊이 + 1)

 */
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
        count = N;

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
    }
}