import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		// 최대공약수 최소공배수 문제
		Scanner sc = new Scanner(System.in);

		int n1 = sc.nextInt();
		int n2 = sc.nextInt();

		int arr_n1[] = new int[n1];
		int arr_n2[] = new int[n2];

		// 1부터 넣어보면서 나눠지는지를 본다
		int n1나눠진답 = 0;
		for (int i = 0; i < n1; i++) {
			if (n1 % (i + 1) == 0) {
				n1나눠진답 = n1 / (i + 1);
				arr_n1[i] = n1나눠진답;
			}
		}
		// 배열의 중복을 없앤다
		arr_n1 = Arrays.stream(arr_n1).distinct().toArray();

		int n2나눠진답 = 0;
		for (int i = 0; i < n2; i++) {
			if (n2 % (i + 1) == 0) {
				n2나눠진답 = n2 / (i + 1);
				arr_n2[i] = n2나눠진답;
			}
		}
		// 배열의 중복을 없앤다
		arr_n2 = Arrays.stream(arr_n2).distinct().toArray();

//		System.out.println(Arrays.toString(arr_n1));
//		System.out.println(Arrays.toString(arr_n2));

		// 공약수들 중에서 가장 큰게 최대공약수
		int 최대공약수 = 0;
		for (int i = 0; i < arr_n1.length; i++) {
			for (int j = 0; j < arr_n2.length; j++) {
				if (arr_n1[i] == arr_n2[j]) {
					if (최대공약수 < arr_n1[i]) {
						최대공약수 = arr_n1[i];
					}
				}
			}
		}
		System.out.println(최대공약수);

		// 최소공배수
		int minn1 = n1 / 최대공약수;
		int minn2 = n2 / 최대공약수;
		int 최소공배수 = minn1 * minn2 * 최대공약수;
		System.out.println(최소공배수);

	}

}
