package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj4779_칸토어집합 {
    static int n;
    static StringBuilder answer;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            n = Integer.parseInt(str);
            answer = new StringBuilder();
            int len = (int) Math.pow(3, n);
            //길이가 3^n 이고 '-'로 이루어진 문자열 미리 만들어 놓고, 재귀함수 시작하기
            for (int i = 0; i < len; i++) {
                answer.append("-");
            }

            recursion(0, len); // 시작 인덱스, 시작 길이
            System.out.println(answer);

        }
    }

    public static void recursion(int start, int size) { //시작 인덱스, 문자열 사이즈
        //size가 1인 경우 리턴
        if (size == 1) {
            return;
        }
        int newSize = size / 3;

        // 3등분한 문자열의 가운데는 공백 처리
        for (int i = start + newSize; i < start + 2 * newSize; i++) {
            answer.setCharAt(i, ' ');
        }

        recursion(start, newSize); // 3등분한 문자열에서 첫번째 부분
        recursion(start + 2 * newSize, newSize); // 3등분한 문자열의 세번째 부분
    }
}

