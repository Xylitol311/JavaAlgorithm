import java.io.*;
import java.util.*;


/*
선형 dp를 이용하여 해결
1. 처음에 N(지름길 개수)과 D(최종 목적지 거리)를 입력받음
2. D + 1만큼의 int[] dp 배열 생성하고 각 인덱스값에 0부터 D+1까지 입력
3. 지름길 정보를 Road 인접 리스트를 활용하여 저장
4. 순차적으로 최단 거리를 계산
    3-1. 이전 지점값을 기준으로 현재 지점 거리 업데이트
    3-2. 현재 지점에서 출발하는 지름길이 있다면
5. 모든 계산이 끝나면 dp[D] 출력
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];

        // dp 초기화
        for (int i = 1; i <= D; i++) {
            dp[i] = i;
        }

        // 지름길 입력
        List<Road>[] roads = new ArrayList[D + 1];
        for (int i = 0; i <= D; i++) {
            roads[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 예외처리
            if (start > D || end > D || weight >= end - start) continue;

            roads[start].add(new Road(start, end, weight));
        }

        // 최단 거리 계산
        for (int i = 0; i <= D; i++) {
            // 이전 거리 기준 최단 경로 업데이트
            if (i > 0) {
                dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
            }

            // 현 위치에서 시작하는 지름길 계산
            for (Road road : roads[i]) {
                dp[road.end] = Math.min(dp[i] + road.weight, dp[road.end]);
            }
        }

        // 정답 출력
        System.out.println(dp[D]);
    }

    static class Road{
        int start, end, weight;

        Road(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}