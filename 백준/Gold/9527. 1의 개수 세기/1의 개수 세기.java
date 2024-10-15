// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
단순 반복으로 풀면 O(n) 시간복잡도로 시간 초과 발생
이진수의 특징을 이용해 N이라는 숫자까지 각 자릿수별 1의 개수를 구하여 합하면 N까지의 1의 개수 합을 구할 수 있다.
이때 이진수의 각 자릿수 별 1의 개수는 자릿수 * 2마다 자릿수개의 1의 개수를 갖는 특징이 있다.
1의 자리의 경우 2개마다 1개의 1, 2의 자리인 경우 4개마다 2개의 1, 4의 자리인 경우 8개마다 4개의 1이 존재한다.
이를 이용해 N까지 각 자릿수 별로 패턴을 이용한 개수와 패턴에 맞게 떨어지지 않고 남은 경우 그만큼의 1만 구해주면 된다.
나머지의 경우 2의 자리를 예시로 들면 4개마다 앞에 두 수는 2의 자리가 0, 뒤에 두 수는 2의 자리가 1인 패턴이 반복된다.
이 네 개의 수를 4로 나누면 나머지는 0, 1, 2, 3이 되고 나머지가 2와 3인 경우만 2의 자리가 1이다.
나머지가 2인 경우 2의 자리의 1의 개수가 한 개, 나머지가 3인 경우는 1의 개수가 두개가 된다.
즉 현재 숫자를 (자릿수 * 2)로 나눈 나머지에 자릿수를 빼고 +1을 하면 현재 숫자까지의 그 자릿수의 1의 개수를 구할 수 있다.
n % (자릿수 * 2) - 자릿수 + 1
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long start, end;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    private static long solution() {
        // 각 숫자까지의 누적합의 차이를 출력
        return countOne(end) - countOne(start - 1);
    }

    private static long countOne(long n) {
        long cnt  = 0;
        long digits = 1; // 이진수의 자리를 나타냄
        while (digits <= n) {
            long groupCnt = (n / (digits * 2)) * digits;
            long remainCnt = Math.max(0, n % (digits * 2) - digits + 1);
            cnt += groupCnt + remainCnt;

            digits *= 2;
        }

        return cnt;
    }
}