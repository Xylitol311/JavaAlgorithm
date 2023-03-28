package zero_base;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P3_5 {
    public static void main(String[] args) {
        String[] BJ = {"혁준", "하밥", "양상", "심심이", "소스왕"};
        String[] One = {"혁준", "양상"};
        String[] Two = {"심심이", "소스왕"};

        System.out.println("정답은 "+ solution(BJ, One, Two));

    }
    public static String solution(String[] bj, String[] one, String[] two) {
        String answer = "";
        int awards = 450 + one.length * 150 + two.length * 300;
        String winner = "";
        List<String> bjList = new ArrayList<>(Arrays.asList(bj));
        List<String> oneList = new ArrayList<>(Arrays.asList(one));
        List<String> twoList = new ArrayList<>(Arrays.asList(two));
        bjList.removeAll(oneList);
        bjList.removeAll(twoList);
        winner = bjList.get(0);
        answer = awards + "만원(" + winner + ")";

        return answer;
    }
}