import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long A, B;
    
    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
    }
    
    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }
    
    // 에라토스테네스의 체를 이용하여 limit까지의 소수들을 구함
    static List<Long> sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        List<Long> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add((long) i);
            }
        }
        return primes;
    }
    
    // 거의 소수의 개수를 계산
    static int solution() {
        int limit = (int) Math.sqrt(B) + 1;
        List<Long> primes = sieve(limit);
        Set<Long> almostPrimes = new HashSet<>();
        
        for (long prime : primes) {
            long power = prime * prime; // 2제곱부터 시작
            
            // 소수의 거듭제곱이 B를 넘지 않는 동안 반복
            while (power <= B) {
                if (power >= A) {
                    almostPrimes.add(power);
                }
                
                // 오버플로우 체크 후 다음 거듭제곱 계산
                if (power > B / prime) break;
                power *= prime;
            }
        }
        
        return almostPrimes.size();
    }
}