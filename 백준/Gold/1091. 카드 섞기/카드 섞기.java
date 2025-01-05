// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
특정 카드가 목표 플레이어에게 분배되도록 하기 위해 카드를 최소 몇번 섞어야 하는지 구하는 문제
1. 카드의 초기 상태와 목표 상태를 비교하면서 섞는 과정을 반복하고, 주어진 패턴으로 카드 배열을 변경한다.
2. 이때 만약 카드가 초기 상태로 돌아오면 루프가 발생하므로 '-1'을 출력한다.
 */
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, result; // 카드 수, 섞는 횟수
    static int p[], order[], cards[], compare[], next[];

    private static void input() throws IOException {
        N = Integer.parseInt(in.readLine());
        result = 0;

        p = new int[N]; // 각 카드의 최종 위치가 속한 플레이어 정보
        order = new int[N]; // 카드 섞기 패턴 배열
        cards = new int[N]; // 현재 카드 상태 저장 배열

        // 플레이어 입력
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++)
            p[i] = Integer.parseInt(st.nextToken());

        // 섞는 순서 정보 입력
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            order[Integer.parseInt(st.nextToken())] = i;
            cards[i] = i % 3;
        }

        compare = cards.clone(); // 초기 상태를 저장. 루프 확인용
        next = new int[N]; // 섞은 결과를 저장할 배열
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    private static void solution() {
        // 반복적으로 카드 상태를 섞으며 확인
        // 현재 상태가 목표 상태와 다르면서 루프가 발생하지 않았을 때
        while (!Arrays.equals(cards, p) && !(result != 0 && Arrays.equals(cards, compare))) {
            // 카드 섞기
            for (int j = 0; j < N; j++)
                next[order[j]] = cards[j];
            // 상태 업데이트
            cards = next.clone();
            result++;
        }

        if (result != 0 && Arrays.equals(cards, compare))
            System.out.println(-1); // 루프가 발생한 경우
        else System.out.println(result);
    }
}