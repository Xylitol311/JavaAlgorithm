package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj25556 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int[] numArray = new int[n];
        for (int i = 0; i < n; i++) {
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        int[] stack = new int[4];
        String result = "YES";

        loopOut:
        for (int num : numArray) {
            for (int i = 0; i < stack.length; i++) {
                if (num > stack[i]) {
                    stack[i] = num;
                    break;
                }
                if (i == 3) {
                    result = "NO";
                    break loopOut;
                }
            }
        }

        System.out.println(result);

    }
}
