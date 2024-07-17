import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] alphabet;

		int count = 0;
		String word;
		boolean check = true; // false면 그룹 단어가 아님
		for (int i = 0; i < n; i++) {
			word = br.readLine();
			check = true;
			alphabet = new int[27];
			for (int j = 0; j < word.length(); j++) {
				// 직전 글자랑 같으면 통과
				if (j >= 1 && word.charAt(j) == word.charAt(j - 1))
					continue;
				// 처음 입력되는 글자가 아닌 경우 break;
				if (alphabet[word.charAt(j) - 96] != 0) {
					check = false;
					break;
				}

				// 처음 입력된 글자면 1
				alphabet[word.charAt(j) - 96] = 1;
			}
			if (check) count++;
		}
		System.out.println(count);
	}
}
