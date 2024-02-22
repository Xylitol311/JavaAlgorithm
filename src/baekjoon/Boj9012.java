package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            sb.append(solve(br.readLine())).append('\n'); // 괄호를 한 줄씩 입력받아 solve 메소드로 보내고 리턴받은 결과값을 '\n'를 이용해 한 줄씩 sb에 저장한다.
        }

        System.out.println(sb);
    }

    public static String solve(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // 여는 괄호일 경우 스택에 push
            if (c == '(') {
                stack.push(c);
            }

            // 스택이 비어있는데 닫는 괄호를 입력받는 경우 VPS가 아니다.
            else if (stack.empty()) {
                return "NO";
            }
            // 그 외의 경우 스택 원소를 pop 한다.
            else {
                stack.pop();
            }
        }
        // 모든 검사를 마치고 스택이 비어있다면 완전한 VPS이고 그렇지 않으면 "NO"를 출력한다.
        if (stack.empty()) {
            return "YES";
        }
        else {
            return "NO";
        }
    }
}
