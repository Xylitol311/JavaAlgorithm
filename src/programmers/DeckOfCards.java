package programmers;

import java.util.Arrays;

public class DeckOfCards {
    public static void main(String[] args) {
//        DoubleArray sol = new DoubleArray();
//        int[] num = {1, 2, 3, 4, 5};
//        System.out.println("정답은 " + Arrays.toString(sol.solution(num)));
    
    }
    
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0, idx2 = 0;
        
        for (String word : goal) {
            if (idx1 < cards1.length && word.equals(cards1[idx1])) {
                idx1++;
                continue;
            } else if (idx2 < cards2.length && word.equals(cards2[idx2])) {
                idx2++;
                continue;
            } else {
                return "No";
            }
        }
        return "Yes";
    }
}
