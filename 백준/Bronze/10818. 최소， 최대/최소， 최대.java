import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

			int nums[] = new int[T];

			for (int i = 0; i < T; i++) {
				nums[i] = sc.nextInt();
			}

			int max = -1000000;
			int min = 1000000;
			for (int i = 0; i < nums.length; i++) {
				if (max <= nums[i]) {
					max = nums[i];
				}
				if (min>=nums[i]) {
					min = nums[i];
				}
			}
			System.out.print(min);
			System.out.print(" ");
			System.out.print(max);
		}
	}
