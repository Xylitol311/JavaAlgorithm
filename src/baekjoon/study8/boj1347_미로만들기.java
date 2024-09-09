package baekjoon.study8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
주어지는 명령어의 길이가 0 이상 50 미만이므로 100*100크기의 배열의 정중앙에서부터 명령어를 시작해
최소 행,열과 최대 행,열을 구해 출력한다.
 */
public class boj1347_미로만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static char[][] map;
    static String order;
    static int nx, ny, minY, minX, maxY, maxX;

    private static void input() throws IOException {
        // 첫 줄 스킵
        br.readLine();

        order = br.readLine();

        map = new char[101][101];
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++)
                map[i][j] = '#';
        }

        nx = ny = minY = minX = maxX = maxY = 50;
    }

    public static void main(String[] args) throws IOException {
        input();

        int direction = 2; //남쪽으로 설정
        map[ny][nx] = '.'; // 시작 위치 .으로 변경
        for (int i = 0; i < order.length(); i++) {
            if (order.charAt(i) == 'F') {
                nx += dx[direction];
                ny += dy[direction];
                map[ny][nx] = '.';
                maxX = Math.max(maxX, nx);
                maxY = Math.max(maxY, ny);
                minX = Math.min(minX, nx);
                minY = Math.min(minY, ny);
            } else if (order.charAt(i) == 'L') {
                direction = (--direction + 4) % 4;
            } else {
                direction = ++direction % 4;
            }
        }

        // 최소, 최대 좌표 이용해서 출력
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
