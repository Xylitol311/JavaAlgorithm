import java.util.Arrays;
import java.util.Scanner;

class Solution {
	
	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int num = 0;
		int[] ans = new int[T];
		
		for (int i = 0; i<T; i++) {
			num = sc.nextInt();
			ans[i] = num;
		}
		
		Arrays.sort(ans);
		System.out.println(ans[T/2]);
		
	}
}
