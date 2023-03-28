package zero_base;

public class P1_3 {
    public static void main(String[] args) {
        int[] A = {100000, 99000, 98000, 97000};
        int answer = 0;
        int min = 1000000;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < min) {
                min = A[i];
            }
            if (A[i] > min & (A[i] - min) > answer) {
                answer = A[i] - min;
            }
        }

    }
}

