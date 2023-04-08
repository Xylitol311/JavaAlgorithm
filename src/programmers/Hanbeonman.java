import java.lang.reflect.Array;
import java.util.Arrays;

public class Hanbeonman {
    public static void main(String[] args) {
        Hanbeonman sol = new Hanbeonman();
        System.out.println("정답은 "+ sol.solution("hello"));

    }
    public String solution(String s) {
        String answer = "";
        int[] result = new int[26];

        // 문자열 s를 하나씩 읽어서 각 알파벳에 해당하는 인덱스에 값을 +1 한다.
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 97] += 1;
        }

        // result의 값 중 1인 것을 찾아 answer에 입력한다.
        for (int i = 0; i < result.length; i++) {

            if (result[i] == 1) {
                answer += (char) (i + 97);
            }
        }
        return answer;
    }
}