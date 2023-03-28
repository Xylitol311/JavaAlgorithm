package zero_base;

public class P1_5 {
    public static void main(String[] args) {
        String S = "1101";
        int num = Integer.parseInt(S, 2);
        int answer = 0;

        while (num != 0) {
            if (num % 2 == 1) {
                num -= 1;
            } else {
                num /= 2;
            }
            answer += 1;
        }

        System.out.println(answer);

    }
}

