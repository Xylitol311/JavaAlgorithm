package baekjoon.solved.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2480 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int money = 0;

        if (a == b && b == c) {
            money = 10000 + (1000 * a);
        } else if (a == b) {
            money = 1000 + (100 * a);
        } else if (c == b) {
            money = 1000 + 100 * c;
        } else if (a == c) {
            money = 1000 + 100 * a;
        } else {
            money = Math.max(a, Math.max(b, c)) * 100;
        }
        System.out.println(money);
    }
}
