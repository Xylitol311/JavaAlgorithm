// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
먼저 감소하는 수 list를 만들어 정렬 후 n번째 수를 반환한다.
현재 뽑은 수 num을 기준으로 num을 10으로 나눈 나머지보다 작은 값만 다음에 올 수 있다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static List<Long> numList;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numList = new ArrayList<>();

        if (N <= 10) System.out.println(N);
        else if (N > 1022) System.out.println("-1"); // 1022번째 감소하는 수가 9876543210로 만들 수 있는 최대 감소하는 수이다.
        else {
            for (int i = 0; i < 10; i++) {
                bp(i, 1);
            }
            Collections.sort(numList);

            System.out.println(numList.get(N));
        }
    }

    public static void bp(long num, int idx) {
        if (idx > 10) return;

        numList.add(num);
        for (int i = 0; i < num % 10; i++) {
            bp((num * 10) + i, idx + 1);
        }
    }
}