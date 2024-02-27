package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2830 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] names = new int[N];
        int[] count = new int[20];
        long sum =0;

        // 모든 주민 이름 담기
        for (int i = 0; i < N; i++) {
            names[i] = Integer.parseInt(br.readLine());
        }

        // 각 자리수의 1의 개수 세기
        for (int i = 0; i < N; i++) {
            int name = names[i];
            int idx = 0;
            while (name > 0) {
                count[idx++] += name % 2;
                name /= 2;
            }
        }

        // 행성 가치 구하기
        for (int i = 19; i >= 0; i--) {
            sum += (long) count[i] * (N - count[i]);
            sum <<= 1;
        }
        sum >>= 1;
        System.out.println(sum);
    }
}