package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1010 {

    static int[][] dp = new int[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(combie(M, N));
        }

    }

    private static int combie(int n, int r) {
        if (dp[n][r] > 0) {
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = combie(n - 1, r - 1) + combie(n - 1, r);
    }
}
