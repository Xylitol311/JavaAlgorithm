package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String query = br.readLine();

            switch (query.charAt(0)) {
                case '1' :
                    stack.push(Integer.parseInt(query.substring(2)));
                    break;
                case '2' :
                    sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
                    break;
                case '3' :
                    sb.append(stack.size()).append("\n");
                    break;
                case '4' :
                    sb.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;
                case '5' :
                    sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
