package baekjoon.study8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2075_N번째큰수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, arr[];
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(arr);
        System.out.println(arr[N * N - N]);
    }
}
