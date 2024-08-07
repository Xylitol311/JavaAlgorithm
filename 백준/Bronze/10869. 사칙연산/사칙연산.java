import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int A = sc.nextInt();
	int B = sc.nextInt();
	
	int add = A+B;
	int substract = A-B;
	int multiply = A*B;
	int divide = A/B;
	int remainder = A%B;
	
	System.out.println(add);
	System.out.println(substract);
	System.out.println(multiply);
	System.out.println(divide);
	System.out.println(remainder);
	
	}

}