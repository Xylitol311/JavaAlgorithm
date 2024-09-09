package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class War1 {
    static int N; // 가로
    static int M; // 세로
    static int count;
    static int whiteCnt = 0;
    static int blackCnt = 0;

    static Queue<Node> que = new LinkedList<>();
    static int dy[] = {-1, 1, 0, 0}; // 상 하 좌 우
    static int dx[] = {0, 0, -1, 1}; // 상 하 좌 우
    static boolean isVisited[][];
    static char field[][];

    static class Node {
        int x;
        int y;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    } // Node

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가로
        M = Integer.parseInt(st.nextToken()); // 새로

        // 방문 여부 체크 배열
        isVisited = new boolean[M][N];

        // 전장 입력
        field = new char[M][N];
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();

            for (int col = 0; col < N; col++) {
                char ch = temp.charAt(col);
                field[row][col] = ch;
            }
        }

        // bfs를 이용해 전장을 순환
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                // 방문한 적 없는 경우만 실행
                if (isVisited[row][col] == false) {
                    if (field[row][col] == 'W') {
                        int num = bfs(row, col, 'W');
                        whiteCnt += num * num;
                    } else {
                        int num = bfs(row, col, 'B');
                        blackCnt += num * num;
                    }

                }
            }
        }
        System.out.println(whiteCnt + " " + blackCnt);
    } // main

    static int bfs(int y, int x, char color) {
        que.offer(new Node(y, x));
        count = 1;
        isVisited[y][x] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 4; i++) {
                int now_y = now.y + dy[i];
                int now_x = now.x + dx[i];

                if (now_y >= 0 && now_y < M && now_x >= 0 && now_x < N) {
                    if (isVisited[now_y][now_x] == false && color == field[now_y][now_x]) {

                        que.offer(new Node(now_y, now_x));
                        isVisited[now_y][now_x] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    } // BFS
}
