package programmers.solved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Clothes {
    public static void main(String[] args) {
//        solution(new int[]{1,2,3,4,5});
//        System.out.println("정답은 " + Arrays.toString(solution(new int[]{4,4,4,5,3})));
    }

    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, ArrayList<String>> closet = new HashMap<>();
        ArrayList<String> temp;

        // Map에 입력
        for (String[] clothe : clothes) {
            if (closet.containsKey(clothe[1])) {
                temp = closet.get(clothe[1]);
                temp.add(clothe[0]);
                closet.put(clothe[1], temp);
                continue;
            }
            ArrayList<String> newArray = new ArrayList<>();
            newArray.add(clothe[0]);
            closet.put(clothe[1], newArray);
        }

        ArrayList<String> temp2 = new ArrayList<>();
        for (String str : closet.keySet()) {
            temp2 = closet.get(str);
            answer *= temp2.size() + 1;
        }

        return answer - 1;
    }
}