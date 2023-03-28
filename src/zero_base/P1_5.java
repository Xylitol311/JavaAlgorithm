package zero_base;

import java.util.stream.Stream;

public class P1_4 {
    public static void main(String[] args) {
        String s = "18838274";
        String[] str = s.split("");
        int[] num = Stream.of(str).mapToInt(Integer::parseInt).toArray();

        int[] result = new int[10];
        for (int i : num) {
            result[i] += 1;
        }

        int max = result[0];
        int maxIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) {
                max = result[i];
                maxIndex = i;
            }
        }



    }
}

