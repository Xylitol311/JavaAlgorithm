

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스 개수와 배열 정보를 입력 받는다.
 * 2. 배열 정보를 입력받을 때 값이 1인 경우 프로세서 배열에 해당 row와 col값을 가진 프로세서 객체를 생성하여 저장한다.
 * 2-1. 또한 같은 크기의 boolean 배열을 생성해 프로세서 위치를 true로 저장한다.
 * 3. 입력 완료 후 재귀를 통해 프로세서의 방향을 정하거나 아예 선택하지 않은 경우로 나누어 반복한다.
 * 3-0. 갱신 조건 : 선택된 프로세서 개수가 이전 max개수 이상이고, 연결된 전선 길이가 이전 연결보다 작은 경우
 * 3-1. 마지막 프로세서까지 탐색한 경우 => 갱신조건에 부합하면 최대 프로세서 개수와 최소 전선 길이 갱신
 * 3-2. 4방향을 for문을 통해 반복
 * 3-2-1. 현 위치에서 현 방향으로 탐색하다가 배열에 끝에 도달하지 못하고 다른 프로세서나 연결된 전선을 만난 경우: 다음 방향 탐색
 * 3-2-2. 배열에 끝에 도달한 경우. 현 프로세서에서 현 방향으로 전선 연결 처리 해주기
 * 3-2-3. 연결 처리 후 다음 프로세서 재귀
 * 3-2-4. 재귀가 끝나면 연결된 전선을 원복 처리
 * 3-3. 아무것도 연결하지 않은 상태로 다음 프로세서 재귀
 * 4. 모든 케이스 탐색 후 최소 연결 길이 출력
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();


    static class Processor {
        int row, col;
        boolean connectedPower;

        public Processor(int row, int col) {
            this.row = row;
            this.col = col;
            this.connectedPower = false;
        }
    }

    static int maxProcessor;
    static int minLines;
    static int boardSize;
    static int[][] board;
    static boolean[][] connected;
    static List<Processor> processors;

    static void input() throws IOException {
        boardSize = Integer.parseInt(br.readLine().trim());
        maxProcessor = Integer.MIN_VALUE;
        minLines = Integer.MAX_VALUE;

        board = new int[boardSize][boardSize];
        connected = new boolean[boardSize][boardSize];
        processors = new ArrayList<>();

        // 배열 입력
        for (int row = 0; row < boardSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
                if (board[row][col] == 1) {
                    // 프로세서가 존재하므로 true 처리
                    connected[row][col] = true;
                    // 가장 자리인 경우 이후 재귀에서 탐색할 필요가 없으므로 패스
                    if (inEdge(row, col)) continue;
                    processors.add(new Processor(row, col));
                }
            }
        }
    }

    private static boolean inEdge(int row, int col) {
        if (row == 0 || row == boardSize - 1 || col == 0 || col == boardSize - 1)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            selectPowerLine(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(minLines).append("\n");
        }
        System.out.println(sb);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    private static final boolean CONNECT = true;
    private static final boolean NOT_CONNECT = false;

    private static void selectPowerLine(int nowIdx, int selectedCnt, int connectedLineLength) {
        // 0. 남은 프로세서의 개수를 전부 더해도 maxProcessor 개수를 넘지 못하는 경우 리턴
        if ((processors.size() - nowIdx) + selectedCnt < maxProcessor) {
            return;
        }
        // 1. 마지막 프로세서까지 탐색한 경우
        if (nowIdx == processors.size()) {
            // 코어의 개수가 기존 이상이면서 연결된 라인이 적은 경우
            if (isMax(selectedCnt, connectedLineLength)) {
                maxProcessor = selectedCnt;
                minLines = connectedLineLength;
            }
            // 연결된 라인 수와 관계없이 더 많은 코어를 연결한 경우
            else if (selectedCnt > maxProcessor) {
                maxProcessor = selectedCnt;
                minLines = connectedLineLength;
            }
            return;
        }

        Processor nowProcessor = processors.get(nowIdx);

        for (int direction = 0; direction < 4; direction++) {
            // 해당 방향으로 전원 연결이 가능한 지 확인
            if (isPossibleConnect(nowProcessor, direction)) {
                // 연결 처리
                int newConnectedCnt = connectLine(nowProcessor, direction, CONNECT);
                // 다음 프로세서 반복
                selectPowerLine(nowIdx + 1, selectedCnt + 1, connectedLineLength + newConnectedCnt);

                // 연결 원복
                connectLine(nowProcessor, direction, NOT_CONNECT);
            }
        }

        // 연결하지 않고 다음 프로세서 탐색
        selectPowerLine(nowIdx + 1, selectedCnt, connectedLineLength);
    }

    private static int connectLine(Processor nowProcessor, int direction, boolean isConnect) {
        int cnt = 0;

        int nr = nowProcessor.row;
        int nc = nowProcessor.col;

        while (true) {
            nr += dy[direction];
            nc += dx[direction];

            // 범위 내인지
            if (!inArea(nr, nc)) break;
            cnt++;
            connected[nr][nc] = isConnect;
        }

        return cnt;
    }

    private static boolean isPossibleConnect(Processor processor, int direction) {
        int nr = processor.row;
        int nc = processor.col;

        while (true) {
            nr += dy[direction];
            nc += dx[direction];

            // 범위 내인지
            if (!inArea(nr, nc)) break;

            // 가장자리까지 가는 길에 프로세서나 연결처리된 위치가 있는 경우 연결 불가
            if (connected[nr][nc]) return false;
        }

        return true;
    }

    private static boolean inArea(int nr, int nc) {
        if (nr >= 0 && nr < boardSize && nc >= 0 && nc < boardSize) {
            return true;
        }
        return false;
    }

    private static boolean isMax(int selectedCnt, int connectedLineLength) {
        if (selectedCnt >= maxProcessor && connectedLineLength < minLines)
            return true;
        return false;
    }


}
