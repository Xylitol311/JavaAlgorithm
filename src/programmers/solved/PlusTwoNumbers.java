package programmers.solved;

public class PlusTwoNumbers {
    public static void main(String[] args) {
        PlusTwoNumbers sol = new PlusTwoNumbers();
        String str1 = "ab6CDE443fgh22iJKlmn1o";
        String str2 = "6CD";
        System.out.println("정답은 " + sol.solution(2, 3));

    }
    public int solution(int num1, int num2) {
        int answer = -1;

        answer = num1 + num2;
        return answer;
    }
}