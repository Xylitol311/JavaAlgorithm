package baekjoon.solved.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int wait = Integer.parseInt(br.readLine());

        int min = hour * 60 + minute;
        min += wait;
        hour = (min / 60) % 24;
        minute = min % 60;
        System.out.println(hour + " " + minute);
    }
}
