import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int A = sc.nextInt();
	int X = sc.nextInt();
	
	for (int tc =0; tc<A; tc++) {
		int num = sc.nextInt();
		if (num<X) {
			System.out.print(num);
			System.out.print(" ");
		}
	}
	
	}

}