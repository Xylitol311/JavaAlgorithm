package baekjoon.study8;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 스택을 이용한 풀이
public class boj17413_단어뒤집기 {
    static Stack<Character> stack = new Stack<>(); // 문자열을 뒤집기 위한 스택
    static StringBuilder answer = new StringBuilder();

    public static void main(String args[]) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // Solution
        boolean isTag = false; // 태그 판별
        for (int idx = 0; idx < str.length(); idx++) {
            // 태그 오픈 시 이전까지의 값이 있다면 스택에서 꺼내어 answer에 저장
            if (str.charAt(idx) == '<') {
                reverse();
                answer.append(str.charAt(idx));
                // 태그 표시를 true로 변환
                isTag = true;
                continue;
            }
            // 태그를 닫으면서 태그 표시 false로 전환
            if (str.charAt(idx) == '>') {
                isTag = false;
                answer.append(str.charAt(idx));
                continue;
            }
            // 태그 표시가 true면 그대로 입력
            if (isTag == true) {
                answer.append(str.charAt(idx));
                continue;
            }

            // ====여기부터 태그가 아닌 경우====
            // 현재 문자가 공백일 경우 이전까지 쌓인 문자열 뒤집기
            if (str.charAt(idx) == ' ') {
                reverse();
                answer.append(str.charAt(idx));
                continue;
            }
            // 그외의 경우, 문자열을 뒤집기 위해 스택에 추가
            stack.push(str.charAt(idx));
        }
        reverse();
        System.out.println(answer);
    }

    private static void reverse() {
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
    }
}
