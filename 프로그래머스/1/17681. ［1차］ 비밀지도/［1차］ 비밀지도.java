import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        // or 연산으로 두 수 합치기
        // 합쳐진 수 비트마스킹 연산으로 1인 경우 '#', 0인 경우 ' '로 변경.
        // 각 수를 변환된 String 형태의 답으로 정리 및 출력
        String[] answer = new String[n];
        int[] orResult = new int[n];
        
        for (int i = 0; i < n; i++) {
            orResult[i] = arr1[i] | arr2[i];
        }
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                sb.append((orResult[i] & (1 << j)) != 0 ? "#" : " ");
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}