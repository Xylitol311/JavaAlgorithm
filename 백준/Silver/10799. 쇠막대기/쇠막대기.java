import java.util.Scanner;

public class Main {
    
    // 입력을 받는 함수
    public static String input() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
    
    // 쇠막대기 절단 문제를 해결하는 함수
    public static int solution(String arrangement) {
        int answer = 0;
        int stickCount = 0; // 현재 겹쳐진 막대기 개수
        
        for (int i = 0; i < arrangement.length(); i++) {
            char current = arrangement.charAt(i);
            
            if (current == '(') {
                stickCount++; // 새로운 막대기 시작
            } else { // current == ')'
                stickCount--; // 막대기 하나 제거
                
                // 이전 문자가 '('라면 레이저
                if (i > 0 && arrangement.charAt(i-1) == '(') {
                    answer += stickCount; // 현재 겹쳐진 막대기들이 절단됨
                } else {
                    answer += 1; // 막대기의 끝 (마지막 조각 하나 추가)
                }
            }
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
        String arrangement = input();
        int result = solution(arrangement);
        System.out.println(result);
    }
}