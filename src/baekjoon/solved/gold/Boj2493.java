package baekjoon.solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] towers = new int[N];

        Map<Integer, Integer> map = new HashMap<>(); // 키: 레이저 송신한 타워의 인덱스, 값: 레이저 수신한 타워의 인덱스
        Queue<Integer> heightQue = new ArrayDeque<>();
        Queue<Integer> indexQue = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nowIdx;
        int nowHeight;

        // 배열 입력
        for (int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        // 타워 탐색
        for (int i = N - 1; i >= 0; i--) {
            nowIdx = i;
            nowHeight = towers[i];

            // 큐가 비어있지 않으면 현재값과 큐의 값을 비교
            while (!heightQue.isEmpty() && nowHeight >= heightQue.peek()) {
                heightQue.poll();
                map.put(indexQue.poll(), nowIdx+1);
            }

            indexQue.add(nowIdx);
            heightQue.add(nowHeight);
        }

        // 큐가 남아있다면?
        while (!indexQue.isEmpty()) {
            if (indexQue.size() == 1) {
                map.put(indexQue.poll(), 0);
                heightQue.poll();
                break;
            }

            nowIdx = indexQue.poll();
            nowHeight = heightQue.poll();

            if (nowHeight > heightQue.peek()) {
                map.put(nowIdx, 0);
            } else {
                map.put(nowIdx, indexQue.peek() + 1);
            }
        }

        // keySet을 추출하여 인덱스 순서대로 정렬
        List<Integer> indexList = new ArrayList<>(map.keySet());
        Collections.sort(indexList);


        for (int idx : indexList) {
            answer.append(map.get(idx)).append(" ");
        }

        System.out.println(answer);
    }
}