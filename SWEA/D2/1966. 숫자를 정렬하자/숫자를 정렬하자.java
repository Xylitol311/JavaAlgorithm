import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] numArray = new int[N];
            int[] countingArray = new int[100];

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 배열 입력
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                numArray[i] = num;
                countingArray[num]++;
            }

            // 카운팅 배열 시작 인덱스 설정
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                if (countingArray[i] == 0) {
                    continue;
                }
                idx = i;
                break;
            }

            // 카운팅 배열 누적합 구하기
            for (int i = idx + 1; i < countingArray.length; i++) {
                if (countingArray[i] != 0) {
                    countingArray[i] = countingArray[i] + countingArray[idx];
                    idx = i;
                }
            }

            // 정렬
            int[] result = new int[N];
            for (int i = numArray.length - 1; i >= 0; i--) {
                if (countingArray[numArray[i]] != 0) {
                    result[--countingArray[numArray[i]]] = numArray[i];
                }
            }

            sb.append(String.format("#%d", test_case));
            for (int num : result) {
                sb.append(" " + num);
            }
            sb.append("\n");
        }// for test

        System.out.println(sb.toString());
    }// main
}