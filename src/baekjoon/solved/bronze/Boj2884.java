package baekjoon.solved.bronze;

import java.util.Scanner;

public class Boj2884 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        if (b < 45) {
            b = 60 + b - 45;
            if (a == 0) {
                a = 23;
            } else {
                a -= 1;
            }
        } else {
            b -= 45;
        }

        System.out.println(a + " " + b);
    }
}
