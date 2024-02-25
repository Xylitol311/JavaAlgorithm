package programmers;

import java.util.*;
public class DevelopFunction {
    public static void main(String[] args) {
        solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1});
    }
    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++){
            queue.add((int) Math.ceil((100 - progresses[i]) / (double) speeds[i]));
        }

        List<Integer> answer = new ArrayList<>();

        while(!queue.isEmpty()){
            int day = queue.poll();
            int cnt = 1;
            while(!queue.isEmpty() && day >= queue.peek()){
                cnt++;
                queue.poll();
            }
            answer.add(cnt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}