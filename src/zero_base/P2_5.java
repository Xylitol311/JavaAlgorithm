package zero_base;

import java.util.Arrays;

public class P2_4 {
    public static void main(String[] args) {
        int[] orders = {1, 2, 5, 8, 9, 10};
        int n = 3;
        System.out.println("정답은 "+ solution(orders, n));

    }
    public static int solution(int[] orders, int n) {
        int answer = 0;
        int com = 1;

        int[] result = new int[n];
        int index = 0;

        for (int i : orders) {
            System.out.println("반복 재개 " + index);
            System.out.println("i = " + i);
            if (i == com) {
                com += 1;
                continue;
            }
            while (i != com) {
                System.out.println("com = " + com);
                System.out.println("index = " + index);
                if (index == n) {
                    break;
                }
                result[index] = com;
                index += 1;
                com += 1;
            }
            com = i + 1;
            System.out.println(Arrays.toString(result));
            if (result[n - 1] != 0) {
                break;
            }
        }
        answer = result[n - 1];
        return answer;
    }
}