package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11581 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[N + 1][N + 1];

        // 그래프 초기화
        for (int i = 1; i < N; i++) {
            int linkNum = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < linkNum; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][num] = true;
            }
        }

        //플로이드 워셜
        for (int transit = 1; transit < N; transit++) {
            for (int start = 1; start < N; start++) {
                if (!graph[start][transit]) {
                    continue;
                }
                for (int end = 1; end < N; end++) {
                    if(graph[transit][end]) {
                        graph[start][end] = true;
                    }
                }
            }
        }

        // 1번 노드와 연결된 사이클 유무 검증
        for (int i = 1; i < N; i++) {
            if (graph[1][i] && graph[i][i]) {
                System.out.println("CYCLE");
                return;
            }
        }
        System.out.println("NO CYCLE");
    }
}