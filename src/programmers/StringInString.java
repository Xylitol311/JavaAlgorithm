package programmers;

public class StringInString {
    public static void main(String[] args) {
        StringInString sol = new StringInString();
        String str1 = "ab6CDE443fgh22iJKlmn1o";
        String str2 = "6CD";
        System.out.println("정답은 " + sol.solution(str1, str2));

    }
    public int solution(String str1, String str2) {
        if (str1.contains(str2)) {
            return 1;
        }
        return 2;
    }
}