package zero_base;


import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P4_1 {
    public static void main(String[] args) {
        P4_1 sol = new P4_1();
        String[] str = {"naver", "kakao"};
        System.out.println("정답은 "+ sol.solution(str));

    }
    public String solution(String[] arr) {
        String answer = "";
        answer = String.join(",", arr);
        return answer;
    }
}