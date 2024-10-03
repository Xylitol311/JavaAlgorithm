// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
각 방 [x,y]의 스위치를 저장할 ArrayList배열을 만들어 스위치들을 저장.
불이 켜진 곳과 방문 여부를 체크할 light, visited 2차원 boolean배열을 생성.
스위치를 이용해 불을 켤 때마다 light를 true로 변경하고 visited를 초기화 한다.
(불이 켜짐과 동시에 새로 갈 수 있는 방이 생기기 때문)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    

    static int N;
    static ArrayList<Room> switchList[][];
    static boolean light[][];
    static boolean visitied[][];
    static int result = 1;
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 방의 크기
        int M = Integer.parseInt(st.nextToken()); // 스위치 개수

        // 로직 구현 시 편의를 위해 N+1사이즈로 선언
        switchList = new ArrayList[N + 1][N + 1];
        light = new boolean[N + 1][N + 1];
        visitied = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                switchList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switchList[x][y].add(new Room(a, b));
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    static void bfs() {
        Queue<Room> queue = new ArrayDeque<>();
        queue.offer(new Room(1, 1));
        light[1][1] = true;
        visitied[1][1] = true;

        while(!queue.isEmpty()) {
            Room nowRoom = queue.poll();

            // 불을 켤 수 있는 방이 있다면 전부 켜기.
            if (!switchList[nowRoom.x][nowRoom.y].isEmpty()) {
                // 방문 여부를 초기화해서 다시 방문 처리
                visitied = new boolean[N + 1][N + 1];
                visitied[nowRoom.x][nowRoom.y] = true;
                for (Room room : switchList[nowRoom.x][nowRoom.y]) {
                    // 불이 켜져있지 않은 방만 켜기
                    if (!light[room.x][room.y]) {
                        light[room.x][room.y] = true;
                        result++;
                    }
                }
                // 모든 스위치를 킨 경우 스위치 제거
                switchList[nowRoom.x][nowRoom.y].clear();
            }

            // 탐색
            for (int i = 0; i < 4; i++) {
                int newX = nowRoom.x + dx[i];
                int newY = nowRoom.y + dy[i];

                // 범위 내인지 체크
                if (newX <= 0 || newY <= 0 || newX > N || newY > N) continue;

                // 불이 켜져있고 방문하지 않았다면 방문한다.
                if (light[newX][newY] == true && !visitied[newX][newY]) {
                    queue.offer(new Room(newX, newY));
                    visitied[newX][newY] = true;
                }
            }
        }
    }
    static class Room {
        int x;
        int y;

        Room (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}