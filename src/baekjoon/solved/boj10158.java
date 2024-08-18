package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10158 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int width, height, p, q, time;

    // 입력
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        time = Integer.parseInt(br.readLine());
    }

    // 현재 위치 구하기
    static void move() {
        // x의 주기는 2 * 너비, y는 2 * 높이
        int currentX = (p + time) % (2 * width);
        int currentY = (q + time) % (2 * height);

        // 구해진 현재 위치를 박스 안에 맞게 보정
        if (currentX > width) currentX = 2 * width - currentX;
        if (currentY > height) currentY = 2 * height - currentY;

        sb.append(currentX).append(" ").append(currentY);
        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException{
        input();
        move();
    }
}
