package baekjoon.bronze;

import java.util.Scanner;

public class Boj1330 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        String answer = a > b ? ">" : a < b ? "<" : "==";

        System.out.println(answer);
    }
}
