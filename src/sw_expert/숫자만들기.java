package sw_expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int totalNumCnt; // 총 숫자 개수
    static char[] operators;// 연산자 배열
    static int[] numArr;// 계산할 숫자 배열

    static int max;
    static int min;
    static int answer;//max - min


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solution();
            print(tc);
        }
        System.out.println(sb);
    }

    private static void solution() {
        // 정렬
        Arrays.sort(operators);

        // Next Permutation으로 모든 순열 케이스 탐색
        do {// 모든 순열 경우의 수에 대해 max, min 계산
            calculate();
        } while (np());

        answer = max - min;
    }

    private static void calculate() {
        // 첫 숫자
        int num = numArr[0];

        for (int idx = 0; idx < operators.length; idx++) {
            char operator = operators[idx];

            if (operator == '+') {
                num += numArr[idx + 1];
                continue;
            }
            if (operator == '-') {
                num -= numArr[idx + 1];
                continue;
            }
            if (operator == '*') {
                num *= numArr[idx + 1];
                continue;
            }
            // 나눗셈(소수점 버림)
            num = (int) Math.floor(num / numArr[idx + 1]);
        }

        max = Math.max(max, num);
        min = Math.min(min, num);
    }

    static boolean np() { // boolean : true(다음 순열 존재), false(다음 순열 없음)
        int N = operators.length;
        // step1. 꼭대기 찾기 (교환 위치 찾기)
        int i = N - 1;
        while (i > 0 && operators[i - 1] >= operators[i])
            --i;
        if (i == 0) // 모든 순열을 다 돈 경우
            return false;

        // step2. 꼭대기 앞 교환위치에 교환할 값 찾기 (i - 1 위치 값보다 큰 값중 가장 작은 값) 자리 찾기
        int j = N - 1;
        while (operators[i - 1] >= operators[j])
            --j;
        // step3. swap
        swap(operators, i - 1, j);

        // step4. 꼭대기 맨 뒤까지 오름차순의 형태
        int k = N - 1;
        while (i < k) {
            swap(operators, i++, k--);
        }
        return true;
    }

    static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void input() throws IOException {
        totalNumCnt = Integer.parseInt(br.readLine());
        operators = new char[totalNumCnt - 1];

        // 변수 초기화
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;


        // 연산자 입력
        st = new StringTokenizer(br.readLine());
        int operatorIdx = 0;
        for (int i = 0; i < 4; i++) { // 연산자 개수 총 4개
            int num = Integer.parseInt(st.nextToken());
            if (i == 0) { // +
                for (int j = 0; j < num; j++) {
                    operators[operatorIdx++] = '+';
                }
            } else if (i == 1) { // -
                for (int j = 0; j < num; j++) {
                    operators[operatorIdx++] = '-';
                }
            } else if (i == 2) { // *
                for (int j = 0; j < num; j++) {
                    operators[operatorIdx++] = '*';
                }
            } else {
                for (int j = 0; j < num; j++) {
                    operators[operatorIdx++] = '/';
                }
            }
        }

        // 숫자 입력
        st = new StringTokenizer(br.readLine());
        numArr = new int[totalNumCnt];
        for (int idx = 0; idx < totalNumCnt; idx++) {
            numArr[idx] = Integer.parseInt(st.nextToken());
        }
    }

    private static void print(int tc) {
        sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }
}
