package baekjoon.solved.day0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> que = new LinkedList<int[]>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i ++) {
                int num = Integer.parseInt(st.nextToken());

                que.add(new int[]{i , num});
            }

            int cnt = 0;
            while (true) {
                // 현재 배열
                int[] tmp = que.poll();
                boolean chk = false; // 더 높은 중요도 체크

                for (int[] curQue : que) {
                    if (curQue[1] > tmp[1]) { // 중요도 체크
                        chk = true; // 있으면 true로 바꾸고 종료
                        break;
                    }
                }

                if (!chk) {
                    cnt++;
                    if (tmp[0] == M) break;
                } else {
                    que.add(tmp);
                }
            }

            System.out.println(cnt);
        }
    }
}