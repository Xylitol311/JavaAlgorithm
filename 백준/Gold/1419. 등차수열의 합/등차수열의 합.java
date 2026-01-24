import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long l = Long.parseLong(br.readLine());
        long r = Long.parseLong(br.readLine());
        int k = Integer.parseInt(br.readLine());
        
        System.out.println(solution(l, r, k));
    }
    
    // k값에 따라 [l, r] 범위의 표현 가능한 수 개수 계산
    static long solution(long l, long r, int k) {
        if (k == 2) {
            return countRange(l, r, 3);
        } else if (k == 3) {
            return countMultiples(l, r, 3, 6);
        } else if (k == 4) {
            long count = countMultiples(l, r, 2, 10);
            if (l <= 12 && 12 <= r) count--;
            return count;
        } else {
            return countMultiples(l, r, 5, 15);
        }
    }
    
    // [l, r]에서 minVal 이상인 정수 개수
    static long countRange(long l, long r, long minVal) {
        long start = Math.max(minVal, l);
        return start > r ? 0 : r - start + 1;
    }
    
    // [l, r]에서 minVal 이상인 m의 배수 개수
    static long countMultiples(long l, long r, int m, int minVal) {
        long start = Math.max(minVal, l);
        if (start > r) return 0;
        
        long firstMultiple = ((start + m - 1) / m) * m;
        if (firstMultiple > r) return 0;
        
        long lastMultiple = (r / m) * m;
        return (lastMultiple - firstMultiple) / m + 1;
    }
}