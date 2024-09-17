import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
이분 탐색
입력된 배열을 정렬하고 이분 탐색을 이용해 조카 모두에게 나눠줄 수 있는 최대 길이를 탐색
1. 이분 탐색을 이용해 중간값을 찾고 그 값으로 조카 모두에게 과자를 나눠줄 수 있는 지 확인
    1-1. 1~배열 중 가장 큰 과자의 길이를 이분 탐색한다.(시작할 때 leftIdx는 1, rightIdx는 과자 중 가장 긴 길이)
    1-2. 그 다음 중간값을 구해서 조카 모두에게 과자를 나눠줄 수 있는 지 확인
    1-1. (나눠줄 수 있다면) 그 값을 기존 정답과 비교해서 최댓값으로 정답을 갱신.
    1-2. leftIdx를 중간값 + 1로 설정 후 위 작업 반복
    1-3. (나눠줄 수 없다면) rightIdx를 중간값 - 1로 설정 후 같은 작업 반복
2. 이분탐색이 끝나고 최종 정답을 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int result, numOfNephews, numOfSnack, snacks[];

    private static void input() throws IOException {
        // 정답 초기화
        result = 0;

        // 조카, 과자 수 입력
        st = new StringTokenizer(br.readLine());
        numOfNephews = Integer.parseInt(st.nextToken());
        numOfSnack = Integer.parseInt(st.nextToken());
        snacks = new int[numOfSnack];

        // 과자 종류 입력
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < numOfSnack; idx++) {
            snacks[idx] = Integer.parseInt(st.nextToken());
        }

        // 스낵 정렬
        Arrays.sort(snacks);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(result);
    }

    private static void solution() {
        int left = 1; // 가장 짧은 과자의 길이
        int right = snacks[numOfSnack - 1]; // 가진 것 중 가장 긴 과자의 길이

        while (left <= right) {
            int mid = (right + left) / 2;
            int cnt = 0;

            // 갖고 있는 과자에서 현재 중간값으로 만들 수 있는 과자의 수를 구한다.
            for (int snack : snacks) cnt += snack / mid;

            if (cnt >= numOfNephews) { // 현재 중간값으로 조카 모두에게 나눠줄 수 있는 경우
                result = Math.max(result, mid);
                left = mid + 1;
            }
            else right = mid - 1;
        }
    }
}