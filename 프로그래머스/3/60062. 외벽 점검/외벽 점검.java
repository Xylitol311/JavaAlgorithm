import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int W = weak.length;
        int full = (1 << W) - 1; // 모든 취약 지점이 커버된 상태 (예: W=4 → 1111)

        // 1) dist 내림차순 정렬 (k명 필요시 가장 큰 k명을 쓰는 것이 최적)
        Integer[] sortedDist = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        Arrays.sort(sortedDist, (a, b) -> b - a);

        // 2) 사전 계산: cover[j][d] = weak[j]에서 시작해 거리 d만큼 시계방향으로 커버하는 비트마스크
        int[][] cover = new int[W][sortedDist.length];
        for (int j = 0; j < W; j++) {
            for (int di = 0; di < sortedDist.length; di++) {
                int mask = 0;
                for (int k = 0; k < W; k++) {
                    // weak[j]에서 weak[k]까지의 시계방향 거리가 d 이하인지 확인
                    if ((weak[k] - weak[j] + n) % n <= sortedDist[di]) {
                        mask |= (1 << k);
                    }
                }
                cover[j][di] = mask;
            }
        }

        // 3) 비트마스크 DP
        //    dp[mask] = mask 상태의 취약 지점들을 커버하는 데 필요한 최소 친구 수
        int[] dp = new int[1 << W];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << W); mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;
            if (dp[mask] >= sortedDist.length) continue; // 더 이상 투입할 친구 없음

            int distIdx = dp[mask]; // 다음에 사용할 친구의 인덱스 (내림차순 기준)

            // 아직 커버되지 않은 모든 취약 지점을 시작점으로 시도
            for (int j = 0; j < W; j++) {
                if ((mask & (1 << j)) != 0) continue; // 이미 커버된 지점은 스킵

                int newMask = mask | cover[j][distIdx];
                dp[newMask] = Math.min(dp[newMask], dp[mask] + 1);
            }
        }

        return dp[full] == Integer.MAX_VALUE ? -1 : dp[full];
    }
}