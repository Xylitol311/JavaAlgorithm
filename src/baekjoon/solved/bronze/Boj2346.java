package baekjoon.solved.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Balloon {
    private int idx;
    private int content;

    public Balloon() {
    }

    public Balloon(int idx, int content) {
        this.idx = idx;
        this.content = content;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}

public class Boj2346 {
    public static void main(String[] args) throws IOException {
        // N을 입력 받고 덱 생성해서 덱에 저장
        // 정답을 저장할 StringBuilder 생성
        // 첫 번째 풍선은 수동으로 저장
        // 나머진 반복을 통해 양수이면 오른쪽으로 이동
        // 음수면 왼쪽으로 이동
        // 덱이 비면 반복 종료
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        // 풍선 객체를 만들어 각각의 인덱스와 값을 저장하고 풍선 객체를 덱에 저장
        Deque<Balloon> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 덱에 풍선 객체 저장
        for (int i = 1; i <= N; i++) {
            deque.add(new Balloon(i, Integer.parseInt(st.nextToken())));
        }

        // 첫 번째 풍선은 바로 입력
        int moveCnt = deque.poll().getContent();
        sb.append(1).append(" ");

        while (!deque.isEmpty()) {
            if (moveCnt > 0) { // 풍선 안에 종이가 양수
                for (int i = 1; i < moveCnt; i++) { // 시작점인 풍선은 꺼낸 상태이므로 1부터 시작
                    deque.add(deque.poll());
                }
                moveCnt = deque.peek().getContent();
                sb.append(deque.poll().getIdx()).append(" ");
            } else { // 풍선 안에 종이가 음수
                for (int i = 1; i < -moveCnt; i++) { // 시작점인 풍선은 꺼낸 상태이므로 1부터 시작
                    deque.addFirst(deque.pollLast());
                }
                moveCnt = deque.peekLast().getContent();
                sb.append(deque.pollLast().getIdx()).append(" ");
            }
        }
        System.out.println(sb);
    }
}

