package programmers.solved;

import java.util.HashMap;
import java.util.Map;

public class UnsuccessfulRunner {
    public static void main(String[] args) {

    }
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        for (String player : participant) {
            map.put(player, map.getOrDefault(player, 0) + 1);
        }

        for (String player : completion) {
            map.put(player, map.get(player) - 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0 ){
                return entry.getKey();
            }
        }

        return "미완주한 선수가 없습니다.";
    }
}