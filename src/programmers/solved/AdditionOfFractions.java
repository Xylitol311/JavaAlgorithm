package programmers.solved;

import java.util.Arrays;

public class AdditionOfFractions {
    public static void main(String[] args) {
        AdditionOfFractions sol = new AdditionOfFractions();
        String str1 = "ab6CDE443fgh22iJKlmn1o";
        String str2 = "6CD";
//        System.out.println("정답은 " + sol.solution(1, 2, 3, 4));
        System.out.println("정답은 " + Arrays.toString(sol.solution(1,2,3,4)));

    }
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int numerator = (numer1 * denom2) + (numer2 * denom1);
        int denominator = denom1 * denom2;

        int gdcNum = ((numerator > denominator) ? gdc(numerator, denominator) : gdc(denominator, numerator));

        numerator = numerator / gdcNum;
        denominator = denominator / gdcNum;

        int[] answer = {numerator, denominator};
        return answer;
    }

    public int gdc(int a, int b) {
        if (b == 0) {
            return a;
        }
        int r = a % b;
        return gdc(b, r);
    }
}