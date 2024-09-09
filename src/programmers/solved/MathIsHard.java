package programmers.solved;

import java.util.ArrayList;
import java.util.List;

public class MathIsHard {
    public static void main(String[] args) {
//        solution(new int[]{1,2,3,4,5});
//        System.out.println("정답은 " + Arrays.toString(solution(new int[]{4,4,4,5,3})));
    }
    public static int[] solution(int[] answers) {
        int[] answer;

        int[] no1 = new int[]{1,2,3,4,5};
        int[] no2 = new int[]{2,1,2,3,2,4,2,5};
        int[] no3 = new int[]{3,3,1,1,2,2,4,4,5,5};

        int[] score = new int[3];

        for (int i = 0; i < answers.length; i++){
            if (no1[i % no1.length] == answers[i]) score[0]++;

            if (no2[i % no2.length] == answers[i]) score[1]++;

            if (no3[i % no3.length] == answers[i]) score[2]++;
        }

        int max = Math.max(score[0], Math.max(score[1], score[2]));

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < score.length; i++) {
            if (score[i] == max) result.add(i + 1);
        }

        answer = result.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}