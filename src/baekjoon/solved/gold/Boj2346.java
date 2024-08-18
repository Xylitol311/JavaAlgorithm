package baekjoon.solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 덱 생성
        Deque<int[]> q = new ArrayDeque<>(); // 풍선 인덱스와 내용을 같이 저장할 예정
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        // 첫 번째 풍선부터 터뜨리니 1 추가
        sb.append("1 ");
        int in = arr[0];

        for(int i=1; i<n; i++){
            q.add(new int[] {(i+1), arr[i]}); // {풍선 idx, 내용}
        }

        while(!q.isEmpty()) {
            // 양수인 경우
            if(in >0) {
                // 순서 뒤로 돌리기
                for(int i=1; i<in; i++) {
                    q.add(q.poll()); // 뒤에서 빼서 앞으로 넣기
                }

                int[] nxt = q.poll(); //
                in = nxt[1];
                sb.append(nxt[0]+" ");
            }
            // 음수인 경우
            else {
                for(int i=1; i<-in; i++	) {
                    q.addFirst(q.pollLast());
                }

                int[] nxt = q.pollLast();
                in = nxt[1];
                sb.append(nxt[0]+" ");
            }
        }

        System.out.println(sb.toString());

    }
}