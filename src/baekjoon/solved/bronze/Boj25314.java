package baekjoon.solved.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj25314 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String str = "";
        br.close();

        for(int i=1; i<=n/4; i++) {
            str += "long ";
        }
        System.out.println(str + "int");
    }
}
