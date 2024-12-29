// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
브루트 포스 방식으로 N부터 시작해서 뒤에 0부터 9까지의 숫자를 하나씩 붙여보면서 N의 모든 자리수로 나누어 떨어지는지 확인한다.
나누어 떨어지는 경우 그 수를 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long N;

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        System.out.println(solution());
    }

    private static long solution() {
        Queue<Long> queue = new ArrayDeque<>();
        queue.add(N);
        while (!queue.isEmpty()) {
            long cur = queue.poll();
            if (isPossible(cur)) {
                return cur;
            }
            for (int i = 0; i <= 9; i++) 
                queue.add(cur * 10 + i);
        }

        return 0;
    }

    private static boolean isPossible(long cur) {
        long base = N;
        while (base != 0) {
            if (base % 10 != 0 && cur % (base % 10) != 0)
                return false;
            base /= 10;
        }
        return true;
    }

}