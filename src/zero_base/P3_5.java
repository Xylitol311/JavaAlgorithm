package zero_base;


import java.util.ArrayList;
import java.util.List;

public class P3_3 {
    public static void main(String[] args) {
        String str = "ABBA";
        System.out.println("정답은 "+ solution(str));

    }
    public int solution(String s) {
        String answer = s;
        boolean check = false;

        while (!check) {
            answer = remove(answer);
            check = search(answer);
        }

        if (answer.isEmpty()) {
            return 1;
        }

        return 0;
    }

    public static boolean search(String s) {
        // 중복이 존재하면 false, 없으면 true return
        char temp;

        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (temp != s.charAt(j)) {
                    break;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static String remove(String s) {
        //해당 글자의 중복 시작점과 끝지점을 파악하고 (끝 - 시작)이 0이 아니면 해당 인덱스 범위의 글자 삭제
        int start = 0;
        int end = 0;
        char temp;
        String result = "";
        List<Integer> indexs = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            start = i;
            end = i;
            temp = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (temp != s.charAt(j)) {
                    break;
                }
                end = j;
            }
            if (start != end) {
                indexs.add(start);
                indexs.add(end);
                i = end;
            }
        }
        // 중복값 제거
        result += s.substring(0,indexs.get(0));
        for (int i = 1; i < indexs.size(); i += 2) {
            if (i + 1 >= indexs.size()) {
                result += s.substring(indexs.get(i)+1);
                break;
            }
            result += s.substring(indexs.get(i)+1, indexs.get(i + 1));
        }
        return result;
    }
}