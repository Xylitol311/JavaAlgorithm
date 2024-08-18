package programmers.solved;

import java.util.*;

public class MoreHotter {
    public static void main(String[] args) {

    }
    public int fail(int[] scoville, int K) {
        int answer = 0;

        Arrays.sort(scoville);

        if(scoville[0] >= K) {
            return 0;
        }
        List<Integer> numList = new ArrayList<>();

        for(int n : scoville){
            numList.add(n);
        }

        while(numList.size() > 1){
            int temp = numList.get(0) + numList.get(1)*2;
            numList.remove(0);
            numList.remove(0);
            numList.add(temp);
            answer++;

            Collections.sort(numList);
            if(numList.get(0) >= K) {
                return answer;
            }
        }


        return -1;
    }

    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int n : scoville){
            queue.add(n);
        }

        while(queue.peek() < K){
            if(queue.size() == 1) {
                return -1;
            }
            queue.add(queue.poll() + queue.poll()*2);
            answer++;
        }
        return answer;
    }
}