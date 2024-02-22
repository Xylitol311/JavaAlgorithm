package programmers;

import java.util.HashSet;
import java.util.Set;

public class Ponketmon {
    public static void main(String[] args) {

    }
    public static int solution(int[] nums) {
        int answer;
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        answer = Math.min(nums.length / 2, numSet.size());
        return answer;
    }
}