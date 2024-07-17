import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int sum = 0;
		String num = sc.next();
		for (int i = 0; i < num.length(); i++) {
			sum += (int) num.charAt(i) - 48;
		}
		System.out.println(sum);
	}
}
