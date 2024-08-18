package baekjoon.solved.bronze;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2164 {
    public static void main(String[] args) throws IOException{
        // N을 입력 받고 큐 생성해서 큐에 값 저장
        // 반복(큐 사이즈가 1이 될 때까지)
        // 맨 앞값 버리고 그 다음값 맨 뒤로 이동
        // 남은 값 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<Integer>();

        // 큐 입력
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        // 큐 사이즈가 1이 될 때까지 주어진 명령 반복
        int tmp;
        while(queue.size() != 1) {
            queue.poll();
            tmp = queue.poll();
            queue.add(tmp);
        }

        System.out.println(queue.poll());
    }
}

