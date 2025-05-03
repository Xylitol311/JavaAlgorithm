import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays; // Optional: if sorting A is desired, though not strictly needed for this DP

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N; // 요리사 수
    static long K; // 만들어야 할 음식 개수 (K가 크므로 long)
    static int C; // 격려 횟수
    static int[] A; // 요리사별 조리 시간 (Ai가 크므로 int는 OK, 계산 시 long 필요)

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken()); // K는 long으로 받음
        C = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static long solution() {
        // 최소 시간(low)과 최대 시간(high) 설정 후 이분 탐색
        long low = 1;
        // 상한 설정: 가장 느린 요리사가 격려 없이 혼자 K개 만드는 시간보다 크거나 같음
        // K * max(A_i) 도 충분히 크지만, 간단히 10^12 등 충분히 큰 값 사용
        long high = 1_000_000L * 1_000_000L + 1; // K * max(A_i) + margin
        long ans = high; // 가능한 최소 시간을 저장할 변수

        while (low <= high) {
            long mid = low + (high - low) / 2; // 중간 시간

            if (can(mid)) {
                // mid 시간 안에 K개 요리 가능 -> 시간을 줄여본다
                ans = mid;
                high = mid - 1;
            } else {
                // mid 시간 안에 K개 요리 불가능 -> 시간을 늘려야 한다
                low = mid + 1;
            }
        }

        return ans;
    }

    // 주어진 시간 T 안에 K개 이상의 요리를 만들 수 있는지 판단하는 함수
    static boolean can(long T) {
        // dp[i][j] = 첫 i명의 요리사를 사용하여 총 j번 이하의 격려를 했을 때,
        // 시간 T 내에 만들 수 있는 최대 요리 개수
        long[][] dp = new long[N + 1][C + 1];

        // dp 테이블 초기화 (java에서는 long 배열은 0으로 자동 초기화됨. 여기서는 0이 의미있는 값이므로 별도 초기화 불필요)

        // DP 계산
        for (int i = 1; i <= N; i++) { // i번째 요리사 (배열 인덱스 i-1)
            for (int j = 0; j <= C; j++) { // 총 j번 이하의 격려 사용

                // 1. i번째 요리사가 격려를 0번 받은 경우
                // 앞선 i-1명의 요리사가 j번 이하의 격려를 사용한 최대 요리 개수 + i번째 요리가 T 시간 동안 만든 요리 개수
                long currentTime = A[i - 1];
                long dishesByCurrentChef = T / currentTime;
                dp[i][j] = dp[i - 1][j] + dishesByCurrentChef; // Math.max는 필요 없음, 왜냐면 이건 "0번 격려 시" 경우만 포함

                // 2. i번째 요리사가 격려를 e번 (1 <= e <= j) 받은 경우
                for (int e = 1; e <= j; e++) {
                    // 격려 후 조리 시간 (1초 미만으로 줄어들지 않음)
                    currentTime = Math.max(1L, (long)A[i - 1] - e); // A[i-1] - e 계산 시 int 범위 넘어갈 수 있으므로 long 형변환
                    dishesByCurrentChef = T / currentTime;

                    // 앞선 i-1명의 요리사가 j-e번 이하의 격려를 사용한 최대 요리 개수 + 현재 요리사가 만든 요리 개수
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - e] + dishesByCurrentChef);
                }
            }
        }

        // 최종 상태 (N명의 요리사를 모두 사용하고 C번 이하의 격려를 사용)의 최대 요리 개수가 K 이상인지 확인
        // 사실 j <= C 이므로 dp[N][C]만 확인해도 충분
        return dp[N][C] >= K;

        /*
        // 만약 "정확히 j번의 격려"로 dp 상태를 정의했다면, 마지막에 dp[N][0]부터 dp[N][C]까지 max를 찾아 K와 비교해야 함
        // 하지만 "j번 이하의 격려"로 정의하면 dp[N][C]에 최종적으로 최대 요리 개수가 저장됨
        long maxDishes = 0;
        for(int j=0; j<=C; ++j) {
             maxDishes = Math.max(maxDishes, dp[N][j]);
        }
        return maxDishes >= K;
        // 위 코드블럭 대신 return dp[N][C] >= K; 를 사용하는 것이 "j번 이하의 격려" 정의에 맞음
        */
    }
}