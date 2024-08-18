package baekjoon.solved.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Boj11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //절대값 기준으로 앞 값이 더 크다면 자리를 바꾸기
                if(Math.abs(o1) > Math.abs(o2)) {
                    return Math.abs(o1) - Math.abs(o2);
                    //절대값 기준으로 두 값이 같다면 음수를 앞으로 보내기
                }else if(Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                }else {
                    return -1;
                }
            }
        });

        // 정답 저장
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N; i++){
            int val = Integer.parseInt(br.readLine());
            if(val == 0){
                if(pq.isEmpty()) System.out.println("0");
                else System.out.println(pq.poll());
            }else{
                pq.add(val);
            }
        }
    }
}