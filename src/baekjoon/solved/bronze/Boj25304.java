package baekjoon.solved.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj25304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            sum += count * price;
        }
        if (total == sum) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
