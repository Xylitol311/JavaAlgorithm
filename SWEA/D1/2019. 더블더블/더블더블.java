import java.util.Scanner;

class Solution {
	
	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner (System.in);
		int input = sc.nextInt();
		int result = 1;
		
//		for (int i = 0; i<=input; i++) {
//			System.out.print((int) Math.pow(2, i) + " ");
//		}
		
		for (int i = 0 ; i<input; i++) {
			if (i==0) {
				System.out.print(result + " ");
			}
			System.out.print((result *= 2) + " ");
		}
	}
}
