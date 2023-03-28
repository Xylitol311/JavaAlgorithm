package zero_base;

import java.util.Arrays;

public class P2_5 {
    public static void main(String[] args) {
        int[] arr = {5, 2, 1, 3};
        System.out.println("정답은 "+ solution(arr));

    }
    public static int solution(int[] arr) {
        return Arrays.stream(arr).min().getAsInt();
    }
}