import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numbers;
    static int[] operators; // +, -, *, / 개수
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        operators = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }
    }
    
    // 백트래킹으로 모든 연산자 조합을 탐색하며 최댓값과 최솟값을 갱신
    static void solution(int index, int currentValue) {
        // 모든 연산자를 사용한 경우 (마지막 숫자까지 계산 완료)
        if (index == N) {
            maxValue = Math.max(maxValue, currentValue);
            minValue = Math.min(minValue, currentValue);
            return;
        }
        
        // 각 연산자를 시도
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--; // 연산자 사용
                
                int nextValue = calculate(currentValue, numbers[index], i);
                solution(index + 1, nextValue);
                
                operators[i]++; // 백트래킹: 연산자 복구
            }
        }
    }
    
    // 두 수와 연산자 타입을 받아 계산 수행
    static int calculate(int a, int b, int operatorType) {
        switch (operatorType) {
            case 0: // 덧셈
                return a + b;
            case 1: // 뺄셈
                return a - b;
            case 2: // 곱셈
                return a * b;
            case 3: // 나눗셈
                // 음수를 양수로 나눌 때: 양수로 바꾼 뒤 몫을 취하고 음수로 변환
                if (a < 0) {
                    return -(-a / b);
                }
                return a / b;
            default:
                return 0;
        }
    }
    
    public static void main(String[] args) throws IOException {
        input();
        solution(1, numbers[0]); // 첫 번째 숫자부터 시작
        
        System.out.println(maxValue);
        System.out.println(minValue);
    }
}