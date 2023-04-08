package programmers;

import java.util.LinkedList;

public class RotateArray {
    public static void main(String[] args) {
        RotateArray sol = new RotateArray();
        int[] numbers = {1, 2, 3};
        String direction = "right";
        System.out.println("정답은 " + sol.solution(numbers, direction));

    }
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        LinkedList<Integer> deque = new LinkedList<Integer>();

        for (int i : numbers) {
            deque.offer(i);
        }

        if (direction.equals("right")) {
            int temp = deque.pollLast();
            deque.offerFirst(temp);
        } else {
            int temp = deque.pollFirst();
            deque.offerLast(temp);
        }

        for (int i = 0; i < deque.size(); i++) {
            answer[i] = deque.get(i);
        }
        return answer;
    }
}