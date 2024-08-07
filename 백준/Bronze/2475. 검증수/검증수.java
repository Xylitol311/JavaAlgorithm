import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nums =0;
		for (int i=0; i<5; i++) {
			int num = sc.nextInt();
			nums +=num*num;
		}
		
		int remainder = (nums%10);
		System.out.println(remainder);
		
	}
}
