import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		//소수 찾기 문제
		Scanner sc = new Scanner(System.in);
		int n_times =sc.nextInt();
		int prime_num_count=0;
		for (int i=0; i<n_times; i++) {
			int num = sc.nextInt();
			int arr [] = new int [num];
			
			int count =0;
			for(int j=0; j<num; j++) {
				if (num%(j+1)==0) {
					arr[j] =num/(j+1);
				}
				if (arr[j] != 0) {
					count++;
				}
			}
			
			if (count ==2) {
//				System.out.println(num+"은 솟수입니다.");
				prime_num_count++;
			}
		}
		System.out.println(prime_num_count);
	}

}