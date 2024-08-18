import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();

//	static int[] numArray; // 메모리 초과로 쓸 수 없음.


    public static void main(String[] args) throws IOException {
		int size = Integer.parseInt(br.readLine());
        solution(0, size);
        System.out.println(answer);
    }

    private static void solution(int nowNum, int size) {
        // 기저조건
        // 모든 자릿수를 채운 경우
        if (size == 0) {
            if (isPrime(nowNum)) answer.append(nowNum).append("\n");
            return;
        }

        // 다음 자릿수 계산
        for (int i = 0; i < 10; i++) {
            int nextNum = nowNum * 10 + i;
            // 소수가 아닌 경우 건너뜀
			if (isPrime(nextNum)) solution(nextNum, size - 1);
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;

        // num의 제곱근 이하의 수만 살펴봐도 소수인지 판별 가능
        for (int divisor = 2; divisor <= Math.sqrt(num); divisor++) {
            if (num % divisor == 0) return false;
        }

        // 위에서 걸러지지 않았으면 소수
        return true;

    }
}
