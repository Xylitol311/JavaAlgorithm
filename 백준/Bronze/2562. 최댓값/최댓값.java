import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int nums[] = new int[9];

		for (int i = 0; i < 9; i++) {
			nums[i] = sc.nextInt();
		}
//		System.out.println(Arrays.toString(nums));
		
		int max = 0;
		int idx = 0;
		for (int i = 0; i < nums.length; i++) {
			if (max < nums[i]) {
				max = nums[i];
				idx = i+1;
			}
		}
		System.out.println(max);
		System.out.println(idx);
	}
}
