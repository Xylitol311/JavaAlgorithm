import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N =sc.nextInt();
		
		for (int n =1; n<10; n++) {
			System.out.println(N+" "+"*"+" "+n+" "+"="+" "+N*n);
		}
		
	}
}