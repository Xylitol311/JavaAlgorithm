
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/*
<풀이 방법>
1. 배열을 탐색하면서 특정 위치에서 채취 가능한 M만큼의 열까지 부분 집합을 통해 그 위치에서 캔 꿀로 얻을 수 있는 최대 수익을 구한다.
2. HoneyBox 클래스를 이용해 좌표와 구한 수익을 List<HoneyBox>에 저장한다.
3. 이렇게 모든 배열을 탐색하고 List에 모든 위치에서의 최대 수익이 구해지면 수익을 기준으로 내림차순 정렬한다.
4. 첫번째 값을 결과에 누적하고 첫번째 값의 좌표와 겹치지 않으면서 그 다음으로 수익이 큰 값을 구해 결과에 더하여 출력한다.
 */


public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] map; // 벌통을 저장하는 2차원 배열
    static int fieldSize; // 벌통의 크기
    static int honeyBoxNum; // 채취할 수 있는 벌통의 수
    static int maxHoney; // 채취할 수 있는 꿀의 최대양
    static int maxProfit;// 각 행에서 채취할 수 있는 벌통의 수의 최대 가치
    static ArrayList<HoneyBox> list;// 최대 가치를 가지는 (행, 열)의 값과 가치를 저장

    static class HoneyBox implements Comparable<HoneyBox> {
        int row;
        int col;
        int profit;

        HoneyBox(int row, int col, int profit) {
            this.row = row;
            this.col = col;
            this.profit = profit;
        }

        @Override
        public int compareTo(HoneyBox o) {
            return this.profit - o.profit;
        }
    }

    private static void input() throws IOException {
        // 초기화
        st = new StringTokenizer(br.readLine());
        fieldSize = Integer.parseInt(st.nextToken());
        honeyBoxNum = Integer.parseInt(st.nextToken());
        maxHoney = Integer.parseInt(st.nextToken());
        map = new int[fieldSize][fieldSize];
        list = new ArrayList<HoneyBox>();

        // 입력
        for (int i = 0; i < fieldSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < fieldSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
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

    private static int solution() {
        for (int row = 0; row < fieldSize; row++) {
            for (int col = 0; col <= fieldSize - honeyBoxNum; col++) {
                // max값 초기화
                maxProfit = 0;

                // 부분 집합
                powerSet(row, col, 0, 0, 0, 0);

                // 현재 위치와 max값을 ArrayList에 저장
                list.add(new HoneyBox(row, col, maxProfit));
            }
        }

        // ArrayList를 max기준으로 정렬한 후 뒤집어서 내림차순으로 만듦
        Collections.sort(list);
        Collections.reverse(list);

        int answerSum = 0; // 최종 결과값
        int idx = 0; // list에서 두 번째로 높은 가치를 찾기위한 인덱스
        HoneyBox first = list.get(idx++);// 가장 가치가 높은 것
        answerSum += first.profit;

        while (true) {
            HoneyBox second = list.get(idx++);// 두 번째로 가치가 높은 것

            // 만약 행이 다르다면 first와 겹칠 일이 없기 때문에 sum에 max값을 더하고 break
            if (first.row != second.row) {
                answerSum += second.profit;
                break;
            }

            // 행은 같지만 두 개의 열의 차이가 최대 벌통의 개수 이상이면 겹치지 않기 때문에 sum에 max값을 더하고 break
            else if (first.row == second.row && Math.abs(first.col - second.col) >= honeyBoxNum) {
                answerSum += second.profit;
                break;
            }
        }

        return answerSum;
    }

    public static void powerSet(int startRow, int startCol, int elementIdx, int isSelected, int sum, int powSum) {
        // 기저 조건
        // sum이 이미 maxHoney를 넘어간 경우
        if (sum > maxHoney)
            return;

        // 마지막 요소까지 탐색한 경우
        if (elementIdx == honeyBoxNum) {
            // 합계가 maxCollecion 이하이면서 부분 집합 중 제일 큰 경우 갱신
            maxProfit = Math.max(maxProfit, powSum);
            return;
        }

        // 현재 요소의 제곱한 값
        int tmpPow = squared(map[startRow][startCol + elementIdx]);

        // 현재 요소 선택 : 선택 처리 및 sum에 temp 더하기
        powerSet(startRow, startCol, elementIdx + 1, isSelected | 1 << elementIdx, sum + map[startRow][startCol + elementIdx], powSum + tmpPow);
        // 현재 요소 선택하지 않음
        powerSet(startRow, startCol, elementIdx + 1, isSelected, sum, powSum);
    }

    // 제곱을 구하는 method
    private static int squared(int n) {
        return (int) Math.pow(n, 2);
    }
}