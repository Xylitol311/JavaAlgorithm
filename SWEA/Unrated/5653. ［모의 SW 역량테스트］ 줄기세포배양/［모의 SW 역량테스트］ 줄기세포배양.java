import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
0. 세포 클래스 생성 (좌표, 생명력, 활성 시간, 번식 시간, 죽을 시간)
1. 세포가 존재하는 지 체크할 불린 배열, 비활성 상태 큐, 번식 대기 큐, 활성 상태 큐를 생성
    - 비활성 상태 큐 : 활성화될 시간이 빠른 순으로 정렬
    - 번식 대기 큐 :
        - 저장 시 번식 시간과 죽을 시간을 계산해서 입력
        - 번식 시간 -> 생명력 순으로 우선 정렬
        - 번식 시간이 되면 visit 배열을 확인해서 번식이 가능한 경우 해당 좌표에 세포를 생성하여 비활성 상태 큐에 저장
        - 번식 후 죽을 시간이 남은 세포는 활성 상태 큐에 저장
        - 번식 후 죽을 시간이 된 세포는 제거.
    - 활성 상태 큐 :
        - 죽을 시간 순으로 우선 정렬
        - 시간이 되면 큐에서 제거
2. 데이터 입력.(세포 위치를 배열의 중간 값으로 조정) 입력된 세포 불린 배열에 체크 표시
3. 초기에 입력된 세포를 비활성 상태 큐에 저장
    3-1. 저장 시 현재 시간 + 생명력을 구해서 활성화될 시간을 세포에 저장
    3-2. 활성화될 시간이 짧은 순으로 우선 정렬
4. 입력된 시간까지 반복
    4-1. 활성 상태 큐에서 죽을 시간이 된 세포를 죽음 처리
    4-2. 비활성 상태 큐 -> 번식 대기 큐 (활성 시간이 된 세포들)
    4-3. 번식 대기 큐에서 시간이 된 세포를 번식 처리 후 활성화 혹은 사망 처리
5. 모든 시간만큼 반복이 끝나면 비활성, 번식 대기, 활성화 큐에 있는 데이터의 수의 합계를 출력

 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Cell {
        int y, x, life, activeTime, breedingTime, deadTime;

        public Cell(int y, int x, int life, int nowTime) {
            super();
            this.y = y;
            this.x = x;
            this.life = life;
            // 세포가 만들어진 후 life 시간 이후 활성화
            this.activeTime = nowTime + life;
            // 활성화 1시간 뒤에 번식
            this.breedingTime = nowTime + life + 1;
            // 세포 생성 이후 life*2 시간 이후 세포 사망
            this.deadTime = nowTime + life * 2;
        }
    }

    static boolean[][] visited;
    static int height, width, maxTime;
    static PriorityQueue<Cell> deactiveQueue;
    static PriorityQueue<Cell> breedingQueue;
    static PriorityQueue<Cell> activeQueue;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        maxTime = Integer.parseInt(st.nextToken());

        visited = new boolean[height + maxTime][width + maxTime];

        deactiveQueue = new PriorityQueue<>(new Comparator<Cell>() {

            @Override
            public int compare(Cell o1, Cell o2) {

                return o1.activeTime - o2.activeTime;
            }

        });

        breedingQueue = new PriorityQueue<>(new Comparator<Cell>() {

            @Override
            // 번식 시간이 빠른 순으로 정렬
            // 만약 시간이 같다면 생명력이 더 큰 순으로 정렬
            public int compare(Cell o1, Cell o2) {
                if (o1.breedingTime < o2.breedingTime)
                    return -1;
                else if (o1.breedingTime > o2.breedingTime)
                    return 1;
                else {
                    if (o1.life >= o2.life) {
                        return -1;
                    } else return 1;
                }
            }

        });

        activeQueue = new PriorityQueue<>(new Comparator<Cell>() {

            @Override
            public int compare(Cell o1, Cell o2) {

                return o1.deadTime - o2.deadTime;
            }
        });

        for (int row = maxTime / 2; row < maxTime / 2 + height; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = maxTime / 2; col < maxTime / 2 + width; col++) {
                int life = Integer.parseInt(st.nextToken());
                // 0이면 세포가 존재하지 않으므로 pass
                if (life == 0) continue;
                visited[row][col] = true;
                deactiveQueue.add(new Cell(row, col, life, 0));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            sb.append("#").append(tc).append(" ").append(solution()).append("\n");
        }
        System.out.println(sb);
    }

    static int solution() {
        for (int time = 0; time <= maxTime; time++) {
            // 죽을 시간이 된 셀 제거
            killCell(time);
            // 세포 활성화
            activateCell(time);
            // 세포 번식
            breedCell(time);
        }

        // 주어진 시간이 지난 후 비활성, 활성, 번식 큐(활성화 상태)에 있는 세포 개수를 더해서 반환
        return deactiveQueue.size() + activeQueue.size() + breedingQueue.size();
    }

    // 생명이 다한 활성세포 제거
    static void killCell(int nowTime) {
        // 큐가 비었다면 pass
        while (!activeQueue.isEmpty()) {
            // 추출할 세포의 죽을 시간과 현재 시간이 다르다면 반복 종료
            if (activeQueue.peek().deadTime != nowTime) break;
            activeQueue.poll();
        }
    }

    // 세포 활성화(활성화와 동시에 번식 큐로 이동)
    static void activateCell(int nowTime) {
        // 큐가 비었다면 pass
        while (!deactiveQueue.isEmpty()) {
            // 추출할 세포의 활성 시간과 현재 시간이 다르다면 반복 종료
            if (deactiveQueue.peek().activeTime != nowTime) break;
            breedingQueue.add(deactiveQueue.poll());
        }
    }

    // 세포 번식(번식 후 제거 혹은 activeQueue에 저장)
    static void breedCell(int nowTime) {
        // 큐가 비었다면 pass
        while (!breedingQueue.isEmpty()) {
            // 추출할 세포의 번식 시간과 현재 시간이 다르다면 반복 종료
            if (breedingQueue.peek().breedingTime != nowTime) break;

            // 번식할 세포 큐에서 추출
            Cell nowCell = breedingQueue.poll();

            // bfs를 이용해 번식
            breeding(nowCell, nowTime);

            // 생명이 남았다면 activeQueue에 저장
            if (nowCell.deadTime != nowTime) {
                activeQueue.add(nowCell);
            }
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void breeding(Cell cell, int nowTime) {

        for (int direction = 0; direction < 4; direction++) {
            int ny = cell.y + dy[direction];
            int nx = cell.x + dx[direction];

            // 배열 밖으로 벗어나지 않는다는 가정 하에 배열 범위 체크 안함
            // 이미 세포가 있는 곳인지 체크
            if (visited[ny][nx]) continue;

            // 아직 세포가 없는 곳이라면 세포 번식
            visited[ny][nx] = true;
            deactiveQueue.add(new Cell(ny, nx, cell.life, nowTime));
        }
    }


}