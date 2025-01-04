// 메모리: 14320 KB / 시간: 128 ms

/*
 * 풀이 방법
 * 1. N=1 일때는 예외처리
 *    - 6개의 면 중 가장 큰 수를 제외한 5개의 면의 합이 답
 *
 * 2. N≥2 일때
 *    - 1면이 보이는 주사위: 5(N-2)^2 + 4(N-2)개
 *    - 2면이 보이는 주사위: 8(N-2) + 4개
 *    - 3면이 보이는 주사위: 4개
 *
 * 3. 각 경우의 최소값 계산
 *    - 1면의 최소값: 주사위 6면 중 가장 작은 값
 *    - 2면의 최소값: 인접한 두 면의 합 중 최소값 (마주보는 면은 제외)
 *    - 3면의 최소값: 세 면의 합 중 최소값
 *
 * 4. (각 경우의 개수 * 각 경우의 최소값)의 총 합이 정답
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long N;
    static int[] dice = new int[6];
    static long[] visible = new long[4];  // 보이는 면의 개수 (1면, 2면, 3면)
    static long result;

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // 보이는 면의 개수 계산
        visible[1] = 5 * (N - 2) * (N - 2) + 4 * (N - 2);  // 1면이 보이는 주사위 개수
        visible[2] = 8 * (N - 2) + 4;  // 2면이 보이는 주사위 개수
        visible[3] = 4;  // 3면이 보이는 주사위 개수
    }
    

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(result);
    }

    private static void solution() {
        if (N == 1) {
            int sum = 0;
            int max = 0;
            for (int i = 0; i < 6; i++) {
                sum += dice[i];
                max = Math.max(max, dice[i]);
            }
            result = sum - max;
            return;
        }

        // 1면의 최솟값 계산
        long min1 = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            min1 = Math.min(min1, dice[i]);
        }
        result += visible[1] * min1;

        // 2면의 최솟값 계산
        long min2 = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (i + j != 5) {  // 마주보는 면이 아닌 경우만
                    min2 = Math.min(min2, dice[i] + dice[j]);
                }
            }
        }
        result += visible[2] * min2;

        // 3면의 최솟값 계산
        int min3 = 0;
        for (int i = 0; i < 3; i++) {
            min3 += Math.min(dice[i], dice[5 - i]);
        }
        result += visible[3] * min3;
    }
}