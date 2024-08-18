package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10158_진행중 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    // 방향: 우상향, 좌상향, 좌하향, 우하향,
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {-1, -1, 1, 1};

    static final int RIGHT_UP = 0;
    static final int RIGHT = 0;
    static final int LEFT = 1;
    static final int UP = 0;
    static final int DOWN = 2;

    static int width;
    static int height;
    static int time;

    static int xDirection;
    static int yDirection;


    public static void move(int p, int q) {
        // 초기 위치 및 방향 설정
        int nowX = p;
        int nowY = q;

        xDirection = RIGHT;
        yDirection = LEFT;

        // 정해진 시간동안 반복
        for (int i = 1; i <= time; i++) {
            // 이동
            nowX += dx[xDirection];
            nowY += dx[yDirection];

            // 벽 혹은 모서리인지 판단 후 방향 변경 혹은 유지

        }

    }

//    public static int turn(int nowX, int nowY) {
//        // 모서리인지? x,y 방향 둘 다 변경
//        if ((nowX == width && nowY == height) || (nowX == width && nowY == 0) || (nowX == 0 && nowY == 0) || (nowX == 0 && nowY == height)) {
//            xDirection = (xDirection + 1) & 2;
//        }
//
//        // 벽일 경우? 위 아래면 y 변경, 좌우면 x 변경
//		if (nowX == width)
//
//        return 0;
//    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        time = Integer.parseInt(br.readLine());


    }
}
