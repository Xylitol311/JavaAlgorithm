import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        List<int[]> allCombinations = new ArrayList<>();
        
        // 1~n 중에서 5개를 선택하는 모든 조합 생성
        generateCombinations(n, 5, 1, new ArrayList<>(), allCombinations);
        
        // 각 조합이 모든 시도 조건을 만족하는지 확인
        for (int[] combination : allCombinations) {
            if (isValidCombination(combination, q, ans)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 백트래킹을 이용한 조합 생성
    private void generateCombinations(int n, int r, int start, List<Integer> current, List<int[]> result) {
        // 5개를 모두 선택했으면 결과에 추가
        if (current.size() == r) {
            int[] combination = new int[r];
            for (int i = 0; i < r; i++) {
                combination[i] = current.get(i);
            }
            result.add(combination);
            return;
        }
        
        // start부터 n까지 순회하며 조합 생성
        for (int i = start; i <= n; i++) {
            current.add(i);
            generateCombinations(n, r, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    // 해당 조합이 모든 시도 조건을 만족하는지 확인
    private boolean isValidCombination(int[] combination, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int matchCount = countMatches(combination, q[i]);
            if (matchCount != ans[i]) {
                return false;
            }
        }
        return true;
    }
    
    // 두 배열의 교집합 개수 계산
    private int countMatches(int[] combination, int[] query) {
        int count = 0;
        for (int num : combination) {
            for (int q : query) {
                if (num == q) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}