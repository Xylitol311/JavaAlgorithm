package baekjoon.bronze;

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

        minute += wait;
        if (minute > 60) {
            hour += minute / 60;
            hour %= 24;
            minute %= 60;
        }
        System.out.println(hour + " " + minute);
    }
}
