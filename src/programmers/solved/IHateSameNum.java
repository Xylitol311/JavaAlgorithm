package programmers.solved;

import java.util.ArrayList;
import java.util.List;

public class IHateSameNum {
    public static void main(String[] args) {

    }
    public int[] solution(int []arr) {
        int[] answer = {};

        List<Integer> array = new ArrayList<>();
        int value = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != value){
                array.add(arr[i]);
                value = arr[i];
            }
        }

        answer = array.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}