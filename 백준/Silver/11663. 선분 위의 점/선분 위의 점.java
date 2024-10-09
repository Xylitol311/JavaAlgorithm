// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
선분 위에 점들과 x, y가 주어졌을 때 x, y 좌표 내에 있는 점의 개수를 구하기
이분 탐색을 이용해 start와 end 범위를 구한다.
start <= x, end >= y, 반복은 항상 left <= right일 때만

start 구하기
1. line[mid]가 x보다 작으면 left = mid + 1, 반대면 right= mid - 1
2. 이걸 반복하면 line[left]는 x보다 크거나 같은 상태.
3. start에 left 입력

end 구하기
1. line[mid]가 y보다 크면 right = mid - 1, 반대면 left = mid + 1
2. 이걸 반복하면 lind[right]는 y보다 작거나 같은 상태.
3. end에 right + 1를 입력한다.
    - +1을 하는 이유. 마지막에 end - start를 통해 범위의 포함된 개수를 구할 것이기 때문

모든 작업이 끝나면 항상 아래와 같은 상태가 된다.
- x <= left, right <= y
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int result;

    static long[] line;
    static int N, M;
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = new long[N];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            line[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(line);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb.toString().trim());
    }

    private static void solution() throws IOException {
        // 좌표 정보를 한 줄씩 입력받아 이분 탐색 후 결과를 sb에 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int result = binarySearch(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            sb.append(result).append('\n');
        }
    }

    private static int binarySearch(int x, int y) {
        int left = 0;
        int right = line.length - 1;

        // start 구하기
        while (left <= right) {
            int mid = (left + right) / 2;
            if (line[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int start = left;

        // end 구하기
        left = 0;
        right = line.length - 1;
        // end 구하기
        while (left <= right) {
            int mid = (left + right) / 2;
            if (line[mid] > y) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int end = right + 1;

        return end - start;
    }
}