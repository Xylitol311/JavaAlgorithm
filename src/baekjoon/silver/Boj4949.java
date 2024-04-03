package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String check = br.readLine();
        while (!check.equals(".")) {
            sb.append(solution(check)).append("\n");
            check = br.readLine();
        }
        System.out.println(sb);
    }

    private static String solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[') {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return "no";
                }
                continue;
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return "no";
                }
            }
        }

        if (stack.isEmpty()) {
            return "yes";
        } else {
            return "no";
        }
    }
}
