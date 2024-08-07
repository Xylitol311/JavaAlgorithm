import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String word= sc.nextLine();
		int n = sc.nextInt();
		
		char ans = word.charAt(n-1);
		System.out.println(ans);

	}

}