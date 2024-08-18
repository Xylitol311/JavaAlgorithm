package baekjoon.solved.bronze;

import java.util.Scanner;

public class Boj14681 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer = 0;
        int x = sc.nextInt();
        int y = sc.nextInt();

        if (x > 0 && y > 0) {
            answer = 1;
        } else if (x < 0 && y > 0) {
            answer = 2;
        } else if (x < 0 && y < 0) {
            answer = 3;
        } else {
            answer = 4;
        }
        System.out.println(answer);
    }
}
