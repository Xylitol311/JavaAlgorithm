package programmers.solved;

import java.util.Arrays;
import java.util.HashMap;

public class Running {
    public static void main(String[] args) {
        Running sol = new Running();
        String[] str1 = {"mumu", "soe", "poe", "kai", "mine"};
        String[] str2 = {"kai", "kai", "mine", "mine"};
        System.out.println("정답은 " + Arrays.toString(sol.solution(str1, str2)));

    }
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> mappedByName = new HashMap<>();
        HashMap<Integer, String> mappedByRank = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            mappedByName.put(players[i], i);
            mappedByRank.put(i, players[i]);
        }
        
        for (String name : callings) {
            int idx = mappedByName.get(name);
            String player = mappedByRank.get(idx);
            
            mappedByRank.put(idx, mappedByRank.get(idx - 1));
            mappedByRank.put(idx - 1, player);
            
            mappedByName.put(mappedByRank.get(idx), idx);
            mappedByName.put(player, idx - 1);
        }
        for (int i = 0; i < players.length; i++) {
            players[i] = mappedByRank.get(i);
        }
        return players;
    }
}