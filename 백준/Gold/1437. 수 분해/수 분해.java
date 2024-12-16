// 메모리: 00000 KB / 시간: 00 ms

import java.util.Scanner;/*
N을 분해하고 나온 수를 전부 곱했을 때 가장 큰 수를 구하는 문제
[첫 풀이] - 오답
1. 4 이하일 때는 자기 자신이 제일 큰 곱
2. (N - (N/2))를 재귀를 통해 더 큰 수가 나오지 않을 때까지 반복한다.
-> 큰 수로 갔을 때 결과값이 올바르지 않음.
*/

/*
[공식 참고]
1. 4 이하일 때는 자기 자신.
2. 이렇게저렇게 미분을 해보면 3을 기준으로 나머지 값에 따라 경우를 다르게 설정할 수 있다.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = 10007;

        if (n < 5) {
            System.out.println(n);
        } else if (n % 3 == 0) {
            System.out.println(modularExponentiation(3, n / 3, m));
        } else if (n % 3 == 1) {
            System.out.println((modularExponentiation(3, (n - 4) / 3, m) * 4) % m);
        } else {
            System.out.println((modularExponentiation(3, (n - 2) / 3, m) * 2) % m);
        }
    }

    private static int modularExponentiation(int base, int exponent, int mod) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result = (result * base) % mod;
        }
        return result;
    }
}