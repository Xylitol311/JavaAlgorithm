// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
Z모양으로 2차원 배열을 재귀적으로 탐색할 때, 특정 위치(r,c)가 몇 번째로 방문되는지를 구하는 문제.

[풀이]
분할 정복 이용
1. 각 단계에서 2^N * 2^N 크기의 배열을 4등분하여 어느 사분면에 (r, c)가 속하는지 판단
2. 해당 사분면 이전의 모든 사분면의 칸 수를 더함
3. 재귀적으로 위 과정을 반복
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, r, c, answer;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        input();

        System.out.println(recursion(N, r, c));
    }

    private static long recursion(int N, int r, int c) {
        // 기저 조건
        if (N == 0) return 0;

        // 부분 사각형의 크기 계산 2^(N-1)
        long size = 1L << (N - 1);

        // 어느 사분면에 속하는지 계산한다.
        int quadrant = 0;

        // 행이 size보다 크거나 같으면 아래쪽 사분면(0,1,2,3 중 2,3)
        if (r >= size) {
            quadrant += 2; // 사분면 이동
            r -= size; // 다음 재귀를 위해 위치 변환
        }

        // 열이 size보다 크거나 같으면 오른쪽 사분면 (1, 3 중 하나)
        if (c >= size) {
            quadrant += 1;
            c -= size; // 다음 재귀 위해 위치 변환
        }

        // 결과 계산
        // 현재 사분면 이전까지의 칸 수 + 현재 사분면 내에서의 위치를 재귀를 통해 계산
        return quadrant * (size * size) + recursion(N-1, r, c);
    }
}