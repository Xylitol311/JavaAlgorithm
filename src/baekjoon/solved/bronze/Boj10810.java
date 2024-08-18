package baekjoon.solved.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10810 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] baguni = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            for (int j = start - 1; j < end; j++) {
                baguni[j] = num;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : baguni) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }
}
