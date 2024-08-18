package baekjoon.solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Boj12865 {
    static int[] weight;
    static int[] value;
    static int maxValue = 0;
    static int N;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(stringTokenizer.nextToken()); // 데이터 개수
        K = Integer.parseInt(stringTokenizer.nextToken());

        weight = new int[N];
        value = new int[N];

        // 무게, 가치 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            recursion(i, 0, 0);
        }

        System.out.println(maxValue);
    }

    private static void recursion (int index, int valueSum, int weightSum) {
        valueSum += value[index];
        weightSum += weight[index];

        if (weightSum > K) {
            return;
        }
        if (weightSum == K) {
            maxValue = Math.max(maxValue, valueSum);
            return;
        }
        for (int i = index + 1; i < N; i++) {
            if (weight[i] <= K - weightSum) {
                recursion(i, valueSum, weightSum);
            }
        }
        maxValue = Math.max(maxValue, valueSum);
    }
}
class Sol1 { //top-down
    static Integer[][] dp;
    static int[] W; // weight
    static int[] V; // value

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 데이터 개수
        int K = Integer.parseInt(st.nextToken()); // 지닐 수 있는 최대 무게

        W = new int[N]; // 무게 배열 초기화
        V = new int[N]; // 가치 배열 초기화

        dp = new Integer[N][K + 1]; // dp 배열 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(N - 1, K)); // 배열이 0~N-1까지 이므로 N-1 입력

    }

    static int knapsack(int i, int k) {
        // i가 0미만으로 범위 밖으로 나가면 0 리턴
        if (i < 0)
            return 0;

        // 아직 탐색하지 않은 경우
        if (dp[i][k] == null) {

            // 현재 물건(i)을 담지 못하는 경우 (이전 i값 탐색)
            if(W[i] > k) {
                dp[i][k] = knapsack(i - 1, k); // 만약 2번 아이템을 담을 수 없는 경우 1번의 값을 넣는 것
            }
            // 현재 물건(i)을 담을 수 있는 경우
            else {
                // 이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i])중 큰 값을 저장
                dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
                // 무게가 7이고 i=3(3, 6)인 경우, 이전 값인 13 vs (무게가 4일 때 i=2의 값 = 8) + 현재 아이템 가치(=6)
            }
        }
        return dp[i][k];
    }
}

class Sol2 { //bottom-up 1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 반복문 탐색 중 dp[i-1]을 하는 과정에서 인덱스 범위를 벗어날 수 있으므로 N + 1 크기로 초기화
        int[] W = new int[N + 1]; // 무게
        int[] V = new int[N + 1]; // 가치
        int[][] dp = new int[N + 1][K + 1]; // K는 0부터 N까지 값을 저장하기 위한 크기

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {

                // i번째 무게를 더 담을 수 없는 경우
                if(W[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                // i번째 무게를 더 담을 수 있는 경우
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
                }

            }
        }
        System.out.println(dp[N][K]);
    }
}

class Sol3 { // bottom-up 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // N + 1을 하는 이유는 위와 같다.
        int[] W = new int[N + 1]; // 무게
        int[] V = new int[N + 1]; // 가치
        int[] dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            // K부터 탐색하여 담을 수 있는 무게 한계치가 넘지 않을 때까지 반복
            // 위 방법들에 비해 불필요한 반복 횟수를 줄여 성능을 높일 수 있다.
            for (int j = K; j - W[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
            }
        }
        System.out.println(dp[K]);
    }
}