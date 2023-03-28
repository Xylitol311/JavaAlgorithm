package zero_base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2_2 {
    public static void main(String[] args) {
        Main sol = new Main();
        System.out.println(Arrays.toString(sol.solution("Hello, World!? stay")));
    }
}

class Main {
    public String[] solution(String s) {
        String[] answer = {};
        String[] temp = s.split("\\.|\\,|!|\\?| ");
        List<String> result = new ArrayList<>();
        for (String t : temp) {
            System.out.println("t = " + t);
            if (t.isBlank()) {
                continue;
            }
            StringBuffer sb = new StringBuffer(t);
            String reverse = sb.reverse().toString();
            System.out.println("reverse = " + reverse);
            result.add(reverse);
        }

        answer = result.toArray(result.toArray(new String[0]));
        return answer;
    }
}