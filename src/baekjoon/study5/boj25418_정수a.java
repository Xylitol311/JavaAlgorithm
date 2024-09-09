package baekjoon.study5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
A에 대해서 *2, +1 두 가지 간선이 있다고 가정하고 각각에 대해 BFS로 탐색
K가 될 때까지 반복하고 중간에 큐에 등록하는 수들은 방문처리해서 중복으로 처리하지 않게 막는다.
 */
public class boj25418_정수a {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        solution();
    }

    private static void solution() throws Exception {
        st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[K+1];

        // int[0]는 현재 숫자, int[1]는 연산 횟수
        queue.add(new int[]{A,0});

        visited[A] = true;
        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // K 도달 시 연산 횟수 출력
            if (cur[0] == K) {
                System.out.println(cur[1]);
                return;
            }
            // 곱하기 2 연산
            if (cur[0]*2<=K) {
                visited[cur[0]*2] = true;
                queue.add(new int[]{cur[0]*2, cur[1]+1});
            }
            // 더하기 1 연산
            if (!visited[cur[0]+1])
                queue.add(new int[]{cur[0]+1, cur[1]+1});
        }
    }


}
