package zero_base;

public class P1_1 {
    public static void main(String[] args) {
        int answer = 0;
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            answer += i;
            System.out.println(answer);
        }
        System.out.println(answer);
    }
}

