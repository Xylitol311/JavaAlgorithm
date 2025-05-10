import java.io.*;
import java.util.*;

public class Main {
    // 전역 변수 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] a;            // 학생들이 쓴 숫자 배열
    static int[] freq;         // 각 숫자의 빈도
    static int[] cnt;          // 배수 합산 결과

    public static void main(String[] args) throws IOException {
        input();      // 입력 처리
        solution();   // 문제 풀이
        System.out.print(sb.toString()); // 결과 출력
    }

    // 입력 함수: N과 a 배열을 읽고 freq 초기화
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        a = new int[N];
        // 가능한 숫자의 최댓값 (문제 제약)
        int MAX = 1000000;
        freq = new int[MAX + 1];
        cnt  = new int[MAX + 1];

        // 각 학생이 쓴 숫자를 읽고 freq 증가
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
            freq[a[i]]++;
        }
    }

    // 풀이 함수: 빈도 기반 배수 합산 후 각 학생별로 결과 계산
    static void solution() {
        int MAX = 1000000;

        // 1) 에라토스테네스식으로 각 값 m에 대해
        //    freq[d]를 d의 모든 배수 m에 더해준다.
        //    -> cnt[m]에는 자신을 포함해 머리톡톡 가능한 사람 수가 저장됨
        for (int d = 1; d <= MAX; d++) {
            if (freq[d] == 0) continue;  // d를 쓴 학생이 없으면 건너뛴다
            for (int m = d; m <= MAX; m += d) {
                cnt[m] += freq[d];
            }
        }

        // 2) 각 학생 i가 친 횟수 = cnt[a[i]] - 1 (자기 자신 제외)
        for (int i = 0; i < N; i++) {
            sb.append(cnt[a[i]] - 1).append('\n');
        }
    }
}