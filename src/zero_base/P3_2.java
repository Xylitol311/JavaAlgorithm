package zero_base;


public class P3_1 {
    public static void main(String[] args) {
        String str = "aacddefg";
        System.out.println("정답은 "+ solution(str));

    }
    public static String  solution(String str) {
        String answer = "";
        boolean check = false;

        while (!check) {
            answer = remove(str);
            check = search(answer);
        }

        return answer;
    }

    public static boolean search(String s) {
        // 중복이 존재하면 false, 없으면 true return

    }

    public static String remove(String s) {
        //해당 글자의 중복 시작점과 끝지점을 파악하고 (끝 - 시작)이 0이 아니면 해당 인덱스 범위의 글자 삭제
        int start = 0;
        int end = 0;
        char temp;
        String result;

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
            result = s.substring(end);
            
        }

    }
}