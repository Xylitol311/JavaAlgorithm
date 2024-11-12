// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int field[][], r, c, k, time, maxRow, maxCol;

    private static void input() throws IOException {
        field = new int[100][100];
        // r,c,k 입력
        st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        // 배열 입력
        maxRow = 3;
        maxCol = 3;
        for (int i = 0; i < maxRow; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < maxCol; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    private static int solution() {
        for (int time = 0; time <= 100; time++) {
            // r,c가 k라면?
            if (field[r][c] == k) return time;

            // 행, 열 개수 비교 후 정렬 실행
            if (maxRow >= maxCol) {
                sortByRow();
            } else {
                sortByCol();
            }
        }
        if (field[r][c] == k) return 101;
        return -1;
    }

    // 정렬(행, 열 기준)
    // 1. 1부터 0이 아닌 값이 나올 때까지 탐색하면서 숫자의 개수를 Map에 저장.(Map은 한줄마다 초기화)
    // 2. 한 줄에 대한 탐색이 끝나면 맵에 저장된 값을 숫자의 개수, 같은 경우 숫자의 크기대로 오름차순 정렬
    // 3. 키값, 밸류값 순으로 모든 Map의 값을 해당 줄에 저장.
    // 3-1. 이때 맵의 요소 개수 * 2가 maxRow(maxCol)보다 크면 갱신
    private static void sortByRow() {
        for (int row = 0; row < maxRow; row++) {
            Map<Integer, Integer> map = new HashMap<>();
            // 열 탐색
            for (int col = 0; col < maxCol; col++) {
                // 0인 경우 통과
                if (field[row][col] == 0) continue;
                // 아직 입력되지 않은 숫자인 경우 map에 추가
                if (!map.containsKey(field[row][col])) {
                    map.put(field[row][col], 1);
                }
                // 입력된 숫자라면 +1
                else {
                    map.put(field[row][col], map.get(field[row][col]) + 1);
                }
            }
            // 정렬 후 저장
            // Map을 List로 변환 후 정렬
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
            entryList.sort((e1, e2) -> {
                int valCompare = e1.getValue().compareTo(e2.getValue());
                if (valCompare != 0) return valCompare;
                else return e1.getKey().compareTo(e2.getKey());
            });

            // 정렬한 값을 현재 행에 저장
            int col = 0;
            for (Map.Entry<Integer, Integer> entry : entryList) {
                field[row][col++] = entry.getKey();
                field[row][col++] = entry.getValue();
            }

            // 정렬한 결과가 기존보다 짧은 경우는 나머지 값들을 0으로 변경
            for (col = entryList.size() * 2; col < maxCol; col++) {
                field[row][col] = 0;
            }
            // maxCol 갱신
            maxCol = Math.max(maxCol, entryList.size() * 2);
        }
    }

    private static void sortByCol() {
        for (int col = 0; col < maxCol; col++) {
            Map<Integer, Integer> map = new HashMap<>();
            // 열 탐색
            for (int row = 0; row < maxRow; row++) {
                // 0인 경우 통과
                if (field[row][col] == 0) continue;
                // 아직 입력되지 않은 숫자인 경우 map에 추가
                if (!map.containsKey(field[row][col])) {
                    map.put(field[row][col], 1);
                }
                // 입력된 숫자라면 +1
                else {
                    map.put(field[row][col], map.get(field[row][col]) + 1);
                }
            }
            // 정렬 후 저장
            // Map을 List로 변환 후 정렬
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
            entryList.sort((e1, e2) -> {
                int valCompare = e1.getValue().compareTo(e2.getValue());
                if (valCompare != 0) return valCompare;
                else return e1.getKey().compareTo(e2.getKey());
            });

            // 정렬한 값을 현재 행에 저장
            int row = 0;
            for (Map.Entry<Integer, Integer> entry : entryList) {
                field[row++][col] = entry.getKey();
                field[row++][col] = entry.getValue();
            }

            // 정렬한 결과가 기존보다 짧은 경우는 나머지 값들을 0으로 변경
            for (row = entryList.size() * 2; row < maxCol; row++) {
                field[row][col] = 0;
            }

            // maxRow 갱신
            maxRow = Math.max(maxRow, entryList.size() * 2);
        }
    }
}