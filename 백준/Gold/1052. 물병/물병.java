// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
주어진 물 n이 k보다 이하 라면 -> 그대로 들고 감
주어진 물 n이 k보다 작다면 -> 물병들을 일부 합쳐야 함.

물병이 홀수개면 1개를 추가 구매하고 2로 나눈다.
짝수가 될 때까지 반복.

이때 반복 횟수가 상점에서 물병을 산 횟수와 같다.

비트 마스킹을 이용.
- n을 2진수로 변환했을 때 1의 갯수가 k보다 클 때만 반복한다.
- 1이 처음으로 나타나면 합칠 때 홀수가 발생하는 숫자이므로 그 위치의 수만큼 cnt를 늘려준다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, k;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int answer = 0;

        // n을 2진수로 변환했을 때 1의 갯수가 k보다 클 때만 반복
        while (Integer.bitCount(n) > k) {
            answer += n & (-n);
            n += n & (-n);
        }
        
        System.out.println(answer);
    }
}