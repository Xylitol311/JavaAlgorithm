package programmers.solved;

import java.util.Arrays;

public class DoubleArray {
    public static void main(String[] args) {
        DoubleArray sol = new DoubleArray();
        int[] num = {1, 2, 3, 4, 5};
//        System.out.println("정답은 " + sol.solution(1, 2, 3, 4));
        System.out.println("정답은 " + Arrays.toString(sol.solution(num)));

    }
    public int[] solution(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] *= 2;
        }
        return numbers;
    }
}