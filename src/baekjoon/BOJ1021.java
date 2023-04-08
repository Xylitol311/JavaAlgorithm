package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1021 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<Integer> deque = new LinkedList<Integer>();

        int count = 0; // 누적 이동횟수

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());	// 큐의 크기
        int M = Integer.parseInt(st.nextToken());	// 추출할 숫자 횟수

        // Deque에 1부터 N까지의 숫자를 저장한다.
        for(int i = 1; i <= N; i++) {
            deque.offer(i);
        }

        int[] seq = new int[M];	// 추출할 숫자를 담을 배열 생성

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }


        for(int i = 0; i < M; i++) {
            // 추출할 숫자의 위치 찾기
            int target_idx = deque.indexOf(seq[i]);
            int half_idx;

            if(deque.size() % 2 == 0) { //크기가 짝수의 경우 중간에서 바로 앞 위치를 중간지점으로 설정
                half_idx = deque.size() / 2 - 1;
            }
            else {
                half_idx = deque.size() / 2;
            }


            // 중간 지점과 추출할 숫자의 위치를 비교하여 실행할 연산을 판별
            if(target_idx <= half_idx) { // 덱 크기가 짝수일 경우는 중간지점을 포함한다.
                // (2번 연산)
                for(int j = 0; j < target_idx; j++) {
                    int temp = deque.pollFirst();
                    deque.offerLast(temp);
                    count++;
                }
            }
            else {
                // (3번 연산)
                for(int j = 0; j < deque.size() - target_idx; j++) {
                    int temp = deque.pollLast();
                    deque.offerFirst(temp);
                    count++;
                }

            }
            deque.pollFirst();	// 연산이 끝나면 맨 앞 원소를 삭제
        }

        System.out.println(count);
    }
}
