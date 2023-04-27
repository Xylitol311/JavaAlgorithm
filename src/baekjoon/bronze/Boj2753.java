package baekjoon.bronze;

import java.util.Scanner;

public class Boj2753 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer = 0;
        int year = sc.nextInt();
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            answer = 1;
        }

        System.out.println(answer);
    }
}
