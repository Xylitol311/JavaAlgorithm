package zero_base;

public class P2_1 {
    public static void main(String[] args) {
        String phrases = "Snowball";
        int n = 5;
        int t = 18;

        String answer = "";
        String temp = "";
        for (int i = 0; i < n; i++) {
            temp += ".";
        }
        temp = temp.concat(phrases);

        for (int i = 0; i < t; i++) {
            char tmp = temp.charAt(0);
            temp += tmp;
            temp = temp.substring(1);
        }

        answer = temp.substring(0, n);
        System.out.println(answer);

    }
}

