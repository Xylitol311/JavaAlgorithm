// 메모리: 17440 KB / 시간: 120 ms

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int m, n;
    static long[] ax, ay, bx, by;
    static HashSet<Long> set;
    static long dx, dy;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(dx + " " + dy);
    }

    private static void input() throws IOException {
        // 별자리 점 입력
        m = Integer.parseInt(br.readLine());
        ax = new long[m];
        ay = new long[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            ax[i] = Long.parseLong(st.nextToken());
            ay[i] = Long.parseLong(st.nextToken());
        }

        // 사진 속 별 입력
        n = Integer.parseInt(br.readLine());
        bx = new long[n];
        by = new long[n];
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            bx[i] = Long.parseLong(st.nextToken());
            by[i] = Long.parseLong(st.nextToken());
            long key = (bx[i] << 32) | (by[i] & 0xFFFFFFFFL);
            set.add(key);
        }
    }

    private static void solution() {
        // 기준점(ax[0], ay[0])을 사진의 각 점에 매칭하며 평행 이동량 검사
        outer:
        for (int i = 0; i < n; i++) {
            long tx = bx[i] - ax[0];
            long ty = by[i] - ay[0];
            boolean ok = true;

            // 모든 별자리 점을 이동시켜 존재 여부 확인
            for (int j = 0; j < m; j++) {
                long x = ax[j] + tx;
                long y = ay[j] + ty;
                long key = (x << 32) | (y & 0xFFFFFFFFL);
                if (!set.contains(key)) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                dx = tx;
                dy = ty;
                break outer;
            }
        }
    }
}