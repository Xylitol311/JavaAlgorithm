import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
조합 문제
1. 한 줄을 입력받고 앞 숫자가 0인지 확인(-> 0이면 바로 종료)
2. 첫 숫자의 크기만큼 요소 배열을 만들어 각각의 요소를 조합
3. 조합을 이용해 전체 요소 중 6개를 순서대로 골라서 6개가 완성된 경우 출력
    3-1. 선택된 요소를 확인하는 건 비트마스킹을 이용
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int result, length, elements[];

    private static boolean input() throws IOException {
        st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());

        // 0이면 종료
        if (length == 0) return false;

        // 요소를 배열에 입력
        elements = new int[length];
        for (int i = 0; i < length; i++) {
            elements[i] = Integer.parseInt(st.nextToken());
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        while (true){
            if(!input()) break;
            combination(0, 0, 0);
            sb.append("\n");
        };
        System.out.println(sb);
    }

    private static void combination(int elementIdx, int selectedCnt, int isSelected) {
        // 6개를 다 고른 경우
        if (selectedCnt == 6) {
            for (int idx = 0; idx < length; idx++) {
                if ((isSelected & 1 << idx) != 0) {
                    sb.append(elements[idx]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        // 마지막 요소에 도달한 경우
        if (elementIdx == length) return;

        // 현재 요소 선택
        combination(elementIdx + 1, selectedCnt + 1, isSelected | 1 << elementIdx);

        // 현재 요소 미선택
        combination(elementIdx + 1, selectedCnt, isSelected);
    }
}