

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 플로이드 워셜로 모든 쌍의 최단 거리 구하기
 * 2. 각 사람의 점수 계산: 다른 모든 사람과의 최단 거리 중 최대값을 구함
 * 3. 점수가 가장 낮은 사람을 찾고, 그 사람의 점수와 인원 수를 출력
 * 4. 후보자들의 번호 출력
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] graph;
    static int minScore = Integer.MAX_VALUE;
    static List<Integer> candidates = new ArrayList<>();

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], N + 1);
            graph[i][i] = 0; // 자기 자신과의 거리는 0으로 설정
        }

        // 정점 간의 관계 입력
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1) {
                break; // -1이 나오면 중지
            }

            graph[a][b] = 1;
            graph[b][a] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {
        // 1. 플로이드 워셜 실행
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // 2. 각 정점별 최대값 찾기(최대로 거치는 관계 수) 이때 전체 최솟값도 같이 갱신
        for (int i = 1; i <= N; i++) {
            int max = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j) { // 자기 자신과의 거리는 제외
                    max = Math.max(graph[i][j], max);
                }
            }
            graph[i][0] = max; // 각 정점의 비어있는 0번 인덱스에 최댓값 저장.
            minScore = Math.min(max, minScore);
        }

        // 3. 최대 관계수가 전체 최솟값과 일치하는 정점 찾아 후보 목록에 등록
        for (int i = 1; i <= N; i++) {
            if (graph[i][0] == minScore) {
                candidates.add(i);
            }
        }

        // 4. 결과 출력
        // 점수랑 후보 수 등록
        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        // 후보 등록
        for (int num : candidates) {
            sb.append(num).append(" ");
        }
    }
}