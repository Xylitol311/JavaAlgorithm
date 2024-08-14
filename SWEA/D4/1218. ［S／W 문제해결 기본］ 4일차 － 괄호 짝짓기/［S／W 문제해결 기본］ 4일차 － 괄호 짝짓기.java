import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int stringLength;
	static char[] parenthesis;

	public static void input() throws IOException {
		stringLength = Integer.parseInt(br.readLine());
		parenthesis = br.readLine().toCharArray();
	} // input

	public static int check() {
		Stack<Character> stack = new Stack<>(); 
		for (int idx = 0; idx < stringLength; idx++) {
			char nowChar = parenthesis[idx];
			// 괄호 열기
			if (nowChar == '(' || nowChar == '<' || nowChar == '[' || nowChar == '{') {
				stack.add(nowChar);
				continue;
			}
			// 괄호 닫기
			if (nowChar == ')' && stack.peek() != '(') {
				return 0;
			}
			// 홑화살 괄호 닫기
			if (nowChar == '>' && stack.peek() != '<') {
				return 0;
			}
			// 대괄호 닫기
			if (nowChar == ']' && stack.peek() != '[') {
				return 0;
			}
			// 중괄호 닫기
			if (nowChar == '}' && stack.peek() != '{') {
				return 0;
			}
			
			stack.pop();
		}

		if (!stack.isEmpty()) return 0;
		
		return 1;
	}

	public static void main(String[] args) throws IOException {
		int answer;
		for (int tc = 1; tc <= 10; tc++) {
			input();
			answer = check();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		} // test case 반복
		System.out.println(sb);
	}// main
}
