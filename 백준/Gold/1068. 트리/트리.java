// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
루트 노드를 제거하는 경우는 0
dfs를 이용해서 삭제한 노드를 제외하고 탐색하여 리프 노드의 개수를 카운팅한다.

 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer>[] graph;
    static boolean visited[];
    static int delete;
    static int parent[];
    static int answer, n, root;

    private static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 노드의 개수
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        root = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) {
                // i 노드가 바로 루트 노드
                root = i; // 루트 노드 정보를 저장함
            } else {
                // p 가 i 노드의 부모 노드임
                graph[i].add(p);
                graph[p].add(i); // 서로 연결
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        input();

        // 노드 삭제
        delete = Integer.parseInt(br.readLine());
        if (delete == root) {
            System.out.println(0);
            return;
        }
        else dfs(root);

        System.out.println(answer);
    }

    static void dfs(int v) {
        visited[v] = true;
        int nodes = 0;
        for (int cur : graph[v]) {
            // 연결 노드 탐색
            if (cur != delete && !visited[cur]) {
                nodes++;
                dfs(cur);
            }
        }
        
        // 탐색 후 자식 노드가 0개면 리프 노드
        if (nodes == 0) {
            answer++; // 자식 노드가 하나도 없으면 리프 노드임
        }
    }
}