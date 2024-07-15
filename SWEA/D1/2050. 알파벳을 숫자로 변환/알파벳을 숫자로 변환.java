import java.util.Scanner;

class Solution {
	
	public static void main(String[] args) throws Exception{
		
//		String var = "ABCDEFG";
//		System.out.println(var.length());
		
		int ascii = 0;
		char chr;
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		for (int i=0; i<str.length(); i++) {
			chr = str.charAt(i);
			ascii = (int) chr;
			System.out.print(ascii-64 + " ");
		}
	}
}
