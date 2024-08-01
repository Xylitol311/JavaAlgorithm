import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			answer.append(stack(br.readLine())).append("\n");
		}
		System.out.println(answer);
	}
	
	private static String stack(String line) {
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(') {
				stack.push('*');
			} else if(stack.isEmpty()) {
				return "NO";
			} else {
				stack.pop();
			}
		}
		
		if (stack.isEmpty()) {
			return "YES";
		} else {
			return "NO";
		}
	}
}
