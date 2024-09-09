package baekjoon.study17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
<풀이 방법>
조합을 이용해 M개의 치킨집을 고르고 각 집에서 치킨집까지 거리가 최소가 되는 경우를 구한다.
1. 집, 치킨집이 있는 배열 정보를 입력 받는다.
2. 사람과 치킨집의 좌표를 각각 리스트로 따로 저장한다.
3. 이후 dfs를 통해 치킨집 중에서 M개를 뽑아 각 집에서 치킨집까지 최단 거리가 되는 경우를 구한다.
4. 모든 치킨집의 조합을 탐색한 후 최단 거리를 출력한다.
 */
public class boj15686_치킨배달 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int fieldSize, chickenNum, map[][];
    static List<Node> chickens, houses;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        fieldSize = Integer.parseInt(st.nextToken());
        chickenNum = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;

        map = new int[fieldSize][fieldSize];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        for (int row = 0; row < fieldSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < fieldSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 1) houses.add(new Node(row, col));
                if (map[row][col] == 2) chickens.add(new Node(row, col));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0, 0);
        System.out.println(answer);
    }

    static int answer;
    private static void dfs(int elementIdx, int selectedCnt, int isSelected) {
        // chikenNum만큼 치킨집을 고른 경우
        if (selectedCnt == chickenNum) {
            int totalDistance = 0; // 도시의 모든 집으로부터 치킨집까지 거리의 최솟값
            for(int homeIdx = 0; homeIdx < houses.size(); homeIdx++) {
                // 현재 집에서 가장 가까운 치킨집까지의 거리 변수
                int nowHouseMinDis = Integer.MAX_VALUE;
                for(int chickenIdx = 0; chickenIdx < chickens.size(); chickenIdx++) {
                    if((isSelected & 1 << chickenIdx) != 0) {
                        int dist = calDistance(houses.get(homeIdx), chickens.get(chickenIdx));
                        nowHouseMinDis = Math.min(nowHouseMinDis, dist);
                    }
                }
                totalDistance += nowHouseMinDis;
            }
            answer = Math.min(totalDistance, answer);
            return;
        }

        // 모든 요소를 탐색한 경우
        if (elementIdx == chickens.size()) return;

        // 현재 요소 선택
        dfs(elementIdx + 1, selectedCnt + 1, isSelected | 1 << elementIdx);
        // 현재 요소 미선택
        dfs(elementIdx + 1, selectedCnt, isSelected);
    }

    private static int calDistance(Node house, Node chicken) {
        return Math.abs(house.row - chicken.row)
                + Math.abs(house.col - chicken.col);
    }

}
