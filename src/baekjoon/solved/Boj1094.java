package baekjoon.solved;

import java.io.IOException;
import java.util.Scanner;

public class Boj1094 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int[] nums = new int[]{64, 32, 16, 8, 4, 2, 1};
        int count = 0;

        for (int i : nums) {
            if (X == i) {
                System.out.println(1);
                return;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (X == nums[i]) {
                System.out.println(++count);
                return;
            }
            if (X > nums[i]) {
                count++;
                X -= nums[i];
            }
        }
    }
}
