package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5613 { // 계산기 프로그램

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.parseInt(br.readLine());
        int num = 0;
        String operator = "";

        while (true) {
            operator = br.readLine();
            if (operator.equals("=")) {
                break;
            }
            num = Integer.parseInt(br.readLine());
            answer = calculate(answer, num, operator);
        }
        System.out.println(answer);
    }

    static int calculate(int num1, int num2, String operator) {
        int result = 0;
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return result;
        }
    }
}
