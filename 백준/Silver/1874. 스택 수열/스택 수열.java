import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;

    private static List<Integer> input() throws IOException {
        N = Integer.parseInt(br.readLine());
        List<Integer> inputNums = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            inputNums.add(Integer.parseInt(br.readLine()));
        }

        return inputNums;
    }

    public static void main(String[] args) throws IOException {
        solution(input());
        System.out.println(sb.toString());
    }

    private static void solution(List<Integer> inputNums) {
        int nextPushNum = 1;

        Stack<Integer> stack = new Stack<>();

        for (int target : inputNums) {
            // 타겟 숫자와 일치할 때까지 순서대로 push
            while (nextPushNum <= target) {
                stack.push(nextPushNum);
                sb.append("+").append("\n");
                nextPushNum++;
            }

            // 스택이 비었거나 현재 스택의 최상단 값이 target과 일치하지 않으면 실패
            if (stack.isEmpty() || stack.peek() != target) {
                sb = new StringBuilder();
                sb.append("NO");
                return;
            }

            // 일치한다면 pop
            stack.pop();
            sb.append("-").append("\n");
        }

    }
}