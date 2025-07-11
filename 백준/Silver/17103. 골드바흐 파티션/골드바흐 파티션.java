import java.io.*;
import java.util.*;

public class Main {
    
    // 최대 입력값
    private static final int MAX_N = 1000000;
    // 에라토스테네스의 체로 소수 판별 배열
    private static boolean[] isPrime = new boolean[MAX_N + 1];
    
    /**
     * 입력을 받는 함수
     * @return 입력받은 N값들의 배열
     */
    public static int[] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        int[] testCases = new int[T];
        
        for (int i = 0; i < T; i++) {
            testCases[i] = Integer.parseInt(br.readLine());
        }
        
        br.close();
        return testCases;
    }
    
    /**
     * 에라토스테네스의 체로 소수를 미리 구하는 함수
     */
    private static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int i = 2; i * i <= MAX_N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX_N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    
    /**
     * 골드바흐 파티션의 개수를 구하는 함수
     * @param N 골드바흐 파티션을 구할 짝수
     * @return 골드바흐 파티션의 개수
     */
    public static int solution(int N) {
        int count = 0;
        
        // N/2까지만 확인 (중복 제거)
        // 예: N=10일 때, (3,7)과 (7,3)은 같은 파티션
        for (int i = 2; i <= N / 2; i++) {
            if (isPrime[i] && isPrime[N - i]) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * 메인 함수
     * input 함수로 입력을 받고, solution 함수로 각 테스트 케이스를 처리
     */
    public static void main(String[] args) throws IOException {
        // 소수 배열 초기화
        sieve();
        
        // 입력 받기
        int[] testCases = input();
        
        // 각 테스트 케이스에 대해 solution 함수 호출 및 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int N : testCases) {
            int result = solution(N);
            sb.append(result).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}