package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Boj1541 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int answer = Integer.MAX_VALUE;

        String problem = sc.next();
        String[] substraction = problem.split("-");
        for (int i = 0; i < substraction.length; i++) {
            int temp = 0;

            String[] addition = substraction[i].split("\\+");
            for (int j = 0; j < addition.length; j++) {
                temp += Integer.parseInt(addition[j]);
            }

            if (answer == Integer.MAX_VALUE) {
                answer = temp;
            } else {
                answer -= temp;
            }
        }

        System.out.println(answer);
    }

    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.MAX_VALUE;

        StringTokenizer substraction = new StringTokenizer(br.readLine(), "-");
        while(substraction.hasMoreTokens()) {
            int temp = 0;
            StringTokenizer addition = new StringTokenizer(substraction.nextToken(), "\\+");
            while(addition.hasMoreTokens()) {
                temp += Integer.parseInt(addition.nextToken());
            }

            if (answer == Integer.MAX_VALUE) {
                answer = temp;
            } else {
                answer -= temp;
            }
        }
        System.out.println(answer);
    }
}
