package zero_base;


public class P2_3 {
    public static void main(String[] args) {
        int[] A = {6, 12, 4};
        System.out.println(solution(A));

    }
    public static int solution(int[] A) {
        int answer = A[0];
        if (A.length == 1) {
            System.out.println(answer);
            return answer;
        }

        for (int i = 0; i < A.length - 1; i++) {
            if (answer >= A[i + 1]) {
                answer = gcd(answer, A[i + 1]);
            } else {
                answer = gcd(A[i + 1], answer);
            }
        }
        return answer;
    }

    static int gcd(int a, int b) {
        if(a % b == 0)
            return b;
        return gcd(b, a % b);
    }
}