package baekjoon.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10871 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp < k) {
                sb.append( temp + " ");
            }
        }

        System.out.println(sb);
    }
}
