package baekjoon.solved.bronze;

import java.io.IOException;
import java.util.Scanner;

public class Boj1212 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String octalNum = sc.next();

        long num = Long.parseLong(octalNum, 8);

        String result = Long.toBinaryString(num);

        System.out.println(result);
    }
}
